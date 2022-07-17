package kr.co.musinsa.cooper.musinsahomework.product.exception.handler;

import kr.co.musinsa.cooper.musinsahomework.common.ApiResult;
import kr.co.musinsa.cooper.musinsahomework.product.exception.NotFoundCategoryException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class NotFoundCategoryExceptionHandler {

    @ExceptionHandler(NotFoundCategoryException.class)
    public ResponseEntity<ApiResult<Void>> handlerNotFoundCategoryException(
            NotFoundCategoryException notFoundCategoryException
    ) {
        ApiResult<Void> apiResult = ApiResult.fail(HttpStatus.BAD_REQUEST, notFoundCategoryException.getMessage());
        return ResponseEntity.badRequest().body(apiResult);
    }

}
