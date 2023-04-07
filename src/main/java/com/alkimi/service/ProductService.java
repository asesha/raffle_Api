package com.alkimi.service;

import java.util.List;

import com.alkimi.vo.ProductVO;

public interface ProductService {
	public List<ProductVO> getAllProduct();
	public ProductVO createProduct(ProductVO vo);
	public List<ProductVO> createMultiProduct(List<ProductVO> productVOs);
	public ProductVO getProductById(int productId);
	public ProductVO updateProduct(int productId,ProductVO vo);
	public ProductVO deleteProduct(int productId);
}
