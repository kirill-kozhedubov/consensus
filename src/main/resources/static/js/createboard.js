(function (window, $, undefined) {

    var page;

    var jsClasses = {
        boardNameInput: ".js-board-name",
        boardDescriptionInput: ".js-board-description",
        boardCreateButton: ".js-create-board-button",
        boardTypeInputSelect: ".js-board-type-select",
        boardTypeInputOption: ".js-board-type-select-option",
        projectInputSelect: ".js-project-select",
        projectInputOption: ".js-project-select-option",
        errorMessageContainer: ".js-error-message-container",
        errorMessage: ".js-error-message"
    };


    $(function () {//init
        console.log("BoardCreate.init  start");

        page = $(this);

        console.log("URLparams:", getParameters(getNavUrl()));


        console.log("BoardCreate.init end");
    });


    function initButtons() {
        page.find(jsClasses.boardCreateButton).bind("click", sendCreateBoardRequest);

        var url = getParameters(getNavUrl());
        if (utl.projectId) {
            var projectSelect = page.find(jsClasses.projectInputSelect);
            projectSelect.addClass("disabled");
        }
    }


    function sendCreateBoardRequest() {

        //dom elements
        var projectSelect = page.find(jsClasses.projectInputSelect);
        var nameInput = page.find(jsClasses.boardNameInput);
        var descriptionInput = page.find(jsClasses.boardDescriptionInput);
        var boardTypeSelect = page.find(jsClasses.boardTypeInputSelect);

        //values
        var selectedProject = projectSelect.find("option:selected").val();
        var descriptionText = descriptionInput.val();
        var boardNameValue = nameInput.val();
        var boardType = boardTypeSelect.find("option:selected").val();

        var request = {
            project: selectedProject,
            name: boardNameValue,
            description: descriptionText,
            type: boardType
        };

        var isRequestValid = validateCreateBoardRequest(request);
        if (isRequestValid) {
            sendCreateBoardRequestToBE(request)
        } else {

        }

    }

    function sendCreateBoardRequestToBE(request) {
        var errorContainer = page.find(jsClasses.errorMessageContainer);
        var errorMessage = errorContainer.find(jsClasses.errorMessage);
        errorContainer.addClass("hidden");
        errorMessage.text("");


        $.ajax({
            type: "POST",
            url: "http://" + location.host + "/boards/create-board-request",
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


    function validateCreateBoardRequest(request) {
        if (/*!request.project TODO REMOVE ||*/ !request.name || !request.type) {
            return false;
        }
        return true;
    }


    function getNavUrl() {
        return window.location.search.replace("?", "");
    }

    function getParameters(url) {
        var params = {};
        url = url.toLowerCase();
        url = url.split('&');

        //Iterate over url parameters array
        var length = url.length;
        for (var i = 0; i < length; i++) {
            //Create prop
            var prop = url[i].slice(0, url[i].search('='));
            //Create Val
            var value = url[i].slice(url[i].search('=')).replace('=', '');
            //Params New Attr
            params[prop] = value;
        }
        return params;
    }


}(window, jQuery));