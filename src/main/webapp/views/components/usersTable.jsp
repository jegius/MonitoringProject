<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="ui text container jsUserTableComponent">
    <div class="ui sizer vertical segment">
        <div class="ui center aligned medium header">Все пользователи:</div>
    </div>
    <table class="ui celled definition table jsUserTable">
        <thead>
        <tr>
            <th></th>
            <th>ID</th>
            <th>Имя пользователя</th>
            <th>Роль</th>
            <th>Сменить пароль</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.viewModelUser}" var="user">
            <c:if test="${sessionScope.user.id != user.id}">
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
                    <td>
                        <div class="ui action input jsChangePasswordContainer">
                            <input value="${user.id}" type="hidden" class="userIdForChangePassword">
                            <input type="text" class="jsNewPassword" placeholder="Введите пароль">
                            <button class="ui button blue jsChangePassword"><i class="edit icon"></i>Сменить</button>
                        </div>
                    </td>
                </tr>
            </c:if>
            <c:if test="${sessionScope.user.id == user.id}">
                <tr class="jsUserInfoRow">
                    <td class="collapsing">
                        Вы
                    </td>
                    <td class="jsUserId">${user.id}</td>
                    <td class="jsUserName">${user.name}</td>
                    <td>
                        ${user.role}
                    </td>

                    <td>
                        <div class="ui action input jsChangePassword">
                            <input value="${user.id}" type="hidden" class="userId">
                            <input type="text" placeholder="Введите пароль">
                            <button class="ui button blue"><i class="edit icon"></i>Сменить</button>
                        </div>
                    </td>
                </tr>
            </c:if>
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