$(function () {
    $("#cancel-btn").click(function (event) {
        event.preventDefault();
        swal("Hello world!");
    });
});

$(function () {
    $("#detail-btn").click(function (event) {
        event.preventDefault();
        swal("Good job!", "You clicked the button!", "success");
    });
});