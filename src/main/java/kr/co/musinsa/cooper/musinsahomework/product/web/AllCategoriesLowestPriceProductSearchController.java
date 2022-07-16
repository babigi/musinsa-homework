package kr.co.musinsa.cooper.musinsahomework.product.web;

import kr.co.musinsa.cooper.musinsahomework.common.ApiResult;
import kr.co.musinsa.cooper.musinsahomework.product.application.AllCategoriesLowestPriceProductSearchService;
import kr.co.musinsa.cooper.musinsahomework.product.dto.AllCategoriesLowestPriceProductsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class AllCategoriesLowestPriceProductSearchController {

    private final AllCategoriesLowestPriceProductSearchService allCategoriesLowestPriceProductSearchService;

    @GetMapping("/search/lowest-price/categories/all")
    public ResponseEntity<ApiResult<AllCategoriesLowestPriceProductsResponseDto>> getAllCategoriesLowestPrice() {
        AllCategoriesLowestPriceProductsResponseDto allCategoriesLowestPriceProductsResponseDto
                = allCategoriesLowestPriceProductSearchService.searchAllCategoriesLowestPriceProducts();

        ApiResult<AllCategoriesLowestPriceProductsResponseDto> apiResult
                = ApiResult.success(allCategoriesLowestPriceProductsResponseDto, HttpStatus.OK);

        return ResponseEntity.ok(apiResult);
    }

}
