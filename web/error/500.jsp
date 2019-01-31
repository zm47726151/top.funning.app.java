<%--
  Created by IntelliJ IDEA.
  User: faddenyin
  Date: 2019-03-01
  Time: 13:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" isErrorPage="true" %>
<%@ page import="java.io.*" %>
<html>
<header>
    <title>exception page</title>
    <body>
    <hr/>
    <pre>
            <%
response.getWriter().println("Exception: " + exception);

if(exception != null)
{
   response.getWriter().println("<pre>");
   exception.printStackTrace(response.getWriter());
   response.getWriter().println("</pre>");
}

response.getWriter().println("<hr/>");
%>
