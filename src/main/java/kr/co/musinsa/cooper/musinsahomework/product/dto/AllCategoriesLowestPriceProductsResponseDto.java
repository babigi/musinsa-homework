package kr.co.musinsa.cooper.musinsahomework.product.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AllCategoriesLowestPriceProductsResponseDto implements Serializable {

    private final List<CategoryLowestPriceProductResponseDto> products;
    private final BigDecimal totalPrice;

    public static AllCategoriesLowestPriceProductsResponseDto create(
            List<CategoryLowestPriceProductResponseDto> categoryLowestPriceProductsResponseDto, BigDecimal totalPrice) {
        return new AllCategoriesLowestPriceProductsResponseDto(categoryLowestPriceProductsResponseDto, totalPrice);
    }

}
