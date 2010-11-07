package ikriti.natgeo.util;

import java.io.InputStream;
import java.util.Properties;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProjectProperties extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private static Properties properties;
	private static Logger logger = LoggerFactory.getLogger(ProjectProperties.class);

	
	public void init()
	{
		try
		{
			logger.debug("Loading ProjectProperties ...");
			InputStream is = ProjectProperties.class.getResourceAsStream("/fhe.properties");
			properties = new Properties();
			properties.load(is);

			this.getServletContext().setAttribute("properties", properties);
			is.close();
			logger.info("Loaded ProjectProperties ...");

		}
		catch (Exception e)
		{
			System.out.println("Error while reading ProjectProperties : " + e);
			e.printStackTrace();
		}
	}

	public static Properties getProperties()
	{
		return properties;
	}
	
}
