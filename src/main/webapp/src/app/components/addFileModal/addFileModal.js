(function ($, window) {

    var ELEMENTS = {
        MODAL_PAGE: '.modal'
    };

    var EVENTS = {
        SHOW_MODAL: 'showModal',
        SHOW: 'show'
    };

    var frm = window.frm;

    var AddFileModal = frm.inheritance.inherits(frm.components.Component, {

        /**
         * Executed on component initialization
         */
        init: function () {
            frm.events.on(EVENTS.SHOW_MODAL, this.showModal.bind(this));
        },

        showModal: function () {
            this.content.find(ELEMENTS.MODAL_PAGE).modal(EVENTS.SHOW);
        }
    });

    frm.components.register('AddFileModal', AddFileModal);

})(jQuery, window);