package com.ppc.ffs.web.util;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

/**
 * API 요청 응답 데이터를 규격에 맞게 처리하는 클래스
 */
public class APIResponse {


    /**
     * API 호츨 성공 응답.
     * @param jsonString
     * @return
     */
    public static ResponseEntity<String> success(String jsonString){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","application/json; charset=UTF-8");
        if( jsonString == null){
            return new ResponseEntity<>(headers, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(jsonString, headers, HttpStatus.OK);
        }
    }

    public static ResponseEntity<Object> success(Object object){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","application/json; charset=UTF-8");
        if( object == null){
            return new ResponseEntity<>(headers, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(object, headers, HttpStatus.OK);
        }
    }

    public static ResponseEntity<Object> success(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","application/json; charset=UTF-8");
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

//    /**
//     * API 호출 에러 응답
//     * @param error
//     * @return
//     */
    public static ResponseEntity<Object> error(APIError error) {
        return error(error.getErrorCode(),error.getErrorMessage(),error.getHttpStatus());
    }

    public static ResponseEntity<Object> error(int code, String errorMessage,int httpStatus){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Content-Type","application/json;charset=UTF-8");
//        JSONObject jsonError = new JSONObject();
//        jsonError.put("errorCode",code);
//        jsonError.put("errorMessage",errorMessage);
//        return new ResponseEntity<>(jsonError.toString(), headers, HttpStatus.valueOf(httpStatus));
        return new ResponseEntity<>("", headers, HttpStatus.valueOf(httpStatus));
    }
}
