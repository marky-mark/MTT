<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="jawr" uri="http://jawr.net/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="taskComponent" %>
<%@ taglib uri="http://www.mtt.com/errors" prefix="e" %>

<div id="content">
    <h1>Welcome '${user.username}'</h1>

    <a id="logoutLink" href="${logoutLink}">Logout</a>

    <h2>Create a new task</h2>

    <%--Create a new Task--%>
    <div id="create-task">
        <form action="/dashboard" method="POST">
            <p>Title <input type="text" id="title" name="title" size="${titleSize}"/></p>
            <span class="error"><c:if test="${e:hasError(errors, 'title')}">${e:getError(errors,'title')}</c:if></span>
            <p id="description-text">Description <textarea name="description" cols="40" rows="5"></textarea></p>
             <div><span class="error"><c:if test="${e:hasError(errors, 'description')}">${e:getError(errors,'description')}</c:if></span></div>
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
                        <taskComponent:displayTaskComponent
                                id="${task.id}"
                                title="${task.title}"
                                description="${task.description}"
                                isChecked="${task.checked}"
                                date="${task.createdDate}"
                                titleSize="${titleSize}"
                                errors="${errors}"/>

                    </ul>
                </li>
            </div>
        </c:forEach>
    </ul>

</div>
