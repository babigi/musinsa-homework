package kr.co.musinsa.cooper.musinsahomework.product.domain;

import kr.co.musinsa.cooper.musinsahomework.config.QueryDslConfig;
import kr.co.musinsa.cooper.musinsahomework.product.dto.BrandPriceResponseDto;
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
import static org.junit.jupiter.api.Assertions.*;

@Import(QueryDslConfig.class)
@Sql("classpath:sample-data.sql")
@DataJpaTest(includeFilters = @ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE,
        classes = CategoryMinProductRepository.class
))
@Transactional
class CategoryMinProductRepositoryTest {

    @Autowired
    private CategoryMinProductRepository categoryMinProductRepository;

    @Test
    @DisplayName("카테고리의 브랜드 이름과 최저가를 조회한다")
    void getCategoryMinProduct() {
        BrandPriceResponseDto brandPriceResponseDto
                = categoryMinProductRepository.getCategoryMinProduct(1L);

        assertAll(
                () -> assertThat(brandPriceResponseDto.getBrandName()).isEqualTo("C"),
                () -> assertThat(brandPriceResponseDto.getPrice()).isEqualTo(new BigDecimal("10000.00"))
        );

    }


}