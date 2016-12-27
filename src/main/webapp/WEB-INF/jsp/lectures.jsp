
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Lectures</title>
</head>
<body>
<h4><a href="index.jsp">Start page</a></h4>
<h2>Lectures list</h2>
<a href="lectures?action=create">Add Lecture</a>
<table border="1" cellpadding="8" cellspacing="0">
    <thead>
    <tr>
        <th>Topic</th>
        <th>Duration</th>
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
    <c:forEach items="${lectures}" var="lecture">
        <jsp:useBean id="lecture" scope="page" type="ru.innopolis.uni.course3.model.Lecture"/>
        <tr>
            <td>${lecture.getTopic()}</td>
            <td>${lecture.getDuration()}</td>
            <c:choose>
                <c:when test="${journalId == null}">
                    <td><a href="lectures?action=update&id=${lecture.getId()}">Update</a></td>
                    <td><a href="lectures?action=delete&id=${lecture.getId()}">Delete</a></td>
                </c:when>
                <c:otherwise>
                    <td><a href="journal?action=update&id=${journalId}&lectureId=${lecture.getId()}&lectureRepresentation=${lecture.getRepresentation()}&studentId=${studentId}&studentRepresentation=${studentRepresentation}">Choose</a></td>
                </c:otherwise>
            </c:choose>


        </tr>
    </c:forEach>
</table>


</body>
</html>
