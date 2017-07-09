(function ($, window) {

    var ELEMENTS = {
        ADD_FILE_BUTTON: '.jsOpenModal',
        SEARCH_COMPONENT: '.jsSearchComponent',
        SEARCH_ITEMS_CONTAINER: '.jsSearchItemContainer',
        VALUE: '.jsValue',
        REMOVE_BUTTON: '.jsRemove',
        VIEW_FILE: '.jsGoToSearch'
    };

    var EVENTS = {
        SHOW_MODAL: 'showModal',
        REFRESH: 'refresh',
        REMOVE_SEARCH: 'removeSearch'
    };

    var ACTION = {
        REMOVE_SEARCH: 'removeSearch'
    };

    var LINKS = {
        HOME: '/home'
    };

    var frm = window.frm;

    var SearchComponent = frm.inheritance.inherits(frm.components.Component, {

        /**
         * Executed on component initialization
         */
        init: function () {

            this.content.find(ELEMENTS.VIEW_FILE).on('click', this.redirectToPage.bind(this));
            this.content.find(ELEMENTS.ADD_FILE_BUTTON).on('click', this.showModal);
            this.content.find(ELEMENTS.REMOVE_BUTTON).on('click', this.removeSearch.bind(this));
        },
        removeSearch: function (e) {
            var $searchId = $(e.currentTarget).val();


            $.post(this.params.mainSearch + LINKS.HOME,
                {
                    'action': ACTION.REMOVE_SEARCH,
                    'searchId': $searchId
                }, function () {
                    this.refreshPage();
                }.bind(this));
        },

        redirectToPage: function (e) {
            var $this = $(e.currentTarget);
            var $searchId = $this.val();

            document.location = this.params.mainSearch + '/view?searchId=' + $searchId;
        },

        showModal: function () {
            frm.events.fire(EVENTS.SHOW_MODAL);
        },
        refreshPage: function () {
            $.post(this.params.mainSearch + LINKS.HOME,
                function (data) {
                    var $data = $(data);
                    var newSearches = $data.find(ELEMENTS.SEARCH_ITEMS_CONTAINER);
                    this.content.find(ELEMENTS.SEARCH_ITEMS_CONTAINER).replaceWith(newSearches);

                    this.content.find(ELEMENTS.ADD_FILE_BUTTON).on('click', this.showModal);
                    this.content.find(ELEMENTS.REMOVE_BUTTON).on('click', this.removeSearch.bind(this));

                }.bind(this));
        }
    });

    frm.components.register('SearchComponent', SearchComponent);

})(jQuery, window);

