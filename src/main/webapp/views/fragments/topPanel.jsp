<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="ui fixed menu">
    <div class="ui container">
        <span class="header item">MonTool</span>
        <a href="${pageContext.request.contextPath}/home" class="item"><i class="home icon"></i>Главная страница</a>
        <c:if test="${sessionScope.user.roleId == 1}">
            <a href="${pageContext.request.contextPath}/management" class="item"><i class="users icon"></i>Управление
                пользователями</a>
        </c:if>
        <c:import url="../components/block/userInformation.jsp"/>
        <a href="${pageContext.request.contextPath}/logout" class="ui item right"><b><i
                class="sign out icon"></i>Выйти</b></a>
    </div>
</div>