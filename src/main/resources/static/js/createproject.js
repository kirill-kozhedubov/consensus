(function (window, $, undefined) {

    var page;
    var jsClasses = {
        projectInput: "",
        assigneeInput: "",
        dueDateInput: "",
        typeInputSelect: "",
        typeInputOption: "",
        priorityInputSelect: "",
        priorityInputOption: "",
        descriptionInput: "",
        nameInput: "",
        parentIssueInput: "",
        attachmentsInput: "",
        boardInput: ""
    };


    $(function () {//init
        console.log("Issue.init  start");

        page = $(this);
        page.find(".js-create-issue-button").bind("click", sendCreateIssueRequest);

        console.log("Issue.init end");
    });


    function sendCreateIssueRequest() {

    }


}(window, jQuery));