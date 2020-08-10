package com.edukite.dto;

import java.util.List;

import org.springframework.data.domain.Page;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaginationResponseDto<T> {
	private int size;
	private long totalElements;
	private int totalPages;
	private int numberOfElements;
	private int curentPage;
	private List<T> data;

	public static <T> PaginationResponseDto<T> buildPaginationResponseDto(Page<T> page) {

		return PaginationResponseDto.<T>builder().totalPages(page.getTotalPages())
				.totalElements(page.getTotalElements())
				.size(page.getSize())
				.curentPage(page.getNumber()+1)
				.numberOfElements(page.getNumberOfElements())
				.data(page.getContent())
				.build();

	}

}
