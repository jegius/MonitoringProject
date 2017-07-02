<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="ui text container jsSearchComponent">
    <h2 class="ui center aligned icon header">
        <i class="circular search icon"></i>
        Файлы для мониторинга:
    </h2>
    <div class="ui middle aligned three column centered grid  segment jsSearchItemContainer">
        <c:forEach items="${requestScope.searchList}" var="searchItem">
            <div class="column jsSearchItem">
                <div class="ui items center aligned segment">
                    <c:if test="${!searchItem.lastSearchDate}">
                        <div class="ui top attached label">
                            Поиск еще не осуществлялся.
                        </div>
                        <p><b>${searchItem.fileOriginalName}</b></p>
                        <p>Загружен: <b>${searchItem.creationDate}</b></p>
                    </c:if>
                    <c:if test="${searchItem.lastSearchDate}">
                        <div class="ui green top attached label">
                            Последний поиск: <b>${searchItem.lastSearchDate}</b>
                        </div>
                        <p><b>${searchItem.fileOriginalName}</b></p>
                        <p>Загружен:<b>${searchItem.creationDate}</b></p>
                        <p>Всего единиц:<b>${searchItem.productQuantity}</b></p>
                    </c:if>
                    <div class="item">
                        <button class="ui fluid animated primary button jsGoToSearch">
                            <div class="visible content">Перейти</div>
                            <div class="hidden content">
                                <i class="right arrow icon"></i>
                            </div>
                        </button>
                    </div>
                    <div class="item">
                        <button class="ui fluid red animated red button jsRemove"  value="${searchItem.id}">
                            <div class="visible content">Удалить</div>
                            <div class="hidden content">
                                <i class="right remove icon"></i>
                            </div>
                        </button>
                    </div>
                </div>
            </div>
        </c:forEach>
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
        mainSearch: '${pageContext.request.contextPath}'
    });
</script>