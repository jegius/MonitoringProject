(function ($, window) {

    var ELEMENTS = {
        USER_ROLE_SELECTOR: '.jsAddUserDropdown',
        REMOVE_BUTTON: '.jsRemoveUser',
        USER_TABLE: '.jsUserTable',
        USER_ROLE_SELECTOR_OPTION: '.jsAddUserDropdown option:selected',
        MAIN_CONTAINER: '.jsUserTableComponent',
        TABLE_ROW: '.jsUserInfoRow',
        CHANGE_PASSWORD_CONTAINER: '.jsChangePasswordContainer',
        NEW_PASSWORD_INPUT: '.jsNewPassword',
        USER_ID_FOR_CHANGE_PASSWORD: '.userIdForChangePassword',
        CHANGE_PASSWORD_BUTTON: '.jsChangePassword',
        USER_ID: '.jsUserId'
    };

    var ACTIONS = {
        REMOVE: 'remove',
        CLICK: 'click',
        UPDATE: 'update',
        ACTION: 'action',
        REFRESH: 'refresh'
    };

    var LINKS = {
        MANAGEMENT: '/management'
    };

    var EVENTS = {
        REFRESH_PAGE: 'refreshPage'
    };
    var frm = window.frm;

    var UserTableComponent = frm.inheritance.inherits(frm.components.Component, {

        /**
         * Executed on component initialization
         */
        init: function () {
            frm.events.on(EVENTS.REFRESH_PAGE, this.refreshPage.bind(this));

            this.content.find(ELEMENTS.USER_ROLE_SELECTOR).dropdown();
            this.content.find(ELEMENTS.REMOVE_BUTTON).on(ACTIONS.CLICK, this.removeUser.bind(this));
            this.content.find(ELEMENTS.USER_ROLE_SELECTOR).change(this.updateUserRole.bind(this));
            this.content.find(ELEMENTS.CHANGE_PASSWORD_BUTTON).on(ACTIONS.CLICK, this.changePassword.bind(this))
        },

        removeUser: function (event) {
            var $this = $(event.currentTarget);
            var $userId = $this.val();

            $.post(this.params.userTableUrl + LINKS.MANAGEMENT, {
                action: ACTIONS.REMOVE,
                userId: $userId
            }, this.refreshPage.bind(this))
        },

        refreshPage: function () {
            $.post(this.params.userTableUrl + LINKS.MANAGEMENT,
                {"action": ACTIONS.REFRESH},
                function (data) {
                    var $data = $(data);
                    var newTable = $data.find(ELEMENTS.USER_TABLE);

                    this.content.find(ELEMENTS.USER_TABLE).replaceWith(newTable);
                    this.content.find(ELEMENTS.USER_ROLE_SELECTOR).dropdown();
                    this.content.find(ELEMENTS.REMOVE_BUTTON).on(ACTIONS.CLICK, this.removeUser.bind(this));
                    this.content.find(ELEMENTS.USER_ROLE_SELECTOR).change(this.updateUserRole.bind(this));
                    this.content.find(ELEMENTS.CHANGE_PASSWORD_BUTTON).on(ACTIONS.CLICK, this.changePassword.bind(this))

                }.bind(this))
        },
        changePassword: function (event) {
            var $this = $(event.currentTarget);
            var $buttonContainer = $this.closest(ELEMENTS.CHANGE_PASSWORD_CONTAINER);
            var $password = $buttonContainer.find(ELEMENTS.NEW_PASSWORD_INPUT).val();

            if ($password.length < 3){
                return;
            }

            var $userId = $buttonContainer.find(ELEMENTS.USER_ID_FOR_CHANGE_PASSWORD).val();

            $.post(this.params.userTableUrl + LINKS.MANAGEMENT,
                {
                    action: ACTIONS.UPDATE,
                    password: $password,
                    userId: $userId
                },function () {
                    this.refreshPage();

                }.bind(this))
        },

        updateUserRole: function (event) {
            var currentElement = $(event.currentTarget);
            var parentElement = currentElement.closest(ELEMENTS.TABLE_ROW);
            var userId = parentElement.find(ELEMENTS.USER_ID).text();
            var roleId = parentElement.find(ELEMENTS.USER_ROLE_SELECTOR_OPTION).val();

            $.post(this.params.userTableUrl + LINKS.MANAGEMENT,
                {
                    action: ACTIONS.UPDATE,
                    roleId: roleId,
                    userId: userId
                },function () {
                    this.refreshPage();

                }.bind(this))
        }

    });

    frm.components.register('UserTableComponent', UserTableComponent);

})(jQuery, window);
