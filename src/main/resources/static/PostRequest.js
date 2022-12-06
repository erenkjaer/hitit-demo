$(document).ready( function (){
    $("#add-tweet").submit(function (event) {
        event.preventDefault();
        ajaxPost();
        }

    );
    function ajaxPost(){
        const formData = {
            ownerEmail:$("#tweet-email").val(),
            content:$("#content").val()
        }
        $.ajax({
            type: "POST",
            contentType:"application/json",
            url:"../api/v1/tweets",
            data:JSON.stringify(formData),
            dataType:'json',
            success:function (result){
                if (result.ownerEmail){
                    $("#tweet-result").html(
                        "Just tweeted!</p>"
                    );
                }else{
                    $("#tweet-result").html("<strong>Error</strong>");
                }
                console.log(result.ownerEmail);
            },
        })
    }

    }

)