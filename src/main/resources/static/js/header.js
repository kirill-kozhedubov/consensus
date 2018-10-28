(function (window, $, undefined) {

    var page;

    $(function () {//init
        page = $(this);
        page.find("#js-authorize-user").bind("click", authorize);
    });



}(window, jQuery));