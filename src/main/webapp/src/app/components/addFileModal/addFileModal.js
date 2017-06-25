(function ($, window) {

    var ELEMENTS = {
        MODAL_PAGE: '.modal',
        INPUT_TEXT: 'input:text',
        MAIN_INPUT: '.ui.action.input',
        INPUT_FILE: 'input:file'
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

            this.content.find(ELEMENTS.INPUT_TEXT).click(function() {
                $(this).parent().find(ELEMENTS.INPUT_FILE).click();
            });

            this.content.find(ELEMENTS.INPUT_FILE, ELEMENTS.MAIN_INPUT)
                .on('change', function(e) {
                    var name = e.target.files[0].name;
                    $(ELEMENTS.INPUT_TEXT, $(e.target).parent()).val(name);
                });

        },

        showModal: function () {
            $(ELEMENTS.MODAL_PAGE).modal(EVENTS.SHOW);
        }
    });

    frm.components.register('AddFileModal', AddFileModal);

})(jQuery, window);