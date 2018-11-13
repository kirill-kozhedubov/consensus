(function (window, $, undefined) {

    var page;

    $(function () {//init
        console.log("Issue.init  start");

        page = $(this);
        page.find(".searchInput").bind("keyup", filter);

        console.log("Issue.init end");
    });


    function addAttachment() {
        console.log("IssuePage.addAttachment start");


        console.log("IssuePage.addAttachment end");
    }


    function filter() {
        var searchEntry;
        var input = page.find("#searchInput");
        var filter = input.val().toUpperCase();
        var dropdown = page.find("#searchDropdown");
        searchEntry = dropdown.find("div");

        $.each(searchEntry, function (index, value) {
            if (value.text().toUpperCase().indexOf(filter) > -1) {
                value.removeClass("hidden");
            } else {
                value.addClass("hidden");
            }
            alert(index + ": " + value);
        });



    }




}(window, jQuery));