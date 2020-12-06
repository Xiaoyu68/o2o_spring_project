$(function (){
    var loading = false;
    var maxItems = 999;
    var pageSize = 10;
    var listUrl = '/o2o/frontend/listshops';
    var searchDivUrl = '/o2o/frontend/listshopspageinfo';
    var pageNum = 1;
    var parentId = getQueryString('parentId');
    var areaId= '';
    var shopCategoryId = '';
    var shopName = '';

    getSearchDivData();
    addItems(pageSize, pageNum);

    function getSearchDivData() {
        var url = searchDivUrl + '?' + 'parentId=' + parentId;
        $.getJSON(
            url,
            function (data) {
                if (data.success) {
                    var shopCategoryList = data.shopCategoryList;
                    var html = '';
                    html += '<a href = "#" class="button" data-category-id="">全部类别</a>';
                    shopCategoryList
                        .map(function (item, index) {
                            html += '<a href="#" class="button" data-category-id='
                                + item.shopCategoryId
                                + '>'
                                + item.shopCategoryName
                                + '</a>';
                        });
                    $('#shoplist-search-div').html(html);
                    var selectOptions = '<option value="">全部街道</option>';
                    var areaList = data.areaList;
                    areaList.map(function (item, index) {
                        selectOptions += '<option value="'
                            + item.areaId + '">'
                            + item.areaName + '</option>';
                    });
                    $('#area-search').html(selectOptions);
                }
            });
    }
    function addItems(pageSize, pageIndex) {
        var url = listUrl + '?' + 'pageIndex=' + pageIndex + '&pageSize='
        +pageSize + '&parentId=' + parentId + '&areaId=' + areaId
        +'&shopCategoryId=' + shopCategoryId + '&shopName' + shopName;

        loading = true;

        $.getJSON(url, function (data){
            if(data.success) {
                maxItems = data.count;
                var html = '';
                data.shopList.map(function (item, index){
                    html += '' + '<div class = "card" data-shop-id="'
                    +item.shopId + '">' + '<div class="card-header">'
                    +item.shopName + '</div>'
                    +'<div class="card-content">'
                    +'<div class="list-block media-list">' + '<ul>'
                        +'<li class="item-content"> '
                    +'<div class="item-media">' + '<img src="'
                    +item.shopImg + '"width="44">' + '</div>'
                    +'<div class="item-inner">'
                    +'<div class="item-subtitle">' + item.shopDesc
                    +'</div>' + '</div>' + '</li>' + '</ul> '+ '</div>'
                    +'</div>' + '</div>' + '<div class="card-footer">'
                    +'<p class="color-gray">'
                    + new Date(item.lastEditTime).Format("yyyy-MM-dd")
                    +'更新</p>' + '<span>点击查看</span>' + '</div>'
                    + '</div>';
                });
                $('.list-div').append(html);
                var total = $('.list-div.card').length;
                if(total >= maxItems) {
                    $.detachInfiniteScroll($('.infinite-scroll'));
                    $('.infinite-scoll-preloader').remove();
                }
                pageNum += 1;

                loading = false;

                $.refreshScroller();
            }
        });
    }
    $(document).on('infinite','.inline-scroll-bottom',function (){
        if(loading)
            return;
        addItems(pageSize,parentNum);
    });

    $('.shop-list').on()
});
        $(function (){
    getlist();
    function getlist(e) {
        $.ajax({
            url:"/o2o/shopadmin/getshoplist",
            type:'get',
            dataType:'json',
            success:function (data) {
                if(data.success) {
                    handleList(data.shopList);
                    handleUser(data.user);

                }
            }
        });
    }
    function handleUser(data) {
        $('#user-name').text(data.name);
    }
    function handleList(data) {
        var html = '';
        data.map(function (item, index){
            html += '<div class="row row-shop"><div class="col-40">'
            + item.shopName + '</div><div class="col-40">'
            +shopStatus(item.enableStatus)
            +'</div><div class="col-20">'
            +goShop(item.enableStatus, item.shopId) + '</div></div>';
        });
        $('.shop-wrap').html(html);
    }
    function shopStatus(status) {
        if(status == 0) {
            return 'checking';
        } else if(status == -1) {
            return 'invalid';
        } else if(status == 1) {
            return 'passed';
        }
    }
    function goShop(status, id) {
        if(status == 1) {
            return '<a href = "/o2o/shopadmin/shopmanagement?shopId=' + id + '">enter</a>';
        } else {
            return '';
        }
    }
});