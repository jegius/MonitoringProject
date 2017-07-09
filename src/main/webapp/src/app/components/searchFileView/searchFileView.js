(function ($, window) {

    var frm = window.frm;

    var ELEMENTS = {
        PRODUCT_TABLE: '.jsProductTable',
        TABLE_ROW: '.jsProductTable .jsSearchItem',
        PRICE_CELL: '.jsPrice'
    };

    var EVENTS = {
        SEND_FIND_PRODUCTS: 'sendFindProducts'
    };

    var contextPath;

    var SearchFileView = frm.inheritance.inherits(frm.components.Component, {

        /**
         * Executed on component initialization
         */
        init: function () {
            contextPath = this.params.contextPath;
            var findProductCount;

            this.content.find(ELEMENTS.TABLE_ROW).each(function (index, element) {
                var findProduct = $(element).find(ELEMENTS.PRICE_CELL).text();
                if (findProduct) {
                    findProductCount++;
                }
            });

            frm.events.fire(EVENTS.SEND_FIND_PRODUCTS, findProductCount)
        }
    });

    frm.components.register('SearchFileView', SearchFileView);

})(jQuery, window);
