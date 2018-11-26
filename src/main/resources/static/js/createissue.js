(function (window, $, undefined) {

    var page;

    var jsClasses = {
        createIssueButton: ".js-create-issue-button",
        projectInputSelect: ".js-project-select",
        projectInputOption: ".js-project-select-option",
        assigneeInput: ".js-assignee-name",
        assigneeChangeButton: ".js-change-assignee-button",
        dueDateInput: ".js-due-date-input",
        dueDate: ".js-due-date",
        typeInputSelect: ".js-type-select",
        typeInputOption: ".js-type-select-option",
        priorityInputSelect: ".js-priority-select",
        priorityInputOption: ".js-priority-select-option",
        descriptionInput: ".js-description",
        nameInput: ".js-issue-name",
        parentIssueInput: ".js-parent-issue",
        attachments: ".js-attachments",
        individualAttachment: ".js-attachment",
        tagsInput: ".js-tags",
        boardInput: ".js-board-select",
        assigneeSaveButton: ".js-assignee-save",
        errorMessageContainer: ".js-error-message-container",
        errorMessage: ".js-error-message"
    };


    $(function () {//init
        console.log("Issue.init  start");

        page = $(this);
        page.find(jsClasses.createIssueButton).bind("click", sendCreateIssueRequest);
        page.find(jsClasses.assigneeChangeButton).bind("click", changeAssigneeOpenPopup);
        page.find(jsClasses.assigneeSaveButton).bind("click", changeAssigneeSave);

        console.log("Issue.init end");
    });


    function sendCreateIssueRequest() {

        //dom elements
        var projectSelect = page.find(jsClasses.projectInputSelect);
        var assignee = page.find(jsClasses.assigneeInput);
        var typeSelect = page.find(jsClasses.typeInputSelect);
        var prioritySelect = page.find(jsClasses.priorityInputSelect);
        var description = page.find(jsClasses.descriptionInput);
        var issueName = page.find(jsClasses.nameInput);
        var tags = page.find(jsClasses.tagsInput);
        var parentIssue = page.find(jsClasses.parentIssueInput);
        var dueDate = page.find(jsClasses.dueDate);
        var attachments = page.find(jsClasses.attachments);
        var attachmentsList = attachments.find(jsClasses.individualAttachment);
        var boardSelect = page.find(jsClasses.boardInput);


        //values
        var selectedProject = projectSelect.find("option:selected").val();
        var selectedType = typeSelect.find("option:selected").val();
        var selectedPriority = prioritySelect.find("option:selected").val();
        var assigneeName = assignee.val();
        var descriptionText = description.val();
        var tagsText = tags.val();
        var issueNameValue = issueName.val();
        var dueDateValue = dueDate.val();
        var request = {
            project: selectedProject,
            name: issueNameValue,
            description: descriptionText,
            assignee: assigneeName,
            boardId: null,
            type: selectedType,
            priority: selectedPriority,
            tags: tagsText,
            dueDate: dueDateValue,
            parentIssue: null
        };

        var isRequestValid = validateCreateIssueRequest(request);
        if (isRequestValid) {
            createIssueSendRequestToBE(request)
        } else {

        }

    }

    function createIssueSendRequestToBE(request) {
        var errorContainer = page.find(jsClasses.errorMessageContainer);
        var errorMessage = errorContainer.find(jsClasses.errorMessage);
        errorContainer.addClass("hidden");
        errorMessage.text("");


        $.ajax({
            type: "POST",
            url: "http://" + location.host + "/issues/create-issue-request",
            data: request,
            success: function (data) {
                console.log(data);

                if (data.data && data.success) {

                    if (data.redirectURL && data.success) {
                        window.location.href = "http://" + location.host + data.redirectURL;
                    } else if (data.error) {
                        errorMessage.text(data.errorMessage);
                        errorContainer.removeClass("hidden");
                    }

                }

            },
            error: function (e) {
                console.log(e);
            }
        });
    }


    function validateCreateIssueRequest(request) {
        if (/*!request.project TODO REMOVE ||*/ !request.name || !request.type || !request.priority) {
            return false;
        }
        return true;
    }


    function changeAssigneeOpenPopup() {
        $(".js-change-assignee-popup").modal("show");
    }

    function changeAssigneeSave() {
        var assigneeNameFromPopup = $("#searchInput").val();
        if (assigneeNameFromPopup) {
            var assigneeVariants = $(".assignee-search-container").find(".js-search-user-entry");
            var valid = false;
            $.each(assigneeVariants, function (index, value) {
                var val = $(value);
                var valUsername = val.find(".js-username").text();
                var valFullName = val.find(".js-full-name").text();
                var searchInputVal = $("#searchInput").val();
                var fullNameToCheck = valFullName + valUsername;
                if (fullNameToCheck === searchInputVal) {
                    valid = true;
                    console.log("ChangeAsignee:::::valid = true");
                }
            });

            if (valid) {
                page.find(jsClasses.assigneeInput).val(assigneeNameFromPopup);
            }

        }
    }


}(window, jQuery));