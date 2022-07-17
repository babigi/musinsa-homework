package kr.co.musinsa.cooper.musinsahomework.product.application;

import kr.co.musinsa.cooper.musinsahomework.product.domain.CategoryMaxProductRepository;
import kr.co.musinsa.cooper.musinsahomework.product.domain.CategoryMinProductRepository;
import kr.co.musinsa.cooper.musinsahomework.product.dto.BrandMinAndMaxPriceResponseDto;
import kr.co.musinsa.cooper.musinsahomework.product.dto.BrandPriceResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class BrandMinAndMaxPriceServiceTest {

    private BrandMinAndMaxPriceService brandMinAndMaxPriceService;

    @Mock
    private CategoryMinProductRepository categoryMinProductRepository;

    @Mock
    private CategoryMaxProductRepository categoryMaxProductRepository;

    @BeforeEach
    void init() {
        brandMinAndMaxPriceService = new BrandMinAndMaxPriceServiceImpl(
                categoryMinProductRepository,
                categoryMaxProductRepository
        );
    }

    @DisplayName("각 카테고리의 이름으로 최대, 최대 가격을 조회한다")
    @Test
    void getCategoryMinAndMaxPrice() {
        //given
        BrandPriceResponseDto minBrandPrice
                = new BrandPriceResponseDto("C", new BigDecimal("10000.00"));

        BrandPriceResponseDto maxBrandPrice
                = new BrandPriceResponseDto("I", new BigDecimal("11400.00"));

        given(categoryMinProductRepository.getCategoryMinProduct(any())).willReturn(minBrandPrice);
        given(categoryMaxProductRepository.getCategoryMaxProduct(any())).willReturn(maxBrandPrice);

        //when
        BrandMinAndMaxPriceResponseDto categoryMinAndMaxPrice
                = brandMinAndMaxPriceService.getCategoryMinAndMaxPrice(1L);

        //then
        assertAll(
                () -> assertThat(categoryMinAndMaxPrice.getMinBrandPrice()).isEqualTo(minBrandPrice),
                () -> assertThat(categoryMinAndMaxPrice.getMaxBrandPrice()).isEqualTo(maxBrandPrice)
        );

    }

}