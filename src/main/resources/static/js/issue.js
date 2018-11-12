(function (window, $, undefined) {

    var page;

    $(function () {//init
        console.log("Issue.init  start");

        page = $(this);
        page.find(".js-change-assignee").bind("click", changeAssignee);
        page.find(".js-become-reporter").bind("click", becomeReporter);

        page.find(".js-edit-button").bind("click", editIssue);
        page.find(".js-comment-button").bind("click", addComment);
        page.find(".js-assign-button").bind("click", changeAssignee);
        page.find(".js-move-button").bind("click", moveIssue);
        page.find(".js-change-status").bind("click", changeIssueStatus);
        page.find(".js-attach-button").bind("click", addAttachment);

        page.find(".js-comments-tab").bind("click", openCommentsTab);
        page.find(".js-history-tab").bind("click", openHistoryTab);

        console.log("Issue.init end");
    });


    function openCommentsTab() {
        var commentsTabContent = page.find(".issue-comments-tab-content");
        var historyTabContent = page.find(".js-history-tab-content");

        var commentsTab = page.find(".js-comments-tab");
        var historyTab = page.find(".js-history-tab");


        historyTabContent.addClass("hidden");
        historyTab.removeClass("active");

        commentsTab.addClass("active");
        commentsTabContent.removeClass("hidden");

    }

    function openHistoryTab() {
        var commentsTabContent = page.find(".issue-comments-tab-content");
        var historyTabContent = page.find(".js-history-tab-content");

        var commentsTab = page.find(".js-comments-tab");
        var historyTab = page.find(".js-history-tab");


        historyTabContent.removeClass("hidden");
        historyTab.addClass("active");

        commentsTab.removeClass("active");
        commentsTabContent.addClass("hidden");

    }


    function changeAssignee() {
        console.log("IssuePage.changeAssignee start");


        console.log("IssuePage.changeAssignee end");
    }

    function becomeReporter() {
        console.log("IssuePage.becomeReporter start");


        console.log("IssuePage.becomeReporter end");
    }

    function editIssue() {
        console.log("IssuePage.editIssue start");


        console.log("IssuePage.editIssue end");
    }

    function addComment() {
        console.log("IssuePage.addComment start");


        console.log("IssuePage.addComment end");
    }

    function moveIssue() {
        console.log("IssuePage.moveIssue start");


        console.log("IssuePage.moveIssue end");
    }

    function changeIssueStatus() {
        console.log("IssuePage.changeIssueStatus start");


        console.log("IssuePage.changeIssueStatus end");
    }

    function addAttachment() {
        console.log("IssuePage.addAttachment start");


        console.log("IssuePage.addAttachment end");
    }


}(window, jQuery));