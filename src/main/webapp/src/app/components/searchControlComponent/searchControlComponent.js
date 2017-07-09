(function ($, window) {

    var frm = window.frm;

    var ELEMENTS = {
        FIND_ELEMENTS_FIELD: '.jsFind',
        COMMON_PERCENT:'.jsPercent',
        STORES_DROPDOWN: '.jsStoreSelect',
        FIND_PRODUCTS_BUTTON: '.jsFindProducts',
        STORE_SELECTOR: '.jsStoreSelect option:selected',
        SEARCH_BODY:'#searchBody'
    };

    var ACTIONS = {
        ACTION: 'action',
        DO_SEARCH: 'doSearch'
    };

    var LINKS = {
        VIEW: '/view'
    };

    var EVENTS = {
        SEND_FIND_PRODUCTS: 'sendFindProducts'
    };
    
    var contextPath;

    var SearchControlComponent = frm.inheritance.inherits(frm.components.Component, {

        /**
         * Executed on component initialization
         */
        init: function () {

            contextPath = this.params.contextPath;

            $(this.content)
                .sticky({
                    context: ELEMENTS.SEARCH_BODY,
                    offset: 50
                });

            this.content.find(ELEMENTS.FIND_PRODUCTS_BUTTON).on('click', this.startSearch.bind(this));
            this.content.find(ELEMENTS.STORES_DROPDOWN).dropdown();

            frm.events.on(EVENTS.SEND_FIND_PRODUCTS, this.setFindProducts.bind(this))
        },

        startSearch: function () {
            var storeId = this.content.find(ELEMENTS.STORE_SELECTOR).val();
            $.post(contextPath + LINKS.VIEW, {
                'action' : ACTIONS.DO_SEARCH,
                'storeId': storeId
            }, function (data) {
                
            })
        },

        setFindProducts: function (data) {
            window.console.log(data);
            this.content.find(ELEMENTS.FIND_ELEMENTS_FIELD).append(data);
        }

    });

    frm.components.register('SearchControlComponent', SearchControlComponent);

})(jQuery, window);
