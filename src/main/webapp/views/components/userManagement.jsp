<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ru.ncedu.ecomm.utils.JSONUtils" %>

<div class="ui text container jsUserManagement">
    <h2 class="ui center aligned icon header">
        <i class="circular users icon"></i>
        Пользователи:
    </h2>
    <div class="ui form jsAddUserForm">
        <div class="three fields">
            <div class="field">
                <label>Роль:</label>
                <select class="ui dropdown jsAddUserDropdown jsRoleId">
                    <c:forEach items="${requestScope.roles}" var="role">
                        <option value="${role.id}">${role.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="field">
                <label>Имя пользователя:</label>
                <input type="text" placeholder="Введите имя" class="jsUserName">
            </div>
            <div class="field">
                <label>Пароль:</label>
                <input type="text" placeholder="Введите пароль" class="jsPassword">
            </div>
        </div>
        <div class="ui green submit button jsAddUser">Добавить</div>
    </div>
</div>

<%-- JS controller initilization --%>
<script type="text/javascript">
    window.frm.components.init('UserManagement', '.jsUserManagement', {
        // Roles should be converted to JSON, in order to be handled as a JS object
        addUserUrl: '${pageContext.request.contextPath}'
    });
</script>