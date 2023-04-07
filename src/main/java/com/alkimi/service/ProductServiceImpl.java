package com.alkimi.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkimi.dao.ProductRepository;
import com.alkimi.entities.Product;
import com.alkimi.vo.ProductVO;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductRepository repository;

	@Override
	public List<ProductVO> getAllProduct() {
		List<Product> products = repository.findAll();
    	List<ProductVO> productVOList = products.stream().map(ProductVO::new).collect(Collectors.toList());
    	return productVOList;
	}

	@Override
	public ProductVO createProduct(ProductVO vo) {
		Product product = repository.save(vo.getProduct());
		return product.getProductVO();
	}
	
	@Override
	public List<ProductVO> createMultiProduct(List<ProductVO> productVOs){
		List<Product> productList = productVOs.stream().map(Product::new).collect(Collectors.toList());
		productList = repository.saveAll(productList);
		productVOs = productList.stream().map(ProductVO::new).collect(Collectors.toList());
		return productVOs;
	}

	@Override
	public ProductVO getProductById(int productId) {
		Optional<Product> product = repository.findById(productId);
		return product.get().getProductVO();
	}

	@Override
	public ProductVO updateProduct(int productId, ProductVO vo) {
		Optional<Product> product = repository.findById(productId);
		Product modifiedProduct = null;
		if(product.get() != null) {
			modifiedProduct = product.get();
			modifiedProduct.setUpdatedOn(LocalDate.now());
			modifiedProduct = repository.save(modifiedProduct);
	    }
		return modifiedProduct.getProductVO();
	}

	@Override
	public ProductVO deleteProduct(int productId) {
		Optional<Product> product = repository.findById(productId);
		if(product.get() != null) {
			repository.delete(product.get());
	    }
		return product.get().getProductVO();
	}

}
