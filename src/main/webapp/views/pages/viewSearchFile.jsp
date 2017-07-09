<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Просмотр листа мониторинга</title>
    <c:import url="../fragments/head.jsp"/>
</head>
<body>
<c:import url="../fragments/topPanel.jsp"/>

<div class="ui container jsContent" style="min-height: 10em">

    <div class="ui segment" id="searchBody">
        <div class="right ui rail">
            <c:import url="../components/block/searchControlComponent.jsp"/>
        </div>
        <c:import url="../components/searchFileView.jsp"/>
    </div>
    <div class="ui container" style="min-height: 10em"></div>
</div>
<div class="ui container" style="min-height: 10em"></div>
</body>
</html>