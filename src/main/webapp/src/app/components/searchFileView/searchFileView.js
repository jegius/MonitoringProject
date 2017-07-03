(function ($, window) {

    var frm = window.frm;

    var contextPath;

    var SearchFileView = frm.inheritance.inherits(frm.components.Component, {

        /**
         * Executed on component initialization
         */
        init: function () {
            contextPath = this.params.contextPath;
        }
    });

    frm.components.register('SearchFileView', SearchFileView);

})(jQuery, window);
