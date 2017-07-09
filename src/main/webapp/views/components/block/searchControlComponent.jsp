<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="search" value="${requestScope.searchList}"/>
<div class="ui sticky jsSearchControlComponent">
    <div class="ui padded segment">
        <h3 class="ui center aligned icon header">
            <i class="circular search icon"></i>
            Управление Поиском
        </h3>
        <div class="ui vertical menu">
            <div class="item">
                Всего: <span class="ui label">${search.productQuantity}</span>
            </div>
            <c:if test="${search.searchStatus > 1 && search.productQuantity > 0}">
                <div class="item">
                    Найдено: <span class="ui label jsFind">2</span>
                </div>
                <div class="item">
                    Процент: <span class="ui label jsPercent">2</span>
                </div>

            </c:if>
        </div>
        <c:if test="${search.searchStatus == 1}">
            <button class="ui blue labeled icon button">
                <i class="right arrow icon"></i>
                Начать поиск
            </button>
        </c:if>
        <c:if test="${search.searchStatus == 2}">
            <button class="ui green right labeled icon button">
                <i class="Repeat icon"></i>
                Повторный поиск
            </button>
        </c:if>

    </div>
</div>
<script type="text/javascript">
    window.frm.components.init('SearchControlComponent', '.jsSearchControlComponent');
</script>