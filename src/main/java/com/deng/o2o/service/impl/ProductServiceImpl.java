package com.deng.o2o.service.impl;

import com.deng.o2o.dao.ProductDao;
import com.deng.o2o.dao.ProductImgDao;
import com.deng.o2o.dto.ImageHolder;
import com.deng.o2o.dto.ProductExecution;
import com.deng.o2o.entity.Product;
import com.deng.o2o.entity.ProductImg;
import com.deng.o2o.enums.ProductStateEnum;
import com.deng.o2o.exceptions.ProductOperationException;
import com.deng.o2o.service.ProductService;
import com.deng.o2o.util.ImageUtil;
import com.deng.o2o.util.PageCalculator;
import com.deng.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductImgDao productImgDao;

    /**
     * 1.处理缩略图，处理缩略图相对路径，并赋值给product
     * 2.往tb_product写入商品信息，获取productId
     * 3.结合productId批量处理商品详情图
     * 4.将商品详情图列表批量插入tb——product_img中
     *
     * @param product
     * @param imageHolder
     * @param imageHolderList
     * @return
     * @throws ProductOperationException
     */
    @Override
    @Transactional
    public ProductExecution addProduct(Product product, ImageHolder imageHolder, List<ImageHolder> imageHolderList) throws ProductOperationException {
        if (product != null && product.getShop() != null && product.getShop().getShopId() > 0) {
            product.setCreateTime(new Date());
            product.setLastEditTime(new Date());

            product.setEnableStatus(1);

            if (imageHolder != null) {
                addThumbnail(product, imageHolder);
            }
            try {
                int effectedNum = productDao.insertProduct(product);
                if (effectedNum <= 0) {
                    throw new ProductOperationException("failed");
                }
            } catch (Exception e) {
                throw new ProductOperationException(e.getMessage());
            }
            if (imageHolderList != null && imageHolderList.size() > 0) {
                addProductImgList(product, imageHolderList);
            }
            return new ProductExecution(ProductStateEnum.SUCCESS);
        } else {
            return new ProductExecution(ProductStateEnum.NULL_PRODUCT);
        }
    }

    private void addThumbnail(Product product, ImageHolder thumbnail) {
        String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
        String thumbnailAddr = ImageUtil.generateThumbnail(thumbnail.getImage(),thumbnail.getImageName(),dest);
        product.setImgAdress(thumbnailAddr);
    }

    private  void addProductImgList(Product product, List<ImageHolder> imageHolderList) {
        String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
        List<ProductImg> productImgList = new ArrayList<>();
        for(ImageHolder imageHolder:imageHolderList) {
            String imgAddr = ImageUtil.generateNormalImg(imageHolder.getImage(),imageHolder.getImageName(),dest);
            ProductImg productImg = new ProductImg();
            productImg.setImgAddr(imgAddr);
            productImg.setProductId(product.getProductId());
            productImgList.add(productImg);
        }
        if(productImgList.size() > 0) {
            try {
                int effectedNum = productImgDao.batchInsertProductImg(productImgList);
                if(effectedNum <= 0) {
                    throw new ProductOperationException("failed");
                }
            } catch (Exception e) {
                throw new ProductOperationException(e.toString());
            }
        }
    }

    @Override
    public Product getProductById(long productId) {
        return productDao.queryProductById(productId);
    }

    @Override
    @Transactional
    public ProductExecution modifyProduct(Product product, ImageHolder imageHolder, List<ImageHolder> imageHolderList) throws ProductOperationException {
        if(product != null && product.getShop() != null && product.getShop().getShopId() != null) {
            product.setLastEditTime(new Date());
            if(imageHolder != null) {
                Product tempProduct = productDao.queryProductById(product.getProductId());
                if(tempProduct.getImgAdress() != null) {
                    ImageUtil.deleteFileOrPath(tempProduct.getImgAdress());
                }
                addThumbnail(product, imageHolder);
            }
            if(imageHolderList != null && imageHolderList.size() > 0) {
                deleteProductImgList(product.getProductId());
                addProductImgList(product,imageHolderList);
            }
            try {
                int effectNum = productDao.updateProduct(product);
                if(effectNum <= 0) {
                    throw new ProductOperationException("failed");
                }
                return new ProductExecution(ProductStateEnum.SUCCESS,product);
            } catch (Exception e){
                throw new ProductOperationException(e.toString());
            }
        } else {
            return new ProductExecution(ProductStateEnum.NULL_PRODUCT);
        }
    }

    private void deleteProductImgList(long productId) {
        List<ProductImg> productImgList = productImgDao.queryProductImgList(productId);
        for( ProductImg productImg:productImgList) {
            ImageUtil.deleteFileOrPath(productImg.getImgAddr());
        }
        productImgDao.deleteProductImgByProductId(productId);
    }

    @Override
    public ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize) {
        int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
        List<Product> productList = productDao.queryProductList(productCondition, rowIndex, pageSize);

        int count = productDao.queryProductCount(productCondition);
        ProductExecution pe = new ProductExecution();
        pe.setProductList(productList);
        pe.setCount(count);
        return pe;
    }
}
