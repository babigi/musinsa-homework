package kr.co.musinsa.cooper.musinsahomework.product.application;

import kr.co.musinsa.cooper.musinsahomework.product.domain.AllCategoriesLowestPriceProductSearchRepository;
import kr.co.musinsa.cooper.musinsahomework.product.dto.AllCategoriesLowestPriceProductsResponseDto;
import kr.co.musinsa.cooper.musinsahomework.product.dto.CategoryLowestPriceProductResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AllCategoriesLowestPriceProductSearchServiceImpl implements AllCategoriesLowestPriceProductSearchService {

    private final AllCategoriesLowestPriceProductSearchRepository allCategoriesLowestPriceProductSearchRepository;

    @Override
    public AllCategoriesLowestPriceProductsResponseDto searchAllCategoriesLowestPriceProducts() {
        List<CategoryLowestPriceProductResponseDto> categoryLowestPriceProductsResponseDto
                = allCategoriesLowestPriceProductSearchRepository.searchAllCategoriesLowestPriceProduct();

        return AllCategoriesLowestPriceProductsResponseDto.create(
                categoryLowestPriceProductsResponseDto,
                getTotalPrice(categoryLowestPriceProductsResponseDto));
    }

    private BigDecimal getTotalPrice(List<CategoryLowestPriceProductResponseDto> categoryLowestPriceProductsResponseDto) {
        return categoryLowestPriceProductsResponseDto.stream()
                .map(CategoryLowestPriceProductResponseDto::getPrice)
                .reduce(new BigDecimal(0), BigDecimal::add);
    }

}
