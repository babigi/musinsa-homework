package kr.co.musinsa.cooper.musinsahomework.product.application;

import kr.co.musinsa.cooper.musinsahomework.product.dto.BrandMinAndMaxPriceResponseDto;

public interface BrandMinAndMaxPriceService {

    BrandMinAndMaxPriceResponseDto getCategoryMinAndMaxPrice(Long categoryId);

}
