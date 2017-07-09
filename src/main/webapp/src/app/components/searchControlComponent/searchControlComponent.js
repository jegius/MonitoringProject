(function ($, window) {

    var frm = window.frm;

    var ELEMENTS = {
        FIND_ELEMENTS_FIELD: '.jsFind',
        COMMON_PERCENT:'.jsPercent',
        SEARCH_BODY:'#searchBody'
    };

    var EVENTS = {
        SEND_FIND_PRODUCTS: 'sendFindProducts'
    };

    var SearchControlComponent = frm.inheritance.inherits(frm.components.Component, {

        /**
         * Executed on component initialization
         */
        init: function () {
            $(this.content)
                .sticky({
                    context: ELEMENTS.SEARCH_BODY,
                    offset: 50
                });

            frm.events.on(EVENTS.SEND_FIND_PRODUCTS, this.setFindProducts.bind(this))
        },

        setFindProducts: function (data) {
            window.console.log(data);
            this.content.find(ELEMENTS.FIND_ELEMENTS_FIELD).append(data);
        }

    });

    frm.components.register('SearchControlComponent', SearchControlComponent);

})(jQuery, window);
