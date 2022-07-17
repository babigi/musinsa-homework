package kr.co.musinsa.cooper.musinsahomework.product.web;

import kr.co.musinsa.cooper.musinsahomework.product.application.BrandMinAndMaxPriceService;
import kr.co.musinsa.cooper.musinsahomework.product.dto.BrandMinAndMaxPriceResponseDto;
import kr.co.musinsa.cooper.musinsahomework.product.dto.BrandPriceResponseDto;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryBrandMinAndMaxPriceController.class)
@ExtendWith(MockitoExtension.class)
class CategoryBrandMinAndMaxPriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BrandMinAndMaxPriceService brandMinAndMaxPriceService;

    @DisplayName("모든 카테고리의 상품을 한꺼번에 구매할 경우 최저가 및 브랜드 조회")
    @Test
    void getBrandMinAndMaxPrice() throws Exception {
        //given
        BrandPriceResponseDto minBrandPrice
                = new BrandPriceResponseDto("C", new BigDecimal("10000.00"));

        BrandPriceResponseDto maxBrandPrice
                = new BrandPriceResponseDto("I", new BigDecimal("11400.00"));

        BrandMinAndMaxPriceResponseDto brandMinAndMaxPriceResponseDto
                = BrandMinAndMaxPriceResponseDto.create(minBrandPrice, maxBrandPrice);

        given(brandMinAndMaxPriceService.getCategoryMinAndMaxPrice(any()))
                .willReturn(brandMinAndMaxPriceResponseDto);

        //when
        ResultActions result = mockMvc.perform(
                get("/api/v1/products/categories/{categoryName}/brands/min-max-price", "top")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpectAll(
                status().isOk(),
                content().contentType(MediaType.APPLICATION_JSON),
                jsonPath("$.data.minBrandPrice.brandName").value("C"),
                jsonPath("$.data.maxBrandPrice.brandName").value("I")
        );

    }

}