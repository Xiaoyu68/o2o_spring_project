/**
 *
 **/
$(function() {
    var shopId = getQueryString('shopid');
    var isEdit = shopId ? true : false;
    var initUrl = '/shopadmin/getshopinitinfo';
    var registerShopUrl = '/shopadmin/registershop';
    var shopInfoUrl = "/o2o/shopadmin/getshopbyid?shopId=" + shopId;
    var editShopUrl = '/o2o/shopadmin/modifyshop';
    alert(initUrl);
    console.log('1');
    if(!isEdit)  {
        getShopInitInfo();

    } else {
        getShopInfo(shopId);
    }

    function getShopInfo(shopId) {
        $.getJSON(shopInfoUrl, function (data) {
            if (data.success) {
                var shop = data.shop;
                $('#shop-name').val(shop.shopName);
                $('#shop-address').val(shop.shopAddress);
                $('#phone').val(shop.phone);
                $('#shop-desc').val(shop.Desc);
                var shopCategory = '<option data-id="'
                    + shop.shopCategoty.shopCategotyId + '"selected>'
                    + shop.shopCategoty.shopCategoryName + '</option>';
                var tempAreaHtml = '';
                data.areaList.map(function (item, index) {
                    tempAreaHtml += '<option data-id="' + item.areaId + '">'
                        + item.areaName + '</option>';
                });
                $('#shop-category').html(shopCategory);
                $('#shop-category').attr('disabled', 'disabled');
                $('#area').html(tempAreaHtml);
                $("#area option[data-id='" + shop.area.areaId + "']").attr('selected', 'selected');
            }
        });
    }

            function getShopInitInfo() {
                $.getJSON(initUrl, function (data) {
                    if (data.success) {
                        var tempHtml = '';
                        var tempAreaHtml = '';
                        data.shopCategoryList.map(function (item, index) {
                            tempHtml += '<option data-id="' + item.shopCategoryId + '">'
                                + item.shopCategoryName + '</option>';
                        });
                        data.areaList.map(function (item, index) {
                            tempAreaHtml += '<option data-id="' + item.areaId + '">'
                                + item.areaName + '</option>';
                        });
                        $('#shop-category').html(tempHtml);
                        $('#area').html(tempAreaHtml);
                    }
                });
                $('#submit').click(function () {
                    var shop = {};
                    if(isEdit) {
                        shop.shopId = shopId;
                    }
                    shop.shopName = $('#shop-name').val();
                    shop.shopAddress = $('#shop-address').val();
                    shop.phone = $('#phone').val();
                    shop.shopCategoty = {
                        shopCategotyId: $('#shop-category').find('option').not(function () {
                            return !this.selected;
                        }).data('id')
                    };
                    shop.area = {
                        areaId: $('#shop-area').find('option').not(function () {
                            return !this.selected;
                        }).data('id')
                    };
                    var shopImg = $('#shop-img')[0].files[0];
                    var formData = new FormData();
                    formData.append('shopImg', shopImg);
                    formData.append('shopStr', JSON.stringify(shop));
                    $.ajax({
                        url: registerShopUrl,
                        type: 'POST',
                        data: formData,
                        contentType: false,
                        processData: false,
                        cache: false,
                        success: function (data) {
                            if (data.success) {
                                $.toast('success');
                            } else {
                                $.toast('failed' + data.errMsg);
                            }
                        }
                    })
                });
            }
        })