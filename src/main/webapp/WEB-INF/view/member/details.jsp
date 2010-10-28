
<%@page import="ikriti.natgeo.hb.Member"%><%
	Member member = (Member) request.getAttribute("member");
%>
Hello World, <%= member.getFirstname() %>