<form class="ui small modal jsAddFileModal" action="${pageContext.request.contextPath}/upload" method="post" enctype="multipart/form-data">
    <div class="header">
        Выберите файл
    </div>
    <div class="content">
        <p>Выберите файл в формате Excel для загрузки на сервер мониторинга:</p>

        <div class="ui action input">
            <input name="description" type="text" placeholder="Выберите файл" readonly>
            <input name="data" type="file">
            <div class="ui icon button">
                <i class="attach icon"></i>
            </div>
        </div>
    </div>
    <div class="actions">
        <div class="ui negative button">
            Отмена
        </div>
        <button class="ui positive right labeled icon button" type="submit">
            Загрузить
            <i class="checkmark icon"></i>
        </button>
    </div>
</form>

<script type="text/javascript">
    window.frm.components.init('AddFileModal', '.jsAddFileModal');
</script>