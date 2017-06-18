<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="ui fixed menu">
    <div class="ui container">
        <span class="header item">MonTool</span>
        <a href="${pageContext.request.contextPath}/home" class="item">Главная страница</a>
        <c:if test="${sessionScope.roleId == 1}">
            <a href="${pageContext.request.contextPath}/management" class="item">Управление пользователями</a>
        </c:if>
    </div>
</div>