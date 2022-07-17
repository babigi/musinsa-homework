package kr.co.musinsa.cooper.musinsahomework.product.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class BrandMinAndMaxPriceResponseDto {

    private final BrandPriceResponseDto minBrandPrice;
    private final BrandPriceResponseDto maxBrandPrice;

    public static BrandMinAndMaxPriceResponseDto create(BrandPriceResponseDto minBrandPrice, BrandPriceResponseDto maxBrandPrice) {
        return new BrandMinAndMaxPriceResponseDto(minBrandPrice, maxBrandPrice);
    }

}
