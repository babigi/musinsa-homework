package kr.co.musinsa.cooper.musinsahomework.product.domain;

import kr.co.musinsa.cooper.musinsahomework.config.QueryDslConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.*;

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
    @CsvSource(value = {"outer:true", "etc:false"}, delimiter = ':')
    @ParameterizedTest
    void existCategory(String categoryName, boolean existCategory) {
        assertThat(lookUpCategoryRepository.existCategory(categoryName)).isEqualTo(existCategory);
    }

}