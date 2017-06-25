<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="ui text container jsErrorMessage">
    <c:if test="${requestScope.answer != null}">
        <div class="ui success  message">
            <i class="close icon"></i>
            <p>${requestScope.answer}</p>
        </div>
    </c:if>
    <c:if test="${requestScope.error != null}">
        <div class="ui negative message">
            <i class="close icon"></i>
            <p>${requestScope.error}</p>
        </div>
    </c:if>
</div>
<script type="text/javascript">
    window.frm.components.init('ErrorMessage', '.jsErrorMessage');
</script>