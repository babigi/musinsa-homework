package kr.co.musinsa.cooper.musinsahomework.product.web;

import kr.co.musinsa.cooper.musinsahomework.common.ApiResult;
import kr.co.musinsa.cooper.musinsahomework.product.application.LookupCategoryService;
import kr.co.musinsa.cooper.musinsahomework.product.dto.BrandMinAndMaxPriceResponseDto;
import kr.co.musinsa.cooper.musinsahomework.product.application.BrandMinAndMaxPriceService;
import kr.co.musinsa.cooper.musinsahomework.product.exception.NotFoundCategoryException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class CategoryBrandMinAndMaxPriceController {

    private final BrandMinAndMaxPriceService brandMinAndMaxPriceService;
    private final LookupCategoryService lookupCategoryService;

    @GetMapping("/categories/{categoryName}/brands/min-max-price")
    public ResponseEntity<ApiResult<BrandMinAndMaxPriceResponseDto>> getBrandMinAndMaxPrice(
            @PathVariable String categoryName
    ) {
        if (!lookupCategoryService.existCategory(categoryName)) {
            throw new NotFoundCategoryException(categoryName);
        }

        BrandMinAndMaxPriceResponseDto categoryMinAndMaxPriceResponseDto
                = brandMinAndMaxPriceService.getCategoryMinAndMaxPrice(categoryName);

        ApiResult<BrandMinAndMaxPriceResponseDto> apiResult
                = ApiResult.success(categoryMinAndMaxPriceResponseDto, HttpStatus.OK);

        return ResponseEntity.ok(apiResult);
    }
}

