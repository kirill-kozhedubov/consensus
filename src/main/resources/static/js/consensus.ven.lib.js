$(function () {
    //setup ajax error handling
    $.ajaxSetup({
        error: function (x, status, error) {
            if (x.status == 403) {
                alert("Sorry, your session has expired. Please login again to continue");
                window.location.href ="/login";
            }
            else {
                alert("An error occurred: " + status + "nError: " + error);
            }
        }
    });
});