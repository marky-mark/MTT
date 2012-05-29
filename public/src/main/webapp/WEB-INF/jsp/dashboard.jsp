<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="jawr" uri="http://jawr.net/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Task Dashboard</title>
    <jawr:style src="/bundles/mtt.css"/>
    <jawr:script src="/bundles/mtt.js"/>
</head>
<body>
    <div id="container">
        <div id="banner">
            <div id='bannertitle'>MTT Task Test</div>
        </div>

        <div id="content">
            <h1>Welcome '${user.username}'</h1>

            <a id="logoutLink" href="${logoutLink}">Logout</a>

            <h2>Create a new task</h2>

            <%--Create a new Task--%>
            <div id="create-task">
                <form action="/dashboard" method="POST">
                    <p>Title <input type="text" id="title" name="title" size="${titleSize}"/></p>
                    <p id="description-text">Description <textarea name="description" cols="40" rows="5"></textarea></p>
                    <input type="submit" id="create-task-button" name="create-task" value="Create task" />
                </form>
            </div>

            <h2>Your current tasks</h2>

            <ul>
                <%--List the current Tasks--%>
                <c:forEach var="task" items="${tasks}">
                    <div class="border-control">
                        <li>
                            <ul>
                                <%--Display a Task--%>
                                <div id="text-task-${task.id}">
                                    <form action="/dashboard" method="POST" >
                                        <input type="hidden" name="taskId" value="${task.id}"/>
                                        <input name="checked" type="checkbox" value="true" <c:if test="${task.checked == true}">checked="checked" </c:if> onclick="this.form.submit();" />
                                    </form>
                                    <div  onclick="editTask(${task.id})">
                                        ${task.createdDate} <h2 class="title">${task.title}</h2>
                                        <div class="description">${task.description}</div>
                                        <form class="delete" action="/dashboard" method="POST">
                                            <input type="hidden" name="taskId" value="${task.id}"/>
                                            <input type="submit" class="delete-button" id="delete-task-button" name="delete-task" value="Delete" />
                                        </form>
                                    </div>
                                </div>

                                <%--Edit a Task--%>
                                <div id="text-task-${task.id}-edit" hidden="true">
                                    <form class="update" action="/dashboard" method="POST">
                                        <input name="checked" type="checkbox" value="true" <c:if test="${task.checked == true}">checked="checked"</c:if> />
                                        ${task.createdDate} <p>Title <input type="text" id="title" name="title" size="${titleSize}" value="${task.title}"></p>
                                        <p>Description <textarea name="description" cols="40" rows="5">${task.description}</textarea></p>
                                        <input type="hidden" name="id" value="${task.id}"/>
                                        <input type="submit" class="update-button" id="update-task-button" name="update-task" value="Update" />
                                    </form>
                                    <input type="submit" class="cancel-button" name="cancel-task" value= "Cancel" onclick="unEditTask(${task.id})" />
                                    <form class="delete" action="/dashboard" method="POST">
                                        <input type="hidden" name="taskId" value="${task.id}"/>
                                        <input type="submit" class="delete-button" id="delete-task-button" name="delete-task" value="Delete" />
                                    </form>
                                </div>
                            </ul>
                        </li>
                    </div>
                </c:forEach>
            </ul>

		</div>
	</div>
 	<div id="footer"><h1>Developed by Mark Kelly</h1></div>
</body>
</html>
