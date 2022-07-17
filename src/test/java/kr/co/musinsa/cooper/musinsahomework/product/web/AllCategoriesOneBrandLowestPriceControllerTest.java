package kr.co.musinsa.cooper.musinsahomework.product.web;

import kr.co.musinsa.cooper.musinsahomework.product.application.AllCategoriesOneBrandLowestPriceService;
import kr.co.musinsa.cooper.musinsahomework.product.dto.AllCategoriesOneBrandPriceResponseDto;
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

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(AllCategoriesOneBrandLowestPriceController.class)
@ExtendWith(MockitoExtension.class)
class AllCategoriesOneBrandLowestPriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AllCategoriesOneBrandLowestPriceService allCategoriesOneBrandLowestPriceService;

    @DisplayName("모든 카테고리의 상품을 한꺼번에 구매할 경우 최저가 및 브랜드 조회")
    @Test
    void getAllCategoriesOneBrandPriceResponseDto() throws Exception {
        //given
        AllCategoriesOneBrandPriceResponseDto allCategoriesOneBrandPriceResponseDto
                = new AllCategoriesOneBrandPriceResponseDto("D", new BigDecimal(36100));

        given(allCategoriesOneBrandLowestPriceService.getAllCategoriesOneBrandPrice())
                .willReturn(allCategoriesOneBrandPriceResponseDto);

        //when
        ResultActions result = mockMvc.perform(get("/api/v1/products/search/categories/all/brand/lowest-price")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        //then
        result.andExpectAll(
                status().isOk(),
                jsonPath("$.httpStatus").value("200"),
                jsonPath("$.data.brandName").value("D"),
                jsonPath("$.data.price").value("36100")
        );
    }

}