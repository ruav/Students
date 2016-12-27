
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Student</title>
    <%--<link rel="stylesheet" href="style.css">--%>
    <style>
        dl {
            background: none repeat scroll 0 0 #FAFAFA;
            margin: 8px 0;
            padding: 0;
        }
        dt {
            display: inline-block;
            width: 170px;
        }
        dd {
            display: inline-block;
            margin-left: 8px;
            vertical-align: top;
        }
    </style>
</head>
<body>
<section>
    <h4><a href="${pageContext.request.contextPath}/index.jsp">Start page</a></h4>
    <h3>Edit student</h3>
    <hr>
    <jsp:useBean id="student" type="ru.innopolis.uni.course3.model.Student" scope="request"/>
    <form method="post" action="students">
        <input type="hidden" name="id" value="${student.getId()}">
        <dl>
            <dt>Name:</dt>
            <dd><input type="text" value="${student.getName()}" size=40 name="name"></dd>
        </dl>
        <dl>
            <dt>Surname:</dt>
            <dd><input type="text" value="${student.getSurname()}" size=40 name="surname"></dd>
        </dl>
        <dl>
            <dt>Sex:</dt>
            <dd><input type="text" value="${student.getSex()}" size=10 name="sex"></dd>
        </dl>
        <dl>
            <dt>Group number:</dt>
            <dd><input type="number" value="${student.getGroupNumber()}" name="groupNumber"></dd>
        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()">Cancel</button>
    </form>
</section>
</body>
</html>
