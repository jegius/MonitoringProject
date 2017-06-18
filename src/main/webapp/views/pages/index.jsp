<%--
    Used fragments:
    - head
    - topPanel

    Used components:
    - productList
    - companyInfo
--%>

<%@ page contentType="text/html;charset=utf-8"  pageEncoding="utf-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Главная страница</title>
    <c:import url="../fragments/head.jsp"/>
</head>
<body>

    <c:import url="../fragments/topPanel.jsp"/>
    <div class="ui container" style="height: 10em"></div>
    <c:import url="../components/etalon.jsp">
        <c:param name="showCheckbox" value="true"/>
    </c:import>

    <c:import url="../components/companyInfo.jsp"/>

</body>
</html>