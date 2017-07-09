<%--
    Used fragments:
    - head
    - topPanel

    Used components:
    - productList
    - companyInfo
--%>

<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Главная страница</title>
    <c:import url="../fragments/head.jsp"/>
</head>
<body>

<c:import url="../fragments/topPanel.jsp"/>
<div class="ui container jsContent" style="min-height: 10em">
    <c:import url="../components/mainSearches.jsp"/>
</div>
<div class="ui container" style="min-height: 10em"></div>
<c:import url="../components/block/errorMessage.jsp"/>
<c:import url="../components/block/addFileModal.jsp"/>
</body>
</html>