$(function () {
    var listUrl = "/o2o/shopadmin/getproductcategorylist";
    var addUrl = "/o2o/shopadmin/addproductcategorys";
    var deleteUrl = "/o2o/shopadmin/removeproductcategory";

    getList();

    function getList() {
        $.getJSON(
            listUrl,
            function (data) {
                if (data.success) {
                    var dataList = data.data;
                    $('.category-wap').html('');
                    var tempHtml = '';
                    dataList.map(function (item, index) {
                        tempHtml += ''
                            + '<div class="row row-product-category now">'
                            + '<div class="col-33 product-category-name">'
                            + item.productCategoryName
                            + '</div>'
                            + '<div class="col-33"><a href="#" class="button delete" data-id="'
                            + item.productCategoryId
                            + '">删除</a></div>'
                            + '</div>';
                    });
                    $('.category-wrap').append(tempHtml);
                }
            });
    }
    $('#new')
        .click(
            function () {
                var tempHtml = '<div class="row row-product-category temp">'
                +'<div class="col-33"><input class="category-input category" type="text" placeholder="">'
                +'<div class="col-33"><input class="category-input priority" type="number" placeholder="">'
                +'<div class="col-33"><a href="#" class="button-delete">delete</a></div>'
                    +'</div>';
                $('.category-wrap').append(tempHtml);
            });
    $('#submit').click(function (){
        var tempArr = $('.temp');
        var productCategoryList = [];
        tempArr.map(function (index, item){
            var tempObj = {};
            tempObj.productCategoryName = $(item).find('.category').val();
            tempObj.priority = $(item).find('.priority').val();
            if(tempObj.productCategoryName && tempObj.priority) {
                productCategoryList.push(tempObj);
            }
        });
        $.ajax({
            url:addUrl,
            type:'POST',
            data:JSON.stringify(productCategoryList),
            contentType:'application/json',
            success:function (data) {
                if(data.success) {
                    $.toast('success');
                    getList();
                } else {
                    $.toast('failed');
                }
            }
        });
        });
    $('.category-wrap').on('click','.row-product-category.temp.delete',
        function (e) {
        console.log($(this).parent().parent());
        $(this).parent().parent().remove(); //这个是 去找到最上面的div
        });
    $('.category-wrap').on('click','.row-product-category.now.delete',
        function (e){
        var target = e.currentTarget;
        $.confirm('Sure?',function (){
            $.ajax({
                url:deleteUrl,
                type: 'POST',
                data:{
                    productCategoryId:target.dataset.id
                },
                dataType:'json',
                success:function (data) {
                    if(data.success) {
                        $.toast('success');
                        getList();
                    } else {
                        $.toast('faild')
                    }
                }
            });
        });
        });
})