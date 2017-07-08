<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="ui text container jsSearchFileView">
    <c:set var="search" value="${requestScope.searchList}"/>

    <table class="ui selectable inverted table">
        <thead>
        <tr>
            <th>ID ячейки</th>
            <th>Имя товара</th>
            <th class="right aligned">Цена</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${search.searchItemList}" var="searchItem">
        <tr class="jsSearchItem">
            <td>${searchItem.searchItemId}</td>
            <td>${searchItem.productName}</td>
            <td class="right aligned">${searchItem.productPrice}</td>
        </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script type="text/javascript">
    window.frm.components.init('SearchFileView', '.jsSearchFileView', {
        contextPath: '${pageContext.request.contextPath}'
    });
</script>