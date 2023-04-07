package com.alkimi.vo;

import java.time.LocalDate;

import com.alkimi.entities.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductVO {

	private int productId;

	private String productName;

	private String productDescription;

	private String productCategory;

	private String productImage;

	private String productVideo;
	
	private LocalDate createdOn;

	private String createdBy;

	private LocalDate updatedOn;

	private String updatedBy;
	
	public ProductVO(Product product) {
		this.productId = product.getProductId();
		this.productName = product.getProductName();
		this.productCategory = product.getProductCategory();
		this.productImage = product.getProductImage();
		this.productVideo = product.getProductVideo();
		this.productDescription = product.getProductDescription();
		this.createdOn = product.getCreatedOn();
		this.createdBy = product.getCreatedBy();
		this.updatedOn = product.getUpdatedOn();
		this.updatedBy = product.getUpdatedBy();
	}
	
	
	@JsonIgnore
	public Product getProduct() {
		Product product = new Product();
		product.setProductId(productId);
		product.setProductName(productName);
		product.setProductCategory(productCategory);
		product.setProductImage(productImage);
		product.setProductVideo(productVideo);
		product.setProductDescription(productDescription);
		
		product.setCreatedOn(createdOn);
		product.setCreatedBy(createdBy);
		product.setUpdatedOn(updatedOn);
		product.setUpdatedBy(updatedBy);
		return product;
	}


}
	
	

