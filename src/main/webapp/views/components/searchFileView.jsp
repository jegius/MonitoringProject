<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="ui text container jsSearchFileView">
    <c:set var="search" value="${requestScope.searchList}"/>
    <h1 class="ui blue center aligned header">
        ${search.fileOriginalName}
    </h1>
        <c:if test="${fn:length(search.searchItemList) != 0}">
            <table class="ui selectable compact table jsProductTable">
                <thead>
                <tr>
                    <th>ID ячейки</th>
                    <th>Имя товара</th>
                    <th class="right aligned">Цена</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${search.searchItemList}" var="searchItem">
                    <tr class="jsSearchItem ${searchItem.productPrice == 0 ? 'warning' : 'positive'}">
                        <td>${searchItem.searchItemId}</td>
                        <td>${searchItem.productName}</td>
                        <td class="right aligned jsPrice">${searchItem.productPrice}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>

        <c:if test="${fn:length(search.searchItemList) == 0}">
            <h2 class="ui center aligned icon header">
                <i class="circular ban icon"></i>
                В файле не найдено ни одной записи.
            </h2>
        </c:if>
</div>
<script type="text/javascript">
    window.frm.components.init('SearchFileView', '.jsSearchFileView', {
        contextPath: '${pageContext.request.contextPath}'
    });
</script>