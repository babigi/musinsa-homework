package kr.co.musinsa.cooper.musinsahomework.product.domain;

import kr.co.musinsa.cooper.musinsahomework.product.dto.BrandPriceResponseDto;

public interface CategoryMaxProductRepository {

    BrandPriceResponseDto getCategoryMaxProduct(Long categoryId);

}
