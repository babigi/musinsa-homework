package kr.co.musinsa.cooper.musinsahomework.product.domain;

import kr.co.musinsa.cooper.musinsahomework.product.dto.CategoryLowestPriceProductResponseDto;

import java.util.List;

public interface AllCategoriesLowestPriceProductSearchRepository {

    List<CategoryLowestPriceProductResponseDto> searchAllCategoriesLowestPriceProduct();

}
