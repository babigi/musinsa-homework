package kr.co.musinsa.cooper.musinsahomework.product.domain;

import kr.co.musinsa.cooper.musinsahomework.product.dto.BrandPriceResponseDto;

public interface CategoryMinProductRepository {

    BrandPriceResponseDto getCategoryMinProduct(String categoryName);

}
