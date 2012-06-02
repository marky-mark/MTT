<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://jawr.net/tags" prefix="jawr" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<%--Required--%>
<tiles:useAttribute id="headerTitle" name="headerTitle" classname="java.lang.String" />
<tiles:useAttribute id="pageCss" name="pageCss" classname="java.util.List" />
<tiles:useAttribute id="pageHeadScript" name="pageHeadScript" classname="java.util.List" />

<%--optional--%>
<tiles:useAttribute id="pageMetaRobots" name="pageMetaRobots" classname="java.lang.String" ignore="true" />

<!DOCTYPE html>
<html>
<head>
    <title>${headerTitle}</title>
    <jawr:style src="/bundles/mtt.css"/>
    <jawr:script src="/bundles/mtt.js"/>
</head>
<div id="container">
    <div id="banner">
        <div id='bannertitle'>MTT Task Test</div>
    </div>

    <div id="content">
        <h1>Welcome to the MTT Test</h1>