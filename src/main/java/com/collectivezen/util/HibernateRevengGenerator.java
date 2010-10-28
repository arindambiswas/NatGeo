package com.collectivezen.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HibernateRevengGenerator
{
	Writer output = null;
	String init_text = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
	String schemaDefinition = "";
	String schemaPkDefinition = "";
	String schemaColsDefinition = "";
	String tableSchemaNode = "";
	String allTableNode = "";
	String allTableSchemaNode = "";

	String DB_CONNECTION_URL = "jdbc:postgresql://localhost:5432/fhe_masterclass_pojo";
	String DB_USERNAME = "kalki";
	String DB_PASSWORD = "k4lk1";
	String PACKAGE_NAME = "com.codedrunks.ikriti.fhe.hb";
	String OUTPUT_FILE_NAME = "hibernate.reveng.xml";
	
	public HibernateRevengGenerator(String dbConnection, String dbUserName,
			String dbPassword, String packageName, String outputFile)
	{
		this.DB_CONNECTION_URL = dbConnection;
		this.DB_USERNAME = dbUserName;
		this.DB_PASSWORD = dbPassword;
		this.PACKAGE_NAME = packageName;
		this.OUTPUT_FILE_NAME = outputFile;
	}

	public void createConfigFile()
	{
		try
		{
			File file = new File(OUTPUT_FILE_NAME);
			output = new BufferedWriter(new FileWriter(file));
			initFileCreation();

			Class.forName("org.postgresql.Driver");
			String databaseName = DB_CONNECTION_URL;
			Connection jdbcConnection = DriverManager.getConnection(
					databaseName, DB_USERNAME, DB_PASSWORD);
			System.out
					.println("Have secured connection with Database" + jdbcConnection.toString());
			Statement stmt = jdbcConnection.createStatement();
			Statement stmt1 = jdbcConnection.createStatement();
			ResultSet result = stmt
					.executeQuery("select relname from pg_stat_user_tables order by relname;");
			ResultSet res;
			while (result.next())
			{
				// System.out.println("table.." + result.getString(1));
				createTableNode(result.getString(1));
				ResultSet res2 = stmt1
						.executeQuery("SELECT ta.attname AS column_name,ic.relname AS index_name, bc.relname AS tab_name, "
								+ " i.indisprimary AS primary_key , i.indisunique AS unique_key "
								+ " FROM 	pg_class bc, pg_class ic, pg_index i,pg_attribute ta, pg_attribute ia WHERE "
								+ " bc.oid = i.indrelid  AND ic.oid = i.indexrelid  AND ia.attrelid = i.indexrelid "
								+ " AND ta.attrelid = bc.oid AND bc.relname = '"
								+ result.getString(1)
								+ "' AND ta.attrelid = i.indrelid"
								+ " AND ta.attnum = i.indkey[ia.attnum-1] ORDER BY index_name, tab_name, column_name ");
				String pKey = "";
				while (res2.next())
				{
					pKey = res2.getString(1);
					// System.out.println("\t\t Field : .." +
					// res2.getString(1));
				}
				/*
				 * res2 =stmt1.executeQuery(
				 * "SELECT relname FROM pg_class WHERE relkind = 'S' AND relnamespace IN "
				 * +
				 * "( SELECT oid FROM pg_namespace WHERE nspname NOT LIKE 'pg_%' AND nspname != 'information_schema' )"
				 * ); while(res2.next()) { System.out.println("\t\t Field : .."
				 * + res2.getString(1)); }
				 */
				res = stmt1
						.executeQuery("SELECT column_name FROM information_schema.columns WHERE table_name = '"
								+ result.getString(1) + "'");
				while (res.next())
				{
					// System.out.println("\t\t Field : .." + res.getString(1));
					boolean isPKey = (res.getString(1).equalsIgnoreCase(pKey)) ? true
							: false;
					createSchemaNode(result.getString(1), res.getString(1),
							isPKey);
				}
				tableSchemaNode = schemaDefinition + schemaPkDefinition
						+ schemaColsDefinition + "\t</table>\n\n";
				allTableSchemaNode += tableSchemaNode;
			}
			writeXML(allTableNode);
			writeXML("\n" + allTableSchemaNode);
			writeXML("</hibernate-reverse-engineering>");
			jdbcConnection.close();
			output.close();
		} catch (ClassNotFoundException e)
		{
			System.out.println("Exception in Test :: main...Error : ");
			e.printStackTrace();
		} catch (SQLException e)
		{
			System.out.println("Exception in Test :: main...Error : ");
			e.printStackTrace();
		} catch (Exception e)
		{
			System.out.println("Exception in Test :: main...Error : ");
			e.printStackTrace();
		}
	}

	private void writeXML(String text)
	{
		try
		{
			output.write(text);
			// System.out.println(text);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private void initFileCreation()
	{
		init_text += "<!DOCTYPE hibernate-reverse-engineering PUBLIC \"-//Hibernate/Hibernate Reverse Engineering DTD 3.0//EN\" \"http://hibernate.sourceforge.net/hibernate-reverse-engineering-3.0.dtd\" >\n\n";
		init_text += "<hibernate-reverse-engineering>\n\n";
		init_text += "\t<type-mapping>\n";
		init_text += "\t\t<sql-type jdbc-type=\"INTEGER\" hibernate-type=\"int\"/>\n";
		init_text += "\t\t<sql-type jdbc-type=\"FLOAT\" hibernate-type=\"float\"/>\n";
		init_text += "\t\t<sql-type jdbc-type=\"REAL\" hibernate-type=\"float\"/>\n";
		init_text += "\t\t<sql-type jdbc-type=\"NUMERIC\" hibernate-type=\"long\"/>\n";
		init_text += "\t\t<sql-type jdbc-type=\"VARCHAR\" length=\"1\" hibernate-type=\"char\"/>\n";
		init_text += "\t\t<sql-type jdbc-type=\"VARCHAR\" hibernate-type=\"string\"/>\n";
		init_text += "\t</type-mapping>\n\n";
		writeXML(init_text);
	}

	private void createTableNode(String tableName)
	{
		String tableNode = "\t<table-filter match-name=\"" + tableName
				+ "\" match-schema=\"public\" \n";
		tableNode += "\t\tpackage=\"" + PACKAGE_NAME + "\" />\n";
		allTableNode += tableNode;
		String[] tableNameParts = tableName.split("_");
		String tableJavaName = "";
		for (int index = 0; index < tableNameParts.length; index++)
		{
			tableJavaName += (tableNameParts[index].toUpperCase()).charAt(0)
					+ tableNameParts[index].substring(1);
		}
		schemaDefinition = "\t<table schema=\"public\" name=\""
				+ tableName.toLowerCase() + "\" class=\"" + PACKAGE_NAME + "."
				+ tableJavaName + "\">\n";
		schemaPkDefinition = "";
		schemaColsDefinition = "";
		// writeXML(tableNode);
	}

	private void createSchemaNode(String tableName, String fieldName,
			boolean isPKey)
	{
		String[] fieldNameParts = fieldName.split("_");
		String fieldJavaName = fieldNameParts[0];
		if (isPKey)
		{
			for (int index = 1; index < fieldNameParts.length; index++)
			{
				fieldJavaName += (fieldNameParts[index].toUpperCase())
						.charAt(0)
						+ fieldNameParts[index].substring(1);
			}
			schemaPkDefinition = "\t\t<primary-key>\n"
					+ "\t\t\t<generator class=\"sequence\"> \n"
					+ "\t\t\t\t<param name=\"sequence\">"
					+ tableName.toLowerCase() + "_" + fieldName.toLowerCase()
					+ "_seq</param>\n" + "\t\t\t</generator>\n"
					+ "\t\t\t<key-column name=\"" + fieldName
					+ "\" property=\"" + fieldJavaName + "\" />\n"
					+ "\t\t</primary-key>\n";
		} else
		{
			for (int index = 1; index < fieldNameParts.length; index++)
			{
				fieldJavaName += (fieldNameParts[index].toUpperCase())
						.charAt(0)
						+ fieldNameParts[index].substring(1);
			}
			schemaColsDefinition += "\t\t<column name=\"" + fieldName
					+ "\" property=\"" + fieldJavaName + "\" />\n";
		}
	}

	public void setPackageName(String pkgName)
	{
		this.PACKAGE_NAME = pkgName;
	}

	public String getPackageName()
	{
		return this.PACKAGE_NAME;
	}

	public void setOutputFileName(String fileName)
	{
		this.OUTPUT_FILE_NAME = fileName;
	}

	public String getOutputFileName()
	{
		return this.OUTPUT_FILE_NAME;
	}

	public static void main(String[] args) throws Exception
	{
		String DB_CONNECTION_URL;
		String DB_USERNAME;
		String DB_PASSWORD;
		String PACKAGE_NAME;
		String OUTPUT_FILE_NAME;

/*		
		String DB_CONNECTION_URL = "jdbc:postgresql://localhost:5432/fbtestbed";
		String DB_USERNAME = "kalki";
		String DB_PASSWORD = "k4lk1";
		String PACKAGE_NAME = "com.collectivezen.fbtestbed.hb";
		String OUTPUT_FILE_NAME = "hibernate.reveng.xml";
*/		
		if(args[0] == null)
		{
			throw new Exception("DB_CONNECTION_URL missing!");
		}
		else
		{
			DB_CONNECTION_URL = args[0];
		}
		
		if(args[1] == null)
		{
			throw new Exception("DB_USERNAME missing!");
		}
		else
		{
			DB_USERNAME = args[1];
		}		

		if(args[2] == null)
		{
			throw new Exception("DB_PASSWORD missing!");
		}
		else
		{
			DB_PASSWORD = args[2];
		}		

		if(args[3] == null)
		{
			throw new Exception("PACKAGE_NAME missing!");
		}
		else
		{
			PACKAGE_NAME = args[3];
		}
		
		if(args[4] == null)
		{
			throw new Exception("OUTPUT_FILE_NAME missing!");
		}
		else
		{
			OUTPUT_FILE_NAME = args[4];
		}
		
		HibernateRevengGenerator test = new HibernateRevengGenerator(DB_CONNECTION_URL,
				DB_USERNAME, DB_PASSWORD, PACKAGE_NAME, OUTPUT_FILE_NAME);
		// test.setPackageName("com.nanocast.blogified");

//		test
//				.setOutputFileName("/Users/arindam/Documents/workspace/AS3_workspace/Kalki/Projects/AB/Facebook-Testbed/src/main/resources/hibernate.reveng.xml");
		test.createConfigFile();
		
		System.out.println("Generated hibernate.reveng.xml with the following parameters :");

		System.out.println("\t DB_CONNECTION_URL : "+ DB_CONNECTION_URL);
		System.out.println("\t DB_USERNAME : "+ DB_USERNAME);
		System.out.println("\t DB_PASSWORD : "+ DB_PASSWORD);
		System.out.println("\t PACKAGE_NAME : "+ PACKAGE_NAME);
		System.out.println("\t OUTPUT_FILE_NAME : "+ OUTPUT_FILE_NAME);
		
	}
}
