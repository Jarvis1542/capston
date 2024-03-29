$(function() {
    var rank_arr = [], rank_arr2 = [];

    $.ajax({
        method: "get",
        url: "/rest/rankingSCS"
    }).done(function (msg) {
        for (var i = 0; i < msg.length; i++){
            $(".ranking").append("" +
                "<div class=\"single_carousel\">\n" +
                "            <div class=\"row\">\n" +
                "                <div class=\"col-lg-11\">\n" +
                "                    <div class=\"single_testmonial d-flex align-items-center\">\n" +
                "                        <div class=\"thumb\">\n" +
                "                            <img src=" + msg[i].scs_PIC + " alt=\"\" style=\"width:210px; height:200px; \">\n" +
                "                                <div class=\"rankingIcon\">\n" +
                "                                    <img src=\"../img/ranking.png\" style=\"width:60px;\" />\n" +

                "                                </div>\n" +
                "                        </div>\n" +
                "                        <div class=\"info\">\n" +
                "                            <p>충전소 랭킹 TOP " + (i + 1) + ", " + msg[i].scs_NAME + "</p>\n" +
                "                            <span>" + msg[i].road_ADDR + ", " + msg[i].detail_ADDR + "<br><br>" +    // ADDR 문자는 대문자로 JS에 넘어오니 주의(대소문자 구분 필수)
                "                                "+ "TEL." + msg[i].phone + "</span>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </div>");

            rank_arr[i] = msg[i];

        }

        rankingHp();
    }).fail(function (error) {
        console.log("에러 : ", error);
    });

    function rankingHp() {
        $.ajax({
            method: "get",
            url: "/rest/rankingHP"
        }).done(function (msg) {
            for (var i = 0; i < msg.length; i++) {
                $(".ranking").append("" +
                    "<div class=\"single_carousel\">\n" +
                    "            <div class=\"row\">\n" +
                    "                <div class=\"col-lg-11\">\n" +
                    "                    <div class=\"single_testmonial d-flex align-items-center\">\n" +
                    "                        <div class=\"thumb\">\n" +
                    "                            <img src=" + msg[i].hp_PIC + " alt=\"\" style=\"width:210px; height:200px; \">\n" +
                    "                                <div class=\"rankingIcon\">\n" +
                    "                                    <img src=\"../img/ranking.png\" style=\"width:60px;\" />\n" +

                    "                                </div>\n" +
                    "                        </div>\n" +
                    "                        <div class=\"info\">\n" +
                    "                            <p>주차장 랭킹 TOP " + (i + 1) + ", " + msg[i].hp_NAME + "</p>\n" +
                    "                            <span>" + msg[i].road_ADDR + ", " + msg[i].detail_ADDR + "<br><br>" +    // ADDR 문자는 대문자로 JS에 넘어오니 주의(대소문자 구분 필수)
                    "                                " + "TEL." + msg[i].phone + "</span>\n" +
                    "                        </div>\n" +
                    "                    </div>\n" +
                    "                </div>\n" +
                    "            </div>\n" +
                    "        </div>");
                rank_arr2[i] = msg[i];
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

        }).fail(function (error) {
            console.log("에러 : ", error);
        });
    }



});
