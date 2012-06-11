<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="jawr" uri="http://jawr.net/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="taskComponent" %>
<%@ taglib uri="http://www.mtt.com/errors" prefix="e" %>
<%@ taglib uri="http://www.mtt.com/session" prefix="s" %>

<form action="/register" method="post" id="register-form" autocomplete="off">

    <p>First name <input type="text" id="firstName" name="firstName" value="${registerUserBean.firstName}" /></p>
    <span class="error"><c:if test="${e:hasError(errors, 'firstName')}">${e:getError(errors,'firstName')}</c:if></span>

    <p>Last name <input type="text" id="lastName" name="lastName" value="${registerUserBean.lastName}" /></p>
    <span class="error"><c:if test="${e:hasError(errors, 'lastName')}">${e:getError(errors,'lastName')}</c:if></span>

    <p>Email Address <input type="text" id="emailAddress" name="emailAddress" value="${registerUserBean.emailAddress}" /></p>
    <span class="error"><c:if test="${e:hasError(errors, 'emailAddress')}">${e:getError(errors,'emailAddress')}</c:if></span>

    <p>Confirm email address<input type="text" id="confirmedEmailAddress" name="confirmedEmailAddress" value="${registerUserBean.confirmedEmailAddress}" /></p>
    <span class="error"><c:if test="${e:hasError(errors, 'confirmedEmailAddress')}">${e:getError(errors,'confirmedEmailAddress')}</c:if></span>

    <p>Telephone number <input type="text" id="telephoneNumber" name="telephoneNumber" value="${registerUserBean.telephoneNumber}" /></p>
    <span class="error"><c:if test="${e:hasError(errors, 'telephoneNumber')}">${e:getError(errors,'telephoneNumber')}</c:if></span>

    <p>Password <input type="password" id="password" name="password" value="${registerUserBean.password}" /></p>
    <span class="error"><c:if test="${e:hasError(errors, 'password')}">${e:getError(errors,'password')}</c:if></span>

    <p>Confirm password<input type="password" id="confirmedPassword" name="confirmedPassword" value="${registerUserBean.confirmedPassword}" /></p>
    <span class="error"><c:if test="${e:hasError(errors, 'confirmedPassword')}">${e:getError(errors,'confirmedPassword')}</c:if></span>

    <p><input type="submit" id="register-user-button" name="register-user" value="Register" /></p>
</form>