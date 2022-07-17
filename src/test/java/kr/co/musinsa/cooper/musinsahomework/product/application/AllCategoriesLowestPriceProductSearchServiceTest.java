package kr.co.musinsa.cooper.musinsahomework.product.application;

import kr.co.musinsa.cooper.musinsahomework.product.domain.AllCategoriesLowestPriceProductSearchRepository;
import kr.co.musinsa.cooper.musinsahomework.product.dto.AllCategoriesLowestPriceProductsResponseDto;
import kr.co.musinsa.cooper.musinsahomework.product.dto.CategoryLowestPriceProductResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AllCategoriesLowestPriceProductSearchServiceTest {

    private AllCategoriesLowestPriceProductSearchService allCategoriesLowestPriceProductSearchService;

    @Mock
    private AllCategoriesLowestPriceProductSearchRepository allCategoriesLowestPriceProductSearchRepository;

    @BeforeEach
    void init() {
        allCategoriesLowestPriceProductSearchService
                = new AllCategoriesLowestPriceProductSearchServiceImpl(allCategoriesLowestPriceProductSearchRepository);
    }

    @DisplayName("상품 가격 합계와 최저가 상품 리스트를 반환한다")
    @Test
    void searchAllCategoriesLowestPriceProducts() {
        //given
        List<CategoryLowestPriceProductResponseDto> categoryLowestPriceProductsResponseDto
                = List.of(
                new CategoryLowestPriceProductResponseDto("outer", "A", new BigDecimal(1000)),
                new CategoryLowestPriceProductResponseDto("top", "B", new BigDecimal(2000)),
                new CategoryLowestPriceProductResponseDto("pants", "C", new BigDecimal(3000))
        );

        when(allCategoriesLowestPriceProductSearchRepository.searchAllCategoriesLowestPriceProduct())
                .thenReturn(categoryLowestPriceProductsResponseDto);

        //when
        AllCategoriesLowestPriceProductsResponseDto allCategoriesLowestPriceProductsResponseDto
                = allCategoriesLowestPriceProductSearchService.searchAllCategoriesLowestPriceProducts();

        //then
        assertAll(
                () -> assertThat(allCategoriesLowestPriceProductsResponseDto.getProducts()).hasSize(3),
                () -> assertThat(allCategoriesLowestPriceProductsResponseDto.getTotalPrice())
                        .isEqualTo(new BigDecimal(6000))
        );
    }

}