package kr.co.musinsa.cooper.musinsahomework.product.domain;

import kr.co.musinsa.cooper.musinsahomework.config.QueryDslConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

@Import(QueryDslConfig.class)
@Sql("classpath:sample-data.sql")
@DataJpaTest(includeFilters = @ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE,
        classes = LookUpCategoryRepository.class
))
@Transactional
class LookUpCategoryRepositoryTest {

    @Autowired
    private LookUpCategoryRepository lookUpCategoryRepository;

    @DisplayName("카테고리 존재 유무를 확인한다")
    @Test
    void existCategory() {

        assertAll(
                () -> assertThat(lookUpCategoryRepository.existCategory(2L)).isTrue(),
                () -> assertThat(lookUpCategoryRepository.existCategory(1000L)).isFalse()
        );
    }

}