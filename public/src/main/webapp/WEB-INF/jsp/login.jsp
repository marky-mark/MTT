<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="jawr" uri="http://jawr.net/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>MTT Login</title>
    <jawr:style src="/bundles/mtt.css"/>
    <jawr:script src="/bundles/mtt.js"/>
</head>
<body>
    <div id="container">
        <div id="banner">
            <div id='bannertitle'>MTT Task Test</div>
        </div>

        <div id="content">
            <h1>Welcome to the MTT Test</h1>

            <h2>Please Login</h2>

            <div id="login">
                <form class="update" action="/login" method="POST">
                    <p>Username <input type="text" id="username" name="username" name="title" size="${titleSize}"/></p>
                    <c:if test="${not empty userNameError}"><label color="#9A1212">${userNameError}</label></c:if>
                    <p>Password <input type="password" id="password" name="password" name="title" size="${titleSize}"/></p>
                    <c:if test="${not empty passwordError}"><label color="#9A1212">${passwordError}</label></c:if>
                    <input name="rememberMe" type="checkbox" value="true" /><label class="remember-me">Remember me</label>
                    <p><input type="submit" value="login" id="login-button" name="login" /></p>
                </form>
            </div>

		</div>
	</div>
 	<div id="footer"><h1>Developed by Mark Kelly</h1></div>
</body>
</html>
