(function ($, window) {

    var ELEMENTS = {
        CLOSE_ICON: '.message .close',
        MESSAGE: '.message'
    };

    var frm = window.frm;

    var ErrorMessage = frm.inheritance.inherits(frm.components.Component, {

        /**
         * Executed on component initialization
         */
        init: function () {
            this.content.find(ELEMENTS.CLOSE_ICON)
                .on('click', function () {
                    $(this)
                        .closest(ELEMENTS.MESSAGE)
                        .transition('fade');
                });
        }
    });

    frm.components.register('ErrorMessage', ErrorMessage);

})(jQuery, window);
