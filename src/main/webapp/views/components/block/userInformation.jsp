<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="user" scope="session" value="${sessionScope.user}"/>
<div class="ui item inverted right jsUserInformation">
    <p>Добро пожаловать, <b>${user.name}</b>!</p>
</div>
<script type="text/javascript">
    window.frm.components.init('UserInformation', '.jsUserInformation');
</script>