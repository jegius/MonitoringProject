(function ($, window) {

    var ELEMENTS = {
        USER_ROLE_SELECTOR: '.jsAddUserDropdown',
        ADD_USER_FORM: '.jsAddUserForm',
        NAME_INPUT: '.jsUserName',
        PASSWORD_INPUT: '.jsPassword',
        ADD_USER_BUTTON: '.jsAddUser',
        USER_ROLE: '.jsRoleId'
    };

    var LINKS = {
        MANAGEMENT: '/management'
    };

    var EVENTS = {
        CLICK: 'click',
        REFRESH_PAGE: 'refreshPage'
    };

    var ACTIONS = {
        ADD: 'add'
    };

    var frm = window.frm;

    var UserManagementComponent = frm.inheritance.inherits(frm.components.Component, {

        /**
         * Executed on component initialization
         */
        init: function () {
            this.content.find(ELEMENTS.USER_ROLE_SELECTOR).dropdown();
            this.content.find(ELEMENTS.ADD_USER_BUTTON).on(EVENTS.CLICK, this.addNewUser.bind(this));
        },

        addNewUser: function (event) {
            var $this = $(event.currentTarget);
            var $parentElement = $this.closest(ELEMENTS.ADD_USER_FORM);
            var $userName = $parentElement.find(ELEMENTS.NAME_INPUT).val();
            var $userRole = $parentElement.find(ELEMENTS.USER_ROLE);
            var $userPassword = $parentElement.find(ELEMENTS.PASSWORD_INPUT).val();
            $.post(
                this.params.addUserUrl + LINKS.MANAGEMENT, {
                    action: ACTIONS.ADD,
                    name: $userName,
                    role: $userRole,
                    password: $userPassword
                }, function () {
                    frm.events.fire(EVENTS.REFRESH_PAGE);
                }

            )
        }

    });

    frm.components.register('UserManagement', UserManagementComponent);

})(jQuery, window);
