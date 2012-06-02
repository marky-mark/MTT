<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<tiles:insertAttribute name="header" />
<div id="main" role="main" class="content-block">
    <tiles:insertAttribute name="body" />
</div>
<tiles:insertAttribute name="footer" />