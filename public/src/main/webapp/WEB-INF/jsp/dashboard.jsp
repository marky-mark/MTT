<!DOCTYPE html>
<%@ taglib prefix="jawr" uri="http://jawr.net/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Task Dashboard</title>
    <jawr:style src="/bundles/mtt.css"/>
</head>
<body>
    <div id="container">
        <div id="banner">
            <div id='bannertitle'>MTT Task Test</div>
        </div>

        <div id="content">
            <h1>hello ${user.username}</h1>

            <h2>Your current tasks</h2>
            <ul>
                <c:forEach var="task" items="${tasks}">
                    <li><input type="checkbox" value="${task.checked}"/> : ${task.createdDate} : ${task.title} : ${task.description}</li>
                </c:forEach>
            </ul>
            <form action="/dashboard" method="POST">
                <input type="text" name="title"/>
                <input type="text" name="description" />
                <input type="submit" name="create-task" value="Create task" />
            </form>
        </div>
    </div>
</body>
</html>
