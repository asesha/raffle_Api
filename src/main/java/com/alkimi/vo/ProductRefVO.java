package com.alkimi.vo;

import com.alkimi.entities.Product;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

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
@JsonInclude(Include.NON_NULL)
public class ProductRefVO {
	private int productId;
	private String productName;
	
	public ProductRefVO(Product product) {
		this.productId = product.getProductId();
		this.productName = product.getProductName();
	}
	
	public ProductRefVO(int productId) {
		this.productId = productId;
	}
}
	
	

