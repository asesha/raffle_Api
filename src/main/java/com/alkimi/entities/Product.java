package com.alkimi.entities;

import javax.persistence.*;

import com.alkimi.vo.ProductVO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Table(name="PRODUCT")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	
	public Product(int productId) {
		this.productId = productId;
	}
	
	public Product(ProductVO vo) {
		this.productId = vo.getProductId();
		this.productName = vo.getProductName();
		this.productCategory = vo.getProductCategory();
		this.productImage = vo.getProductImage();
		this.productVideo = vo.getProductVideo();
		this.productDescription = vo.getProductDescription();
		this.createdOn = vo.getCreatedOn();
		this.createdBy = vo.getCreatedBy();
		this.updatedOn = vo.getUpdatedOn();
		this.updatedBy = vo.getUpdatedBy();
	}

	@Id
	@Column(name="PRODUCT_ID")
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int productId;

	@Column(name="PRODUCT_NAME")
	private String productName;

	@Column(name="CREATED_ON")
	private LocalDate createdOn;

	@Column(name="CREATED_BY")
	private String createdBy;

	@Column(name="UPDATED_ON")
	private LocalDate updatedOn;

	@Column(name="UPDATED_BY")
	private String updatedBy;

	@Column(name="PRODUCT_DESC")
	private String productDescription;

	@Column(name="PRODUCT_CATEGORY")
	private String productCategory;

	@Column(name="PRODUCT_IMAGE")
	private String productImage;

	@Column(name="PRODUCT_VIDEO")
	private String productVideo;
	
	@JsonIgnore
	public ProductVO getProductVO() {
		ProductVO vo = new ProductVO();
		vo.setProductId(productId);
		vo.setProductName(productName);
		vo.setProductCategory(productCategory);
		vo.setProductImage(productImage);
		vo.setProductVideo(productVideo);
		vo.setProductDescription(productDescription);
		
		vo.setCreatedOn(createdOn);
		vo.setCreatedBy(createdBy);
		vo.setUpdatedOn(updatedOn);
		vo.setUpdatedBy(updatedBy);
		return vo;
	}

	
}
	
	

