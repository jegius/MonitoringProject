<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="search" value="${requestScope.searchList}"/>
<div class="ui sticky jsSearchControlComponent">
    <div class="ui padded segment">
        <h3 class="ui center aligned icon header">
            <i class="circular search icon"></i>
            Управление Поиском
        </h3>
        <c:if test="${search.searchStatus != 0}">
            <div class="ui vertical menu">
                <div class="item">
                    Всего: <span class="ui label jsCommon">${search.productQuantity}</span>
                </div>
                <c:if test="${search.searchStatus > 1 && search.productQuantity > 0}">
                    <div class="item">
                        Найдено: <span class="ui label jsFind"></span>
                    </div>
                    <div class="item">
                        Процент: <span class="ui label jsPercent"></span>
                    </div>

                </c:if>
                <div class="item">
                    <select class="ui dropdown  jsStoreSelect fluid">
                        <c:forEach items="${requestScope.stores}" var="store">
                            <option value="${store.id}">${store.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <c:if test="${search.searchStatus == 1}">
                <button class="ui blue labeled icon button jsFindProducts">
                    <i class="right arrow icon"></i>
                    Начать поиск
                </button>
            </c:if>
            <c:if test="${search.searchStatus == 2}">
                <button class="ui green right labeled icon button jsFindProducts">
                    <i class="Repeat icon"></i>
                    Повторный поиск
                </button>
            </c:if>
        </c:if>

    </div>
</div>
<script type="text/javascript">
    window.frm.components.init('SearchControlComponent', '.jsSearchControlComponent', {
        contextPath: '${pageContext.request.contextPath}'
    });
</script>