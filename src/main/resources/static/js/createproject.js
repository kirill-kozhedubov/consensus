(function (window, $, undefined) {

    var page;
    var jsClasses = {
        projectNameInput: ".js-project-name",
        descriptionInput: ".js-project-description",
        abbreviationInput: ".js-project-abbreviation",
        createProjectButton: ".js-create-project-button",
        errorMessageContainer: ".js-error-message-container",
        errorMessage: ".js-error-message"
    };


    $(function () {//init
        console.log("Issue.init  start");

        page = $(this);
        page.find(jsClasses.createProjectButton).bind("click", sendCreateProjectRequest);

        console.log("Issue.init end");
    });


    function sendCreateProjectRequest() {

        var nameInput = page.find(jsClasses.projectNameInput);
        var abbrInput = page.find(jsClasses.abbreviationInput);
        var descriptionInput = page.find(jsClasses.descriptionInput);

        var projectName = nameInput.val();
        var description = descriptionInput.val();
        var abbreviation = abbrInput.val();

        var request = {
            name: projectName,
            description: description,
            abbreviation: abbreviation
        };


        var isRequestValid = validateProjectRequest(request);
        if (isRequestValid) {
            sendCreateProjectRequestToBE(request);
        }

    }

    function sendCreateProjectRequestToBE(request) {
        var errorContainer = page.find(jsClasses.errorMessageContainer);
        var errorMessage = errorContainer.find(jsClasses.errorMessage);
        errorContainer.addClass("hidden");
        errorMessage.text("");

        $.ajax({
            type: "POST",
            url: "http://" + location.host + "/projects/create-project-request",
            data: request,
            success: function (data) {
                console.log(data);

                if (data.redirectURL && data.success) {
                    window.location.href = "http://" + location.host + data.redirectURL;
                } else if (data.error) {
                    errorMessage.text(data.errorMessage);
                    errorContainer.removeClass("hidden");
                }

            },
            error: function (e) {
                console.log(e);
            }
        });


    }

    function validateProjectRequest(request) {
        if (!request.name || !request.abbreviation) {
            return false;
        }
        return true;
    }


}(window, jQuery));