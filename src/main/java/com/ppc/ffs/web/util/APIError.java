package com.ppc.ffs.web.util;

import org.json.JSONObject;

public enum APIError {
    REQUIRED_PARAMETER(101, "필수 파라미터가 누락됨", 400),
    INVALID_PARAMETER(102, "유효하지 않는 파라미터", 400),

    USER_NOT_FOUND(200, "사용자 정보를 찾을 수 없음", 400),

    // 시스템 관련
    SYSTEM_ERROR(500, "시스템 오류", 500),
    NOT_FOUND(404, "페이지를 찾을 수 없습니다.", 404);

    final private int errorCode;

    final private String errorMessage;

    final private int httpStatus;

    APIError(int errorCode, String errorMessage, int httpStatus) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public String getJSONString() {
        JSONObject jsonError = new JSONObject();
        jsonError.put("errorCode", this.getErrorCode());
        jsonError.put("errorMessage", this.getErrorMessage());
        return jsonError.toString();
    }
}

