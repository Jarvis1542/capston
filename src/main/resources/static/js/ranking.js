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
                "                            <img src="+msg[i].scs_PIC+" alt=\"\" style=\"width:240px; height:200px; \">\n" +
            "                                <div class=\"quote_icon\">\n" +
            "                                    <i class=\"Flaticon flaticon-quote\"></i>\n" +
            "                                </div>\n" +
            "                        </div>\n" +
            "                        <div class=\"info\">\n" +
            "                            <p>충전소 인기 "+(i+1)+"위</p>\n" +
            "                            <span>" + msg[i].road_ADDR + "<br>\n" +    // ADDR 문자는 대문자로 JS에 넘어오니 주의(대소문자 구분 필수)
            "                                " + msg[i].jibun_ADDR + "<br><br>\n" +
            "                                " + msg[i].phone + "</span>\n" +
            "                        </div>\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "            </div>\n" +
            "        </div>");

            rank_arr[i] = msg[i];
        }
        // review-active
        var testmonial = $('.testmonial_active2');
        if(testmonial.length){
            testmonial.owlCarousel({
                loop:true,
                margin:0,
                autoplay:true,
                navText:['<i class="ti-angle-left"></i>','<i class="ti-angle-right"></i>'],
                nav:true,
                dots:false,
                autoplayHoverPause: true,
                autoplaySpeed: 800,
                responsive:{
                    0:{
                        items:1,
                        dots:false,
                        nav:false,
                    },
                    767:{
                        items:1,
                        dots:false,
                        nav:false,
                    },
                    992:{
                        items:1,
                        nav:true
                    },
                    1200:{
                        items:1,
                        nav:true
                    },
                    1500:{
                        items:1
                    }
                }
            });
        }
        console.log("rank_arr",rank_arr);
        console.log("rank_arr1", rank_arr[0].scs_PIC);
        console.log("JIIIII", rank_arr[0].jibun_addr);
    }).fail(function (error) {
        console.log("에러 : ", error);
    });
});