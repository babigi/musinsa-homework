package kr.co.musinsa.cooper.musinsahomework.product.infra;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.musinsa.cooper.musinsahomework.product.domain.CategoryMaxProductRepository;
import kr.co.musinsa.cooper.musinsahomework.product.dto.BrandPriceResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static kr.co.musinsa.cooper.musinsahomework.product.domain.QBrand.brand;
import static kr.co.musinsa.cooper.musinsahomework.product.domain.QCategory.category;
import static kr.co.musinsa.cooper.musinsahomework.product.domain.QProduct.product;

@Repository
@RequiredArgsConstructor
public class CategoryMaxProductRepositoryImpl implements CategoryMaxProductRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public BrandPriceResponseDto getCategoryMaxProduct(Long categoryId) {
        return jpaQueryFactory.select(Projections.constructor(BrandPriceResponseDto.class,
                        product.brand.name, product.price))
                .from(product)
                .where(product.category.id.eq(categoryId))
                .innerJoin(product.category, category)
                .innerJoin(product.brand, brand)
                .groupBy(category, brand)
                .orderBy(product.price.desc())
                .fetchFirst();
    }
}
