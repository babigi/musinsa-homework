package kr.co.musinsa.cooper.musinsahomework.product.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@ToString
public class CategoryLowestPriceProductResponseDto implements Serializable {

    private final String category;
    private final String brand;
    private final BigDecimal price;

    @QueryProjection
    public CategoryLowestPriceProductResponseDto(String category, String brand, BigDecimal price) {
        this.category = category;
        this.brand = brand;
        this.price = price;
    }

}
