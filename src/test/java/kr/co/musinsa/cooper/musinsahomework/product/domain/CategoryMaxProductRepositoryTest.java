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
        classes = CategoryMaxProductRepository.class
))
@Transactional
class CategoryMaxProductRepositoryTest {

    @Autowired
    private CategoryMaxProductRepository categoryMaxProductRepository;

    @Test
    @DisplayName("카테고리의 브랜드 이름과 최고가를 조회한다")
    void getCategoryMaxProduct() {
        BrandPriceResponseDto brandPriceResponseDto
                = categoryMaxProductRepository.getCategoryMaxProduct(1L);

        assertAll(
                () -> assertThat(brandPriceResponseDto.getBrandName()).isEqualTo("I"),
                () -> assertThat(brandPriceResponseDto.getPrice()).isEqualTo(new BigDecimal("11400.00"))
        );

    }

}