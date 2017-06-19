<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ru.ncedu.ecomm.utils.JSONUtils" %>

<div class="ui text container jsUserTable">
    <div class="ui sizer vertical segment">
        <div class="ui center aligned medium header">Все пользователи:</div>
    </div>
    <table class="ui compact celled definition table">
        <thead>
        <tr>
            <th></th>
            <th>ID</th>
            <th>Имя пользователя</th>
            <th>Роль</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.viewModelUser}" var="user">
        <tr>
            <td class="collapsing">
                <button class="circular ui icon button jsRemoveUser" value="${user.id}">
                    <i class="red remove icon"></i>
                </button>
            </td>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.role}</td>
        </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<%-- JS controller initilization --%>
<script type="text/javascript">
    window.frm.components.init('UserTable', '.jsUserTable', {
        userTableUrl: '${pageContext.request.contextPath}'
    });
</script>