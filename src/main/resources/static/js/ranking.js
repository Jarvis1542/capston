$(function() {
    var rank_arr = [];
    $.ajax({
        method: "get",
        url: "/rest/ranking"
    }).done(function (msg) {
        for (var i = 0; i < msg.length; i++){
            $(".none_aaa").append("<div class=\"single_carousel\">\n" +
                "            <div class=\"row\">\n" +
                "                <div class=\"col-lg-11\">\n" +
                "                    <div class=\"single_testmonial d-flex align-items-center\">\n" +
                "                        <div class=\"thumb\">\n" +
                "                            <img src="+msg[i].scs_PIC+" alt=\"\">\n" +
            "                                <div class=\"quote_icon\">\n" +
            "                                    <i class=\"Flaticon flaticon-quote\"></i>\n" +
            "                                </div>\n" +
            "                        </div>\n" +
            "                        <div class=\"info\">\n" +
            "                            <p>BEST 1등 주차장</p>\n" +
            "                            <span>"+msg[i].road_ADDR+"<br>\n" +
            "                                대구광역시 북구 복현동 218(복현로 35)<br>\n" +
            "                                010-xxxx-xxxx</span>\n" +
            "                        </div>\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "            </div>\n" +
            "        </div>");
            rank_arr[i] = msg[i];
        }
        console.log("rank_arr",rank_arr);
        console.log("rank_arr1", rank_arr[0].scs_PIC);
    }).fail(function (error) {
        console.log("에러 : ", error);
    });
});