<!DOCTYPE html>
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
                <p>Username <input type="text" id="username" name="title" size="${titleSize}"/></p>
                <p>Password <input type="password" id="password" name="title" size="${titleSize}"/></p>
            </div>

		</div>
	</div>
 	<div id="footer"><h1>Developed by Mark Kelly</h1></div>
</body>
</html>
