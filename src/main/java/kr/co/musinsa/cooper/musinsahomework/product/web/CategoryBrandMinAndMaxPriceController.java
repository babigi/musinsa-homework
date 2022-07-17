package kr.co.musinsa.cooper.musinsahomework.product.web;

import kr.co.musinsa.cooper.musinsahomework.common.ApiResult;
import kr.co.musinsa.cooper.musinsahomework.product.dto.BrandMinAndMaxPriceResponseDto;
import kr.co.musinsa.cooper.musinsahomework.product.application.BrandMinAndMaxPriceService;
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

    @GetMapping("/categories/{categoryName}/brands/min-max-price")
    public ResponseEntity<ApiResult<BrandMinAndMaxPriceResponseDto>> getBrandMinAndMaxPrice(
            @PathVariable String categoryName
    ) {
        BrandMinAndMaxPriceResponseDto categoryMinAndMaxPriceResponseDto
                = brandMinAndMaxPriceService.getCategoryMinAndMaxPrice(categoryName);

        ApiResult<BrandMinAndMaxPriceResponseDto> apiResult
                = ApiResult.success(categoryMinAndMaxPriceResponseDto, HttpStatus.OK);

        return ResponseEntity.ok(apiResult);
    }
}

