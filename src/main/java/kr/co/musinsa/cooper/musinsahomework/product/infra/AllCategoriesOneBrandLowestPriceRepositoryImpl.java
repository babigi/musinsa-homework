package kr.co.musinsa.cooper.musinsahomework.product.infra;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.musinsa.cooper.musinsahomework.product.domain.AllCategoriesOneBrandLowestPriceRepository;
import kr.co.musinsa.cooper.musinsahomework.product.dto.AllCategoriesOneBrandPriceResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static kr.co.musinsa.cooper.musinsahomework.product.domain.QBrand.brand;
import static kr.co.musinsa.cooper.musinsahomework.product.domain.QCategory.category;
import static kr.co.musinsa.cooper.musinsahomework.product.domain.QProduct.product;

@Repository
@RequiredArgsConstructor
public class AllCategoriesOneBrandLowestPriceRepositoryImpl implements AllCategoriesOneBrandLowestPriceRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public AllCategoriesOneBrandPriceResponseDto getAllCategoriesOneBrandPrice() {
        return jpaQueryFactory.select(Projections.constructor(AllCategoriesOneBrandPriceResponseDto.class,
                brand.name, product.price.sum()))
                .from(product)
                .leftJoin(product.brand, brand)
                .leftJoin(product.category, category)
                .groupBy(product.brand.id)
                .orderBy(product.price.sum().asc())
                .fetchFirst();
    }

}
