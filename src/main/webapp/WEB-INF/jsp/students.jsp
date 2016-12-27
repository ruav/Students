
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Students</title>
</head>
<body>
<h4><a href="index.jsp">Start page</a></h4>
<h2>Students list</h2>
<a href="students/create/new">Add Student</a>
<table border="1" cellpadding="8" cellspacing="0">
    <thead>
    <tr>
        <th>Name</th>
        <th>Surname</th>
        <th>Sex</th>
        <th>Group number</th>
        <c:choose>
            <c:when test="${journalId == null}">
                <th></th>
                <th></th>
            </c:when>
            <c:otherwise>
                <th></th>
            </c:otherwise>
        </c:choose>
    </tr>
    </thead>
    <c:forEach items="${students}" var="student">
        <jsp:useBean id="student" scope="page" type="ru.innopolis.uni.course3.model.Student"/>
        <tr>
            <td>${student.getName()}</td>
            <td>${student.getSurname()}</td>
            <td>${student.getSex()}</td>
            <td>${student.getGroupNumber()}</td>
            <c:choose>
                <c:when test="${journalId == null}">
                    <td><a href="students/update/${student.getId()}">Update</a></td>
                    <td><a href="students/delete/${student.getId()}">Delete</a></td>
                </c:when>
                <c:otherwise>
                    <td><a href="journal?action=update&id=${journalId}&studentId=${student.getId()}&studentRepresentation=${student.getRepresentation()}&lectureId=${lectureId}&lectureRepresentation=${lectureRepresentation}">Choose</a></td>
                </c:otherwise>
            </c:choose>
        </tr>
    </c:forEach>
</table>
</body>
</html>
