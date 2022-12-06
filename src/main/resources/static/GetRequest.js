$(document).ready(
    function () {
        $("#user-list").submit(function (e){
            e.preventDefault()
            if (e.originalEvent.submitter.id === "show-tweet"){
                ajaxGet();
            }else if (e.originalEvent.submitter.id === "show-all-tweet"){
                ajaxGetAll();
            }
            }
        );
        function ajaxGet(){

            const tbody = $('#user-count tr');
            $('#tweet-body tr').remove();
            let ind=0;
            tbody.each(function (){
                if ($(`#checkbox${ind}`).is(':checked')){
                    $.ajax({
                        type:"GET",
                        url:`../api/v1/tweets/owner/${$(`#email${ind}`).html()}`,
                        success:function (result){
                            if (result){
                                console.log(result)
                                result.forEach((x)=>{
                                    $('#tweet-table > tbody:last-child').append('<tr>' +
                                        `<td>${x.ownerEmail}</td>` +
                                        `<td>${x.content}</td>`+
                                        '</tr>')

                                });
                            }
                        }
                    })
                }

                ind++;
            })


        }
        function ajaxGetAll(){
            $.ajax({
                type: "GET",
                url:"../api/v1/tweets",
                success:function (result){
                    console.log(result);
                    result.forEach((x)=>{
                        $('#tweet-table > tbody:last-child').append('<tr>' +
                            `<td>${x.ownerEmail}</td>` +
                            `<td>${x.content}</td>`+
                            '</tr>')

                    });


                }
            })
        }
    }
)

