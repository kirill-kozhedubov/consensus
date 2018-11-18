(function (window, $, undefined) {

    var page;

    $(function () {//init
        console.log("Issue.init  start");

        page = $(this);
        page.find(".js-create-issue-button").bind("click", sendCreateIssueRequest);

        console.log("Issue.init end");
    });


    function sendCreateIssueRequest() {

    }


}(window, jQuery));