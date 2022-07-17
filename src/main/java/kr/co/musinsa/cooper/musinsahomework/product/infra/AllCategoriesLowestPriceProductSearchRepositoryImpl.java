package kr.co.musinsa.cooper.musinsahomework.product.infra;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.musinsa.cooper.musinsahomework.product.domain.AllCategoriesLowestPriceProductSearchRepository;
import kr.co.musinsa.cooper.musinsahomework.product.dto.CategoryLowestPriceProductResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static kr.co.musinsa.cooper.musinsahomework.product.domain.QBrand.brand;
import static kr.co.musinsa.cooper.musinsahomework.product.domain.QCategory.category;
import static kr.co.musinsa.cooper.musinsahomework.product.domain.QProduct.product;

@Repository
@RequiredArgsConstructor
public class AllCategoriesLowestPriceProductSearchRepositoryImpl
        implements AllCategoriesLowestPriceProductSearchRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<CategoryLowestPriceProductResponseDto> searchAllCategoriesLowestPriceProduct() {
        List<Tuple> categoryLowestPrices = getLowestCategoryPrices();
        return jpaQueryFactory.select(Projections.constructor(CategoryLowestPriceProductResponseDto.class,
                        category.name, product.brand.name, product.price))
                .from(product)
                .where(Expressions.list(product.category.id, product.price).in(categoryPriceIn(categoryLowestPrices)))
                .leftJoin(product.category, category)
                .leftJoin(product.brand, brand)
                .fetch();
    }

    private List<Tuple> getLowestCategoryPrices() {
        return jpaQueryFactory.select(category.id, product.price.min())
                .from(product)
                .groupBy(category.id)
                .fetch();
    }

    private Expression[] categoryPriceIn(List<Tuple> categoryLowestPrices) {
        List<Expression> tuples = new ArrayList<>();

        for (Tuple categoryLowestPrice : categoryLowestPrices) {
            tuples.add(Expressions.template(Object.class, "({0}, {1})",
                    categoryLowestPrice.get(0, Long.class),
                    categoryLowestPrice.get(1, BigDecimal.class)));
        }

        return tuples.toArray(new Expression[0]);
    }

}
