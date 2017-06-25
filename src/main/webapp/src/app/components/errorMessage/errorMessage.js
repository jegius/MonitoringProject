(function ($, window) {

    var ELEMENTS = {
        CLOSE_ICON: '.message .close',
        MESSAGE: '.message'
    };

    var CONSTANTS = {
        DELAY: 3000
    };

    var ACTIONS = {
        FADE: 'fade',
        CLICK: 'click'
    };

    var frm = window.frm;

    var ErrorMessage = frm.inheritance.inherits(frm.components.Component, {

        /**
         * Executed on component initialization
         */
        init: function () {
            this.content.find(ELEMENTS.CLOSE_ICON)
                .on(ACTIONS.CLICK, function () {
                    $(this)
                        .closest(ELEMENTS.MESSAGE)
                        .transition(ACTIONS.FADE);
                });

            setTimeout(function () {
                this.content.find(ELEMENTS.MESSAGE).transition(ACTIONS.FADE);
            }.bind(this), CONSTANTS.DELAY)
        }
    });

    frm.components.register('ErrorMessage', ErrorMessage);

})(jQuery, window);
