package kr.co.musinsa.cooper.musinsahomework.product.web;

import kr.co.musinsa.cooper.musinsahomework.common.ApiResult;
import kr.co.musinsa.cooper.musinsahomework.product.application.AllCategoriesOneBrandLowestPriceService;
import kr.co.musinsa.cooper.musinsahomework.product.dto.AllCategoriesOneBrandPriceResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class AllCategoriesOneBrandLowestPriceController {

    private final AllCategoriesOneBrandLowestPriceService allCategoriesLowestPriceProductSearchService;

    @GetMapping("/search/categories/all/brand/lowest-price")
    public ResponseEntity<ApiResult<AllCategoriesOneBrandPriceResponseDto>> getAllCategoriesOneBrandPriceResponseDto() {
        AllCategoriesOneBrandPriceResponseDto allCategoriesOneBrandPriceResponse
                = allCategoriesLowestPriceProductSearchService.getAllCategoriesOneBrandPrice();

        ApiResult<AllCategoriesOneBrandPriceResponseDto> apiResult
                = ApiResult.success(allCategoriesOneBrandPriceResponse, HttpStatus.OK);

        return ResponseEntity.ok(apiResult);
    }

}
