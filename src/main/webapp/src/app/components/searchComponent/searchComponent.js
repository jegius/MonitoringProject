(function ($, window) {

    var ELEMENTS = {
        ADD_FILE_BUTTON: '.jsOpenModal'
    };

    var EVENTS = {
        SHOW_MODAL: 'showModal'
    };

    var frm = window.frm;

    var SearchComponent = frm.inheritance.inherits(frm.components.Component, {

        /**
         * Executed on component initialization
         */
        init: function () {
            $(ELEMENTS.ADD_FILE_BUTTON).on('click', function () {
                frm.events.fire(EVENTS.SHOW_MODAL)
            });
        }
    });

    frm.components.register('SearchComponent', SearchComponent);

})(jQuery, window);

