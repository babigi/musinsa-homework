package kr.co.musinsa.cooper.musinsahomework.product.web;

import kr.co.musinsa.cooper.musinsahomework.product.application.AllCategoriesLowestPriceProductSearchService;
import kr.co.musinsa.cooper.musinsahomework.product.dto.AllCategoriesLowestPriceProductsResponseDto;
import kr.co.musinsa.cooper.musinsahomework.product.dto.CategoryLowestPriceProductResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AllCategoriesLowestPriceProductSearchController.class)
@ExtendWith(MockitoExtension.class)
class AllCategoriesLowestPriceProductSearchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AllCategoriesLowestPriceProductSearchService allCategoriesLowestPriceProductSearchService;

    @DisplayName("카테고리별 최저가 상품을 조회한다")
    @Test
    void getCategoryLowestPriceProduct() throws Exception {
        //given
        List<CategoryLowestPriceProductResponseDto> categoryLowestPriceProductResponse = List.of(
                new CategoryLowestPriceProductResponseDto("outer", "brandA", new BigDecimal(1000)),
                new CategoryLowestPriceProductResponseDto("top", "brandB", new BigDecimal(3000)),
                new CategoryLowestPriceProductResponseDto("pants", "brandC", new BigDecimal(5000))
        );

        BigDecimal totalPrice = new BigDecimal(9000);

        AllCategoriesLowestPriceProductsResponseDto allCategoriesLowestPriceProductsResponseDto =
                AllCategoriesLowestPriceProductsResponseDto.create(categoryLowestPriceProductResponse, totalPrice);

        given(allCategoriesLowestPriceProductSearchService.searchAllCategoriesLowestPriceProducts())
                .willReturn(allCategoriesLowestPriceProductsResponseDto);

        //when
        ResultActions result = mockMvc.perform(get("/api/v1/products/search/lowest-price/categories/all")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        //then
        result.andExpectAll(
                status().isOk(),
                jsonPath("$.httpStatus").value("200"),
                jsonPath("$.data.products").isArray(),
                jsonPath("$.data.products").isNotEmpty(),
                jsonPath("$.data.totalPrice").value(9000));
    }

}