package kr.co.musinsa.cooper.musinsahomework.product.exception.handler;

import kr.co.musinsa.cooper.musinsahomework.product.application.BrandMinAndMaxPriceService;
import kr.co.musinsa.cooper.musinsahomework.product.application.LookupCategoryService;
import kr.co.musinsa.cooper.musinsahomework.product.dto.BrandMinAndMaxPriceResponseDto;
import kr.co.musinsa.cooper.musinsahomework.product.dto.BrandPriceResponseDto;
import kr.co.musinsa.cooper.musinsahomework.product.web.CategoryBrandMinAndMaxPriceController;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryBrandMinAndMaxPriceController.class)
@ExtendWith(MockitoExtension.class)
class NotFoundCategoryExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BrandMinAndMaxPriceService brandMinAndMaxPriceService;

    @MockBean
    private LookupCategoryService lookupCategoryService;

    @DisplayName("잘못된 카테고리 입력값일 경우 예외를 반환한다")
    @Test
    void handleNotFoundCategoryException() throws Exception {
        //given
        BrandPriceResponseDto minBrandPrice
                = new BrandPriceResponseDto("C", new BigDecimal("10000.00"));

        BrandPriceResponseDto maxBrandPrice
                = new BrandPriceResponseDto("I", new BigDecimal("11400.00"));

        BrandMinAndMaxPriceResponseDto brandMinAndMaxPriceResponseDto
                = BrandMinAndMaxPriceResponseDto.create(minBrandPrice, maxBrandPrice);

        given(brandMinAndMaxPriceService.getCategoryMinAndMaxPrice(any()))
                .willReturn(brandMinAndMaxPriceResponseDto);

        given(lookupCategoryService.existCategory(any())).willReturn(false);

        //when
        ResultActions result = mockMvc.perform(
                get("/api/v1/products/categories/{categoryName}/brands/min-max-price", "other-category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));

        //then
        result.andExpectAll(
                status().isBadRequest(),
                content().contentType(MediaType.APPLICATION_JSON),
                jsonPath("$.httpStatus").value("400"),
                jsonPath("$.message").isNotEmpty()
        );

    }
}