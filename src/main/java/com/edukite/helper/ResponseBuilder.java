package com.edukite.helper;

import java.util.List;

import com.edukite.exception.ApiException;
import com.edukite.exception.ErrorDetail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseBuilder<T> {
	private int code;
	private String message;
	private T data;
	
	public static <K> ResponseBuilder<K> buildResponse(K data) {
		ResponseBuilder<K> response = new ResponseBuilder<K>();
		response.data = data;
		return response;
	}
	
	public static <K> ResponseBuilder<K>  buildApplicationException(ApiException apiEx) {
		ResponseBuilder<K> response = new ResponseBuilder<K>();
		response.code = apiEx.getErrorMessage().getCode();
		response.message = apiEx.getErrorMessage().getMessage();
        return response;
    }

    public static <K> ResponseBuilder<K>  buildApplicationException(String exMessage, int exCode) {        
        ResponseBuilder<K> response = new ResponseBuilder<K>();
		response.code = exCode;
		response.message = exMessage;
		return response;
	}

	public static ResponseBuilder<List<ErrorDetail>> buildApplicationExceptionV2(ApiException apiEx) {
		ResponseBuilder<List<ErrorDetail>> response = new ResponseBuilder<>();
		response.code = apiEx.getErrorMessage().getCode();
		response.message = apiEx.getErrorMessage().getMessage();
		if (apiEx.getErrorDetails() != null && !apiEx.getErrorDetails().isEmpty())
			response.setData(apiEx.getErrorDetails());
		return response;
	}
}
