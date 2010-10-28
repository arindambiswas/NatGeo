<%@ page import="com.adobe.services.RemotingServiceBrowser" %>
<%
		response.setContentType("text/xml");
		String classNames = request.getParameter("classNames");
		String operationName = request.getParameter("operationName");

		if(operationName != null)
		{
			RemotingServiceBrowser browser = new RemotingServiceBrowser();
	
			if(operationName.equalsIgnoreCase("getdestinations"))
			{
				out.println(browser.getDestinations("_messageBroker"));
			}
			else if(operationName.equalsIgnoreCase("getpropertiesforclasses"))
			{
				String[] classNamesArr = null;

				if(classNames != null)
				{
					classNamesArr = classNames.split(",");
				}		
				out.println(browser.getPublicPropertiesForClasses(
					classNamesArr));
			}
			else if(operationName.equalsIgnoreCase("getmethodsforclasses"))
			{
				String[] classNamesArr = null;

				if(classNames != null)
				{
					classNamesArr = classNames.split(",");
				}		
				out.println(browser.getPublicMethodsForClasses(
					classNamesArr));
			}
		}
%>