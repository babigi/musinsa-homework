package kr.co.musinsa.cooper.musinsahomework.product.domain;

import kr.co.musinsa.cooper.musinsahomework.config.QueryDslConfig;
import kr.co.musinsa.cooper.musinsahomework.product.dto.AllCategoriesOneBrandPriceResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@Import(QueryDslConfig.class)
@Sql("classpath:sample-data.sql")
@DataJpaTest(includeFilters = @ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE,
        classes = AllCategoriesOneBrandLowestPriceRepository.class
))
@Transactional
class AllCategoriesOneBrandLowestPriceRepositoryTest {

    @Autowired
    private AllCategoriesOneBrandLowestPriceRepository allCategoriesOneBrandLowestPriceRepository;

    @DisplayName("한 브랜드에서 모든 카테고리의 상품을 한꺼번에 구매할 경우 최저가 및 브랜드를 조회한다")
    @Test
    void getAllCategoriesOneBrandLowestPrice() {
        AllCategoriesOneBrandPriceResponseDto allCategoriesOneBrandPriceResponseDto
                = allCategoriesOneBrandLowestPriceRepository.getAllCategoriesOneBrandPrice();

        assertAll(
                () -> assertThat(allCategoriesOneBrandPriceResponseDto.getBrandName()).isEqualTo("D"),
                () -> assertThat(allCategoriesOneBrandPriceResponseDto.getPrice()).isEqualTo(new BigDecimal("36100.00"))
        );
    }

}