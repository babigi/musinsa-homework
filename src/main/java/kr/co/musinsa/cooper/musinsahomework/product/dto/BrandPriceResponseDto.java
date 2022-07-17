package kr.co.musinsa.cooper.musinsahomework.product.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class BrandPriceResponseDto {

    private final String brandName;
    private final BigDecimal price;

    @QueryProjection
    public BrandPriceResponseDto(String brandName, BigDecimal price) {
        this.brandName = brandName;
        this.price = price;
    }

}
