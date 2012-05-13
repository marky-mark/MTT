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
            <h1>Welcome '${user.username}'</h1>

            <h2>Your current tasks</h2>
            <ul>
                <c:forEach var="task" items="${tasks}">
                    <div class="border-control">
                        <li>
                            <ul>
                                <input type="checkbox" value="${task.checked}"/> ${task.createdDate} <h2 class="title">${task.title}</h2>
                                <div class="description">${task.description}</div>
                                <form class="delete" action="/dashboard" method="POST">
                                    <input type="hidden" name="taskId" value="${task.id}"/>
                                    <input type="submit" class="delete-button" id="delete-task-button" name="delete-task" value="Delete" />
                                </form>
                            </ul>
                        </li>
                    </div>
                </c:forEach>
            </ul>

            <div id="create-task">
                <form action="/dashboard" method="POST">
                    <p>Title: <input type="text" id="title" name="title" size="${titleSize}"/></p>
                    <p id="description-text">Description: <textarea name="description" cols="40" rows="5"></textarea></p>
                    <%--<p>Description: <input type="text" id="description" name="description" size="${descSize}"/></p>--%>
                    <input type="submit" id="create-task-button" name="create-task" value="Create task" />
                </form>
            </div>
		</div>
	</div>
 	<div id="footer"><h1>Developed by Mark Kelly</h1></div>
</body>
</html>
