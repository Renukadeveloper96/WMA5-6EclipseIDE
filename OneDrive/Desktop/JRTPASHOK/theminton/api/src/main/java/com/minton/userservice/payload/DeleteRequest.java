package com.minton.userservice.payload;

import lombok.Data;

import java.util.List;

@Data
public class DeleteRequest {

	private List<Long> ids;

}
