<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.mtt.com/errors" prefix="e" %>

<%@ attribute name="id" required="true" type="java.lang.Integer" %>
<%@ attribute name="titleSize" required="true" type="java.lang.Integer" %>
<%@ attribute name="isChecked" required="true" type="java.lang.Boolean" %>
<%@ attribute name="description" required="true" type="java.lang.String" %>
<%@ attribute name="title" required="true" type="java.lang.String" %>
<%@ attribute name="date" required="true" type="java.lang.String" %>
<%@ attribute name="errors" required="false" type="java.lang.Object" %>

<%--Display a Task--%>
<div id="text-task-${id}">
    <form action="/dashboard" method="POST" >
        <input type="hidden" name="taskId" value="${id}"/>
        <input name="checked" type="checkbox" value="true" <c:if test="${isChecked == true}">checked="checked" </c:if> onclick="this.form.submit();" />
    </form>
    <div  onclick="editTask(${id})">
        ${date} <h2 class="title">${title}</h2>
        <div class="description">${description}</div>
        <form class="delete" action="/dashboard" method="POST">
            <input type="hidden" name="taskId" value="${id}"/>
            <input type="submit" class="delete-button" id="delete-task-button" name="delete-task" value="Delete" />
        </form>
    </div>
</div>

<%--Edit a Task--%>
<div id="text-task-${id}-edit" hidden="true">
    <form class="update" action="/dashboard" method="POST">
        <input name="checked" type="checkbox" value="true" <c:if test="${isChecked== true}">checked="checked"</c:if> />
        ${date} <div>Title <input type="text" id="title" name="title" size="${titleSize}" value="${title}"></div>
        <div><span class="error"><c:if test="${e:hasErrorNumber(errors, 'title', id)}">${e:getErrorNumber(errors,'title', id)}</c:if></span></div>
        <p>Description <textarea name="description" cols="40" rows="5">${description}</textarea></p>
        <div><span class="error"><c:if test="${e:hasErrorNumber(errors, 'description', id)}">${e:getErrorNumber(errors,'description', id)}</c:if></span></div>
        <input type="hidden" name="id" value="${id}"/>
        <input type="submit" class="update-button" id="update-task-button" name="update-task" value="Update" />
    </form>
    <input type="submit" class="cancel-button" name="cancel-task" value= "Cancel" onclick="unEditTask(${id})" />
    <form class="delete" action="/dashboard" method="POST">
        <input type="hidden" name="taskId" value="${id}"/>
        <input type="submit" class="delete-button" id="delete-task-button" name="delete-task" value="Delete" />
    </form>
</div>