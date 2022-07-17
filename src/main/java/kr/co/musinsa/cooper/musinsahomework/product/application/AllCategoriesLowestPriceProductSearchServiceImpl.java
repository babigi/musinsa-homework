package kr.co.musinsa.cooper.musinsahomework.product.application;

import kr.co.musinsa.cooper.musinsahomework.product.domain.AllCategoriesLowestPriceProductSearchRepository;
import kr.co.musinsa.cooper.musinsahomework.product.dto.AllCategoriesLowestPriceProductsResponseDto;
import kr.co.musinsa.cooper.musinsahomework.product.dto.CategoryLowestPriceProductResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AllCategoriesLowestPriceProductSearchServiceImpl implements AllCategoriesLowestPriceProductSearchService {

    private final AllCategoriesLowestPriceProductSearchRepository allCategoriesLowestPriceProductSearchRepository;

    @Override
    public AllCategoriesLowestPriceProductsResponseDto searchAllCategoriesLowestPriceProducts() {
        List<CategoryLowestPriceProductResponseDto> categoryLowestPriceProductsResponseDto
                = allCategoriesLowestPriceProductSearchRepository.searchAllCategoriesLowestPriceProduct();

        return AllCategoriesLowestPriceProductsResponseDto.create(
                filtrateDuplicateCategoryProduct(categoryLowestPriceProductsResponseDto),
                getTotalPrice(categoryLowestPriceProductsResponseDto));
    }

    private List<CategoryLowestPriceProductResponseDto> filtrateDuplicateCategoryProduct(
            List<CategoryLowestPriceProductResponseDto> categoryLowestPriceProducts
    ) {
        Set<String> categoryIds = new HashSet<>();

        List<CategoryLowestPriceProductResponseDto> categoryLowestPriceSingleProducts = new ArrayList<>();

        for (CategoryLowestPriceProductResponseDto categoryLowestPriceProduct : categoryLowestPriceProducts) {
            String categoryName = categoryLowestPriceProduct.getCategory();
            if (!categoryIds.contains(categoryName)) {
                categoryLowestPriceSingleProducts.add(categoryLowestPriceProduct);
                categoryIds.add(categoryName);
            }
        }

        return categoryLowestPriceSingleProducts;
    }

    private BigDecimal getTotalPrice(List<CategoryLowestPriceProductResponseDto> categoryLowestPriceProductsResponseDto) {
        return categoryLowestPriceProductsResponseDto.stream()
                .map(CategoryLowestPriceProductResponseDto::getPrice)
                .reduce(new BigDecimal(0), BigDecimal::add);
    }

}
