package com.edukite.exception;

public enum ErrorMessage {
	BAD_REQUEST(400, "bad_request"),

	INVALID_PARAM(400001, "invalid_input_params"),

	INVALID_DATE_FORMAT(400002, "invalid_date_format"),

	CONTRAINS_EXCEPTION(400003, "duplicate key value violates unique constraint"),

	FORBIDDEN_API(40301, "cannot_access_api"),

	NOT_FOUND(404, "resource_not_found"),

	UNSUPPORTED_MEDIA_TYPE(415000, "Unsupported_Media_Type"),

	;

	/** The error code. */
	private int errorCode;

	/** The message. */
	private String message;

	/**
	 * Instantiates a new error message.
	 * 
	 * @param pCode    the code
	 * @param pMessage the message
	 */
	ErrorMessage(int pCode, String pMessage) {
		errorCode = pCode;
		message = pMessage;
	}

	/**
	 * Gets the code.
	 * 
	 * @return the code
	 */
	public int getCode() {
		return errorCode;
	}

	/**
	 * Gets the message.
	 * 
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
}
