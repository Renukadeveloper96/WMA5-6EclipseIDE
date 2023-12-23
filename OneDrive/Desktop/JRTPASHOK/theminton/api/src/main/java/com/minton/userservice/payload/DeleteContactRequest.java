package com.minton.userservice.payload;

import lombok.Data;

import java.util.List;

@Data
public class DeleteContactRequest {

	private List<Long> contactIds;

}
