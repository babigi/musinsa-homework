package kr.co.musinsa.cooper.musinsahomework.product.application;

import kr.co.musinsa.cooper.musinsahomework.product.domain.AllCategoriesOneBrandLowestPriceRepository;
import kr.co.musinsa.cooper.musinsahomework.product.dto.AllCategoriesOneBrandPriceResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AllCategoriesOneBrandLowestPriceServiceImpl implements AllCategoriesOneBrandLowestPriceService {

    private final AllCategoriesOneBrandLowestPriceRepository allCategoriesOneBrandLowestPriceRepository;

    @Override
    public AllCategoriesOneBrandPriceResponseDto getAllCategoriesOneBrandPrice() {
        return allCategoriesOneBrandLowestPriceRepository.getAllCategoriesOneBrandPrice();
    }

}
