(function (window, $, undefined) {

    var page;

    $(function () {//init
        console.log("Issue.init  start");

        page = $(this);
        page.find("#searchInput").bind("keyup", searchAndFilter);
        page.find("#searchInput").bind("blur", blurSearchInput);
        page.find("#searchInput").bind("focus", focusSearchInput);

        page.find(".js-assignee-save").bind("click",selectAssigneeOnBE);

        console.log("Issue.init end");
    });

    function searchAndFilter() {
        console.log("searchAndFilter");

        var searchInput = page.find("#searchInput");
        var searchText = searchInput.val().toUpperCase();

        var usersFound;

        var assigneeVariantsContainer = page.find(".assignee-search-container");
        assigneeVariantsContainer.empty();

        if (searchInput.val().length > 2) {
            $.ajax({
                type: "GET",
                url: "http://" + location.host + "/issues/find-user",
                data: {searchInput: searchText},
                success: function (data) {
                    console.log(data);
                    if (data.data && data.data.foundUsers) {

                        $.each(data.data.foundUsers, function (index, value) {
                            var fullName = value.fullName;
                            var userName = value.username;
                            console.log("found: ", fullName, userName);
                            var userFound = {fullName: fullName, username: userName};
                            var userGeneratedBlock = createPossibleAssigneeContainer(userFound);
                            assigneeVariantsContainer.append(userGeneratedBlock);
                            $("._userName_" + userName).click(function() {
                                selectAssignee(event);
                            });
                        });
                    }

                },
                error: function (e) {
                    console.log(e);
                }
            });
        }

        filter();
    }

    function createPossibleAssigneeContainer(assignee) {
        var fullName = assignee.fullName;
        var userName = assignee.username;

        var spanFullName = "<span class='js-full-name'>" + fullName + "</span>";
        var spanUsername = "<span class='js-username'> (" + userName + ")</span>";
        var div = "<div class='js-search-user-entry  _userName_" + userName + "'>" + spanFullName + spanUsername + "</div>";

        return div;
    }

    function focusSearchInput() {
        var assigneeSearchContainer = page.find(".assignee-search-container");
        assigneeSearchContainer.removeClass("hidden");
    }

    function blurSearchInput() {
        var assigneeSearchContainer = page.find(".assignee-search-container");
        assigneeSearchContainer.delay(300).queue(function () {
            assigneeSearchContainer.addClass("hidden");
            assigneeSearchContainer.dequeue();
        });
    }

    function filter() {
        var input = page.find("#searchInput");
        var filter = input.val().toUpperCase();
        var dropdown = page.find("#searchDropdown");
        var searchEntry = dropdown.find("div");

        $.each(searchEntry, function (index, value) {
            var val = $(value);
            if (val.text().toUpperCase().indexOf(filter) > -1) {
                val.removeClass("hidden");
            } else {
                val.addClass("hidden");
            }
        });
    }

    function selectAssignee(event) {
        console.log(event);

        var target = $(event.target);
        var username = target.find(".js-username").text();
        var fullName = target.find(".js-full-name").text();

        var searchInput = page.find("#searchInput");
        searchInput.val(fullName + username);


    }

    function selectAssigneeOnBE() {
        console.log("ChangeAssignee.selectAssigneeOnBE::::::::::::::::::wrapper");
        $(".js-change-assignee-popup").modal("hide"); //TODO REMOVE



        var issueKey = page.find(".js-issue-key");
        var username = null;
        var fullName = null;

      /*  $.ajax({
            type: "GET",
            url: "http://" + location.host + "/issues/update-assignee-on-issue",
            data: {usename: username, fullName: fullName, issueKey: issueKey},
            success: function (data) {
                console.log(data);

                if (data.data && data.data.foundUsers) {

                }
                $(".js-change-assignee-popup").modal("hide");

            },
            error: function (e) {
                console.log(e);
            }
        });*/
    }


}(window, jQuery));