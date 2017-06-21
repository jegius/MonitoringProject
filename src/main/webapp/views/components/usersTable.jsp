<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="ui text container jsUserTableComponent">
    <div class="ui sizer vertical segment">
        <div class="ui center aligned medium header">Все пользователи:</div>
    </div>
    <table class="ui compact celled definition table jsUserTable">
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
            <tr class="jsUserInfoRow">
                <td class="collapsing">
                    <button class="circular ui icon button jsRemoveUser" value="${user.id}">
                        <i class="red remove icon"></i>
                    </button>
                </td>
                <td class="jsUserId">${user.id}</td>
                <td class="jsUserName">${user.name}</td>
                <td>
                    <select class="ui dropdown jsAddUserDropdown jsRoleId">
                        <c:forEach items="${requestScope.roles}" var="role">
                            <option <c:if test="${role.name == user.role}"> selected </c:if>
                                    value="${role.id}">${role.name}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<%-- JS controller initilization --%>
<script type="text/javascript">
    window.frm.components.init('UserTableComponent', '.jsUserTableComponent', {
        userTableUrl: '${pageContext.request.contextPath}'
    });
</script>