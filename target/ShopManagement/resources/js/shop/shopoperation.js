/**
 *
 **/
$(function(){
  var initUrl = '/shopadmin/getshopinitinfo';
  var registerShopUrl='/shopadmin/registershop';
  alert(initUrl);
  console.log('1');
  getShopInitInfo();
  function getShopInitInfo(){
      $.getJSON(initUrl,function (data){
          if(data.success) {
              var tempHtml = '';
              var tempAreaHtml = '';
              data.shopCategoryList.map(function (item,index){
                  tempHtml += '<option data-id="' + item.shopCategoryId + '">'
                  +item.shopCategoryName + '</option>';
              });
              data.areaList.map(function (item,index){
                  tempAreaHtml += '<option data-id="' + item.areaId + '">'
                  +item.areaName + '</option>';
              });
              $('#shop-category').html(tempHtml);
              $('#area').html(tempAreaHtml);
          }
      });
      $('#submit').click(function (){
          var shop={};
          shop.shopName = $('#shop-name').val();
          shop.shopAddress = $('#shop-address').val();
          shop.phone = $('#phone').val();
          shop.shopCategoty = {
              shopCategotyId:$('#shop-category').find('option').not(function (){
                  return !this.selected;
              }).data('id')
      };
          shop.area = {
              shopAreaId:$('#shop-area').find('option').not(function (){
                  return !this.selected;
              }).data('id')
          };
          var shopImg = $('#shop-img')[0].files[0];
          var formData = new FormData();
          formData.append('shopImg',shopImg);
          formData.append('shopStr',JSON.stringify(shop));
          $.ajax({
              url:registerShopUrl,
              type:'POST',
              data:formData,
              contentType:false,
              processData:false,
              cache:false,
              success:function (data){
                  if(data.success){
                      $.toast('success');
                  } else{
                      $.toast('failed' + data.errMsg);
                  }
              }
          })
      });
  }
})