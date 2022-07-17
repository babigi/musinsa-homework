package kr.co.musinsa.cooper.musinsahomework.product.exception;

public class NotFoundCategoryException extends IllegalArgumentException {

    private static final String NOT_FOUND_CATEGORY_EXCEPTION_MESSAGE_FORMAT
            = "입력하신 카테고리가 존재하지 않습니다.(categoryId : %s)";

    public NotFoundCategoryException(Long categoryId) {
        super(String.format(NOT_FOUND_CATEGORY_EXCEPTION_MESSAGE_FORMAT, categoryId));
    }

}
