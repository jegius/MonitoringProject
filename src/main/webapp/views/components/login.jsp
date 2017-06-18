<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="ui container jsLoginComponent main-content">
    <div class="ui three column centered grid">
        <div class="column">
            <h2 class="ui center aligned icon header">
                <i class="circular sign in icon"></i>
                Войти в систему
            </h2>
            <form class="ui equal dividing width form" action="${pageContext.request.contextPath}/login" method="post">
                <div class="field">
                    <label> Введите имя пользователя: </label>
                    <input type="text" name="name" placeholder="Иия Пользователя">
                </div>
                <div class="field">
                    <label> Введите Пароль: </label>
                    <input type="password" name="password" placeholder="Пароль">
                </div>
                <div class="ui two column centered grid">
                    <div class="column">
                        <button class="ui fluid animated primary button" type="submit">
                            <div class="visible content">Login</div>
                            <div class="hidden content">
                                <i class="right arrow icon"></i>
                            </div>
                        </button>
                    </div>
                </div>
            </form>
            <div class="ui hidden divider"></div>
            <c:set var="request" scope="session" value="${requestScope.answer}"/>
            <c:if test="${request != null}">
                <div class="ui message warning">
                    <p>${requestScope.answer}</p>
                </div>
            </c:if>
        </div>
    </div>
</div>
<script>
    window.frm.components.init('LoginComponent', '.jsLoginComponent');
</script>