
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Journal</title>
</head>
<body>
<h4><a href="index.jsp">Start page</a></h4>
<h2>Journal</h2>
<a href="journal?action=create">Add Journal Record</a>
<table border="1" cellpadding="8" cellspacing="0">
    <thead>
    <tr>
        <th>Student</th>
        <th>Lecture</th>
        <th>Date</th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <c:forEach items="${journal}" var="record">
        <jsp:useBean id="record" scope="page" type="ru.innopolis.uni.course3.model.Journal"/>
        <tr>
            <td>${record.student.getRepresentation()}</td>
            <td>${record.lecture.getRepresentation()}</td>
            <td>${record.date}</td>
            <td><a href="journal?action=update&id=${record.getId()}">Update</a></td>
            <td><a href="journal?action=delete&id=${record.getId()}">Delete</a></td>
        </tr>
    </c:forEach>
</table>


</body>
</html>
