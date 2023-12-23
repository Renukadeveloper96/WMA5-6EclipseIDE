package com.minton.userservice.payload;

import java.util.List;

import lombok.Data;

@Data
public class PagedResponseDto<T> {
	private int page;
	private int pageSize;
	private Long totalCount;
	private List<T> list;

}
