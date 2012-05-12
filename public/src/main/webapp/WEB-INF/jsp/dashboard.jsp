<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>Task Dashboard</title></head>
<body>
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
</body>
</html>
