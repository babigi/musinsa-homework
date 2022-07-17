package kr.co.musinsa.cooper.musinsahomework.product.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class AllCategoriesOneBrandPriceResponseDto {

    private final String brandName;
    private final BigDecimal price;

    @QueryProjection
    public AllCategoriesOneBrandPriceResponseDto(String brandName, BigDecimal price) {
        this.brandName = brandName;
        this.price = price;
    }

}
