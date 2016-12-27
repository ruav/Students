
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Lecture</title>
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
    <h4><a href="index.jsp">Start page</a></h4>
    <h3>Edit journal</h3>
    <hr>
    <jsp:useBean id="journal" type="ru.innopolis.uni.course3.model.Journal" scope="request"/>
    <c:set var="sId" scope="page" value="${studentId != null ? studentId : journal.getStudent().getId()}"/>
    <c:set var="lId" scope="page" value="${lectureId != null ? lectureId : journal.getLecture().getId()}"/>
    <c:set var="srepr" scope="page" value="${studentRepresentation != null ? studentRepresentation : journal.getStudent().getRepresentation()}"/>
    <c:set var="lrepr" scope="page" value="${lectureRepresentation != null ? lectureRepresentation : journal.getLecture().getRepresentation()}"/>
    <form method="post" action="journal">
        <input type="hidden" name="id" value="${journal.getId()}">
        <dl>
            <dt>Student:</dt>
            <dd><input type="text" value="${srepr}" size=40 name="student" disabled></dd>
            <dd><a href="students?action=chooseStudent&journalId=${journal.id}&lectureId=${lId}&lectureRepresentation=${lrepr}">Change</a></dd>
            <dd><input type="hidden" name="studentId" value="${sId}"></dd>
        </dl>
        <dl>
            <dt>Lecture:</dt>
            <dd><input type="text" value="${lrepr}" size=40 name="lecture" disabled></dd>
            <dd><a href="lectures?action=chooseLecture&journalId=${journal.id}&studentId=${sId}&studentRepresentation=${srepr}">Change</a></dd>
            <dd><input type="hidden" name="lectureId" value="${lId}"></dd>
        </dl>
        <dl>
            <dt>Date:</dt>
            <dd><input type="date" value="${journal.date}" name="date"></dd>
        </dl>
        <button type="submit">Save</button>
        <button type="button" onclick="window.history.back()">Cancel</button>
    </form>
</section>
</body>
</html>
