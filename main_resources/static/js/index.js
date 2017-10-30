var viewmodel = avalon.define({
    //id必须和页面上定义的ms-controller名字相同，否则无法控制页面
    $id: "viewmodel",
    datalist: {},
    text: "请求数据",
    request: function () {
        $.ajax({
            type: "post",
            url: "/user/list",    //向springboot请求数据的url
            data: {},
            success: function (data) {
                $('button').removeClass("btn-primary").addClass("btn-success").attr('disabled', true);
                viewmodel.datalist = data;
                viewmodel.text = "数据请求成功，已渲染";
            }
        });
    }
});