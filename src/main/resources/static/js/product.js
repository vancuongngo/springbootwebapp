$(function () {
    $("#furniture").click(function () {
        $.ajax({
            url: CONTEXT_ROOT + "ajax/product/furniture" ,
            data: {
                name:"Chair",
                usage:"Using for sitting",
                time: new Date()
            },
            type: "GET",
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
            },
            statusCode: {
                404: function (response) {
                    location.href = CONTEXT_ROOT + "error/404";
                },
                403: function (response) {
                    location.href = CONTEXT_ROOT + "error/403";
                },
                401: function (response) {
                    location.href = CONTEXT_ROOT + "login?expired=";
                }
            },
            success: function (resultData) {
                console.log(resultData);
            }
        });
    });

    $("#flower").click(function () {
        $.ajax({
            url: CONTEXT_ROOT + "ajax/product/flower" ,
            data: {
                name:"Rose",
                numberOfPetal:5,
                time: new Date()
            },
            type: "GET",
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
            },
            statusCode: {
                404: function (response) {
                    location.href = CONTEXT_ROOT + "error/404";
                },
                403: function (response) {
                    location.href = CONTEXT_ROOT + "error/403";
                },
                401: function (response) {
                    location.href = CONTEXT_ROOT + "login?expired=";
                }
            },
            success: function (resultData) {
                console.log(resultData);
            }
        });
    })
});