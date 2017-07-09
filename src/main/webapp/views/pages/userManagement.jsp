<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Управление пользователями</title>
    <c:import url="../fragments/head.jsp"/>
</head>
<body>
<c:import url="../fragments/topPanel.jsp"/>

<div class="ui container jsContent" style="min-height: 10em">
    <c:import url="../components/userManagement.jsp"/>
    <div class="ui container" style="min-height: 10em"></div>
    <c:import url="../components/usersTable.jsp"/>
</div>
<div class="ui container" style="min-height: 10em"></div>
</body>
</html>