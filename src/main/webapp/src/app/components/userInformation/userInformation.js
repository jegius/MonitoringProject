(function ($, window) {



    var frm = window.frm;

    var UserInformation = frm.inheritance.inherits(frm.components.Component, {

        /**
         * Executed on component initialization
         */
        init: function () {

        }
    });

    frm.components.register('UserInformation', UserInformation);

})(jQuery, window);

