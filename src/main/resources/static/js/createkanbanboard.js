(function (window, $, undefined) {

    var page;

    var jsClasses = {

    };


    $(function () {//init
        console.log("KanbanBoardCreate.init  start");

        page = $(this);
        page.find(jsClasses.createIssueButton).bind("click", sendCreateIssueRequest);

        console.log("KanbanBoardCreate.init end");
    });


    function sendCreateKanbanBoardRequest() {

        //dom elements
        var projectSelect = page.find(jsClasses.projectInputSelect);
        var assignee = page.find(jsClasses.assigneeInput);
        var typeSelect = page.find(jsClasses.typeInputSelect);
        var prioritySelect = page.find(jsClasses.priorityInputSelect);
        var description = page.find(jsClasses.descriptionInput);
        var issueName = page.find(jsClasses.nameInput);
        var tags = page.find(jsClasses.tagsInput);
        var parentIssue = page.find(jsClasses.parentIssueInput);
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

        var request = {
            project: selectedProject,
            name: issueNameValue,
            description: descriptionText,
            assignee: assigneeName,
            boardId: null,
            type: selectedType,
            priority: selectedPriority,
            tags: tagsText,
            parentIssue: null
        };

        var isRequestValid = validateCreateIssueRequest(request);
        if (isRequestValid) {
            sendCreateKanbanBoardRequest(request)
        } else {

        }

    }

    function sendCreateKanbanBoardRequest(request) {
        var errorContainer = page.find(jsClasses.errorMessageContainer);
        var errorMessage = errorContainer.find(jsClasses.errorMessage);
        errorContainer.addClass("hidden");
        errorMessage.text("");



        $.ajax({
            type: "POST",
            url: "http://" + location.host + "/issue/create-issue-request",
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


    function validateCreateKanbanBoardRequest(request) {
        if (/*!request.project TODO REMOVE ||*/ !request.name || !request.type || !request.priority) {
            return false;
        }
        return true;
    }



}(window, jQuery));