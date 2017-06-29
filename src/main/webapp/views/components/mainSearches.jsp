<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="ui text container jsSearchComponent">
    <h2 class="ui center aligned icon header">
        <i class="circular search icon"></i>
        Файлы для мониторинга:
    </h2>
    <div class="ui middle aligned three column centered grid  segment jsSearchItemContainer">
        <c:forEach items="${requestScope.searchList}" var="searchItem">
            <div class="column jsSearchItem">
                <div class="ui center aligned segment">
                    <p>Загружен: <b>24.06.2017</b></p>
                    <p>Последний поиск: <b>24.06.2017</b></p>
                    <p>Всего единиц: <b>36</b></p>
                    <button class="ui fluid animated primary button">
                        <div class="visible content">Перейти</div>
                        <div class="hidden content">
                            <i class="right arrow icon"></i>
                        </div>
                    </button>
                </div>
            </div>
        </c:forEach>
        <%--<div class="column jsSearchItem">--%>
            <%--<div class="ui center aligned segment">--%>
                <%--<p>Загружен: <b>24.06.2017</b></p>--%>
                <%--<p>Поиск еще не осуществлялся</p>--%>
                <%--<button class="ui fluid animated primary button">--%>
                    <%--<div class="visible content">Перейти</div>--%>
                    <%--<div class="hidden content">--%>
                        <%--<i class="right arrow icon"></i>--%>
                    <%--</div>--%>
                <%--</button>--%>
            <%--</div>--%>
        <%--</div>--%>
        <div class="column jsSearchItem">
            <div class="ui center aligned segment">
                <button class="circular ui massive blue icon button jsOpenModal">
                    <i class="icon plus"></i>
                </button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    window.frm.components.init('SearchComponent', '.jsSearchComponent', {
        addUserUrl: '${pageContext.request.contextPath}'
    });
</script>