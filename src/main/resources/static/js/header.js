(function (window, $, undefined) {

    var page;

    $(function () {//init
        page = $(this);
        page.find(".js-create-issue-header").bind("click", createIssue);
    });


    function createIssue() {
        window.location.href = "http://" + location.host + "/issue/create";
    }


}(window, jQuery));