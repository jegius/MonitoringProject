(function ($, window) {

    var ELEMENTS = {
        REMOVE_BUTTON: '.jsRemoveUser'
    };

    var ACTIONS = {
        REMOVE: 'remove',
        CLICK: 'click'
    };

    var LINKS = {
        MANAGEMENT: '/management'
    };


    var frm = window.frm;

    var UserTableComponent = frm.inheritance.inherits(frm.components.Component, {

        /**
         * Executed on component initialization
         */
        init: function () {

            this.content.find(ELEMENTS.REMOVE_BUTTON).on(ACTIONS.CLICK, this.removeUser());
        },
        removeUser: function () {
            var $this = $(this);
            var $userId = $this.val();

            $.post(this.params.userTableUrl + LINKS.MANAGEMENT,{
                action: ACTIONS.REMOVE,
                userId: $userId
            })
        }

    });

    frm.components.register('UserTable', UserTableComponent);

})(jQuery, window);
