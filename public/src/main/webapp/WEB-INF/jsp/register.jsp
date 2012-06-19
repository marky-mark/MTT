<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="jawr" uri="http://jawr.net/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="taskComponent" %>
<%@ taglib uri="http://www.mtt.com/errors" prefix="e" %>
<%@ taglib uri="http://www.mtt.com/session" prefix="s" %>

<form action="/register" method="post" id="register-form" autocomplete="off">

    <div class="text-field">
        First name <input type="text" id="firstName" name="firstName" value="${registerUserBean.firstName}" />
        <span id="firstName-error" class="error"><c:if test="${e:hasError(errors, 'firstName')}">${e:getError(errors,'firstName')}</c:if></span>
    </div>

    <div class="text-field">
        Last name <input type="text" id="lastName" name="lastName" value="${registerUserBean.lastName}" />
        <span id="lastName-error" class="error"><c:if test="${e:hasError(errors, 'lastName')}">${e:getError(errors,'lastName')}</c:if></span>
    </div>

    <div class="text-field">
        Email Address <input type="text" id="emailAddress" name="emailAddress" value="${registerUserBean.emailAddress}" />
        <span id="emailAddress-error" class="error"><c:if test="${e:hasError(errors, 'emailAddress')}">${e:getError(errors,'emailAddress')}</c:if></span>
    </div>

    <div class="text-field">
        Confirm email address<input type="text" id="confirmedEmailAddress" name="confirmedEmailAddress" value="${registerUserBean.confirmedEmailAddress}" />
        <span id="confirmedEmailAddress-error" class="error"><c:if test="${e:hasError(errors, 'confirmedEmailAddress')}">${e:getError(errors,'confirmedEmailAddress')}</c:if></span>
    </div>

    <div class="text-field">
        Telephone number <input type="text" id="telephoneNumber" name="telephoneNumber" value="${registerUserBean.telephoneNumber}" />
        <span id="telephoneNumber-error" class="error"><c:if test="${e:hasError(errors, 'telephoneNumber')}">${e:getError(errors,'telephoneNumber')}</c:if></span>
    </div>

    <div class="text-field">
        Password <input type="password" id="password" name="password" value="${registerUserBean.password}" />
        <span id="password-error" class="error"><c:if test="${e:hasError(errors, 'password')}">${e:getError(errors,'password')}</c:if></span>
    </div>

    <div class="text-field">
        Confirm password<input type="password" id="confirmedPassword" name="confirmedPassword" value="${registerUserBean.confirmedPassword}" />
        <span id="confirmedPassword-error" class="error"><c:if test="${e:hasError(errors, 'confirmedPassword')}">${e:getError(errors,'confirmedPassword')}</c:if></span>
    </div>

    <p><input type="submit" id="register-user-button" name="register-user" value="Register" /></p>
</form>