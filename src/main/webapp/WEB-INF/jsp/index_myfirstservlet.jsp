<%--
  Created by IntelliJ IDEA.
  User: Артем
  Date: 19.12.2016
  Time: 12:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>My first</title>
  </head>
  <body>
  <head>
    <h1>Привет, <%= request.getParameter("name") == null ? "Мир" : request.getParameter("name") %></h1>
  </head>
  </body>
</html>
