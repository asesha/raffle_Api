package com.alkimi.vo;

import java.time.LocalDate;

public interface PrizeDetails {
	
	int getPrizePosition();
	int getProductId();
	String getProductName();
	String getProductImage();
	String getProductVideo();
	String getRaffleName();
	LocalDate getStartDate();
	LocalDate getEndDate();

}
