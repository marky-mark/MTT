<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="jawr" uri="http://jawr.net/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.mtt.com/errors" prefix="e" %>
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

        <div id="registration">
            <form action="/register" method="GET">
                 <span>Not registered? Please register here</span>
                <input type="submit" value="register" name="register"/>
            </form>
        </div>

        <h2>Please Login</h2>

        <div>
            <form class="update" action="/login" method="POST">

                <fieldset>
                    <div class="text-field">
                        <label for="username">Username</label>
                        <input type="text" id="username" name="username" name="title" size="${titleSize}"/>
                        <div><span class="error"><c:if test="${e:hasError(errors, 'username')}">${e:getError(errors,'username')}</c:if></span></div>
                    </div>
                    <div class="text-field">
                        <label for="password">Password</label>
                        <input type="password" id="password" name="password" name="title" size="${titleSize}"/>
                        <div><span class="error"><c:if test="${e:hasError(errors, 'password')}">${e:getError(errors,'password')}</c:if></span></div>
                    </div>
                </fieldset>
                <div id="remember-me">
                    <input name="rememberMe" type="checkbox" value="true"/><label class="remember-me">Remember me</label>
                    <a href="/forgotten password">forgotten password?</a>
                </div><div class="submit-button">
                    <input type="submit" value="login" id="login-button" name="login"/>
                </div>
            </form>
        </div>

    </div>
</div>
<div id="footer"><h1>Developed by Mark Kelly</h1></div>
</body>
</html>
