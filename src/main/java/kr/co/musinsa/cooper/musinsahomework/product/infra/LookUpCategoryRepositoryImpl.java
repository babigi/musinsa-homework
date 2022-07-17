package kr.co.musinsa.cooper.musinsahomework.product.infra;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.musinsa.cooper.musinsahomework.product.domain.LookUpCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static kr.co.musinsa.cooper.musinsahomework.product.domain.QCategory.*;

@Repository
@RequiredArgsConstructor
public class LookUpCategoryRepositoryImpl implements LookUpCategoryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public boolean existCategory(Long categoryId) {
        return jpaQueryFactory.selectFrom(category)
                .where(category.id.eq(categoryId))
                .fetchFirst() != null;
    }

}
