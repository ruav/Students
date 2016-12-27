
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
    <h3>Edit lecture</h3>
    <hr>
    <jsp:useBean id="lecture" type="ru.innopolis.uni.course3.model.Lecture" scope="request"/>
    <form method="post" action="lectures">
        <input type="hidden" name="id" value="${lecture.getId()}">
        <dl>
            <dt>Topic:</dt>
            <dd><input type="text" value="${lecture.getTopic()}" size=40 name="topic"></dd>
        </dl>
        <dl>
            <dt>Duration:</dt>
            <dd><input type="number" value="${lecture.getDuration()}" name="duration"></dd>
        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()">Cancel</button>
    </form>
</section>
</body>
</html>
