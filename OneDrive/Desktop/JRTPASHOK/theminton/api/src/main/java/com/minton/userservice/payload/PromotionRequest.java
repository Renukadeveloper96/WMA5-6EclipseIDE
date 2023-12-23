package com.minton.userservice.payload;

import lombok.Data;

@Data
public class PromotionRequest {

	private String promotionName;
	
	private String promotionText;

	private String image;
	
	private String imageBase64;

	private String url;

	private String tags;

}
