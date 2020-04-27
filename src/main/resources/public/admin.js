console.log("LOADED MY>JS");
console.log("LOADED");
$(document).ready(function () {

    window.onbeforeunload = function (e) {
        e = e || window.event;
        var returnString = 'Are you sure?';
        if (e) {
            e.returnValue = returnString;
        }
        return returnString;
    };


    $("#generateBut").click(function () {

        console.log("Generate CLICKED");
        var numberIpVal = $("#numberInput").val();
        sendGenerateReq(numberIpVal);

    });

    $("#autoGenerateButton").click(function () {
        var secKey = $("#secretKey").val();
        $.ajax({
            type: 'POST',
            url: '/housie/autoGenerate',
            data: JSON.stringify({ key: secKey }),
            //data: '{"name":"jonas"}', // or JSON.stringify ({name: 'jonas'}),
            success: function (response) {
                //console.log("success "+response); 
                $("#generateStatus").html(response["resp"]);
            },
            contentType: "application/json",
            dataType: 'json'
        });
    });

    $("#resetBoard").click(function () {
        console.log("reset clicked ")

        var secretKey = prompt("Enter Secret Key");

        $.ajax({
            type: 'POST',
            url: '/housie/resetBoard',
            data: JSON.stringify({ key: secretKey }),
            //data: '{"name":"jonas"}', // or JSON.stringify ({name: 'jonas'}),
            success: function (response) {
                //console.log("success resetBoard",response); 
                alert(response["resp"])
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(jqXHR, textStatus, errorThrown);
                //alert("Unable to Reset Board");
            },
            contentType: "application/json",
            dataType: 'json'
        });


    });

    $("#delNumBut").click(function () {
        var secKey = $("#secretKey").val();
        var toBeDelNumber = prompt("Enter number to be deleted");
        $.ajax({
            type: 'POST',
            url: '/housie/deleteNum',
            data: JSON.stringify({ number: toBeDelNumber, key: secKey }),
            //data: '{"name":"jonas"}', // or JSON.stringify ({name: 'jonas'}),
            success: function (response) {
                //console.log("success "+response); 
                $("#generateStatus").html(response["resp"]);
            },
            contentType: "application/json",
            dataType: 'json'
        });
    });
    $("#showMsgBut").click(function () {
        console.log("clllllllllllll");
        var secKey = $("#secretKey").val();
        var mess = prompt("Enter Message");
        $.ajax({
            type: 'POST',
            url: '/housie/showMessage',
            data: JSON.stringify({ msg: mess, key: secKey }),
            //data: '{"name":"jonas"}', // or JSON.stringify ({name: 'jonas'}),
            success: function (response) {
                //console.log("success "+response); 
                $("#generateStatus").html(response["resp"]);
            },
            contentType: "application/json",
            dataType: 'json'
        });
    });

    //V2 table

    renderAdminTable();
    $("#adTabTog").click(function () {
        $("#numberTable").toggle();
    });
    
    $("#adTabRef").click(function () {
        setAdminTableStatus();
    });

    $('.tableNumButton').click(function () {
        var number = $(this).html();
        console.log("table number clicked: ",number);
        sendGenerateReq(number);
        $(this).prop('disabled', true);
    });

});

function setAdminTableStatus(){
    $.ajax({
        url: "/housie/map",
        success: function (result) {
            //console.log(result);
            for (var key in result) {
                if (result.hasOwnProperty(key)) {
                    var val = result[key];
                    //console.log(key);
                    //console.log(val);
                    if (val == "true") {
                        $('#adBut' + key).prop('disabled', true);

                    }
                    if (val == "false") {
                        $('#adBut' + key).prop('disabled', false);
                    }
                }

            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(textStatus, errorThrown);
        }
    });

}
function renderAdminTable(){

    for (var i = 0; i < 9; i++) {
        var rowStr = "<tr>";
        for (var j = 1; j <= 10; j++) {

            var number = i * 10 + j;
            rowStr = rowStr + "<td><button id='adBut" + number + "' class='tableNumButton' >" + number + "</button></td>";
        }
        rowStr = rowStr + "</tr>";
        $('#numberTable tr:last').after(rowStr);

    }
    setAdminTableStatus();
}


function sendGenerateReq(nm) {
    var secKey = $("#secretKey").val();
    console.log(nm, secKey);
    $.ajax({
        type: 'POST',
        url: '/housie/generate',
        data: JSON.stringify({ number: nm, key: secKey }),
        success: function (response) {
            //console.log("success "+response); 
            $("#generateStatus").html(response["resp"]);
        },
        contentType: "application/json",
        dataType: 'json'
    });
}

