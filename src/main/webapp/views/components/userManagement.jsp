<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ru.ncedu.ecomm.utils.JSONUtils" %>

<div class="ui text container jsEtalonComponent">
    <h2 class="ui center aligned icon header">
        <i class="circular users icon"></i>
        Пользователи:
    </h2>
    <div class="ui form">
        <div class="three fields">
            <div class="field">
                <label>Роль:</label>
                <select class="ui dropdown">
                    <c:forEach items="${requestScope.roles}" var="role">
                        <option value="${role.id}">${role.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="field">
                <label>Имя пользователя:</label>
                <input type="text" placeholder="Введите имя">
            </div>
            <div class="field">
                <label>Пароль:</label>
                <input type="text" placeholder="Введите пароль">
            </div>
        </div>
        <div class="ui submit button">Добавить</div>
    </div>
</div>

<%-- JS controller initilization --%>
<script type="text/javascript">
    window.frm.components.init('EtalonComponent', '.jsEtalonComponent', {
        // Roles should be converted to JSON, in order to be handled as a JS object
        roles: <%= JSONUtils.toJSON(request.getAttribute("roles")) %>
    });
</script>