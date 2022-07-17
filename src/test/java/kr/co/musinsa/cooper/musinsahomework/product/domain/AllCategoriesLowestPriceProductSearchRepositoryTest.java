package kr.co.musinsa.cooper.musinsahomework.product.domain;

import kr.co.musinsa.cooper.musinsahomework.config.QueryDslConfig;
import kr.co.musinsa.cooper.musinsahomework.product.dto.CategoryLowestPriceProductResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@Import(QueryDslConfig.class)
@Sql("classpath:sample-data.sql")
@DataJpaTest(includeFilters = @ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE,
        classes = AllCategoriesLowestPriceProductSearchRepository.class
))
@Transactional
class AllCategoriesLowestPriceProductSearchRepositoryTest {

    @Autowired
    private AllCategoriesLowestPriceProductSearchRepository allCategoriesLowestPriceProductSearchRepository;

    @DisplayName("카테고리별 최저가 상품을 검색한다(중복 허용)")
    @Test
    void searchAllCategoriesLowestPriceProduct() {
        //when
        List<CategoryLowestPriceProductResponseDto> categoryLowestPriceProductsResponseDto
                = allCategoriesLowestPriceProductSearchRepository.searchAllCategoriesLowestPriceProduct();

        Set<String> categoryNames = categoryLowestPriceProductsResponseDto.stream()
                .map(CategoryLowestPriceProductResponseDto::getCategory).collect(Collectors.toSet());

        //then
        assertThat(categoryNames).hasSize(8);
    }

}