package kr.co.musinsa.cooper.musinsahomework.product.application;

import kr.co.musinsa.cooper.musinsahomework.product.domain.CategoryMaxProductRepository;
import kr.co.musinsa.cooper.musinsahomework.product.domain.CategoryMinProductRepository;
import kr.co.musinsa.cooper.musinsahomework.product.dto.BrandMinAndMaxPriceResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrandMinAndMaxPriceServiceImpl implements BrandMinAndMaxPriceService {

    private final CategoryMinProductRepository categoryMinProductRepository;
    private final CategoryMaxProductRepository categoryMaxProductRepository;

    public BrandMinAndMaxPriceResponseDto getCategoryMinAndMaxPrice(Long categoryId) {
        return BrandMinAndMaxPriceResponseDto.create(
                categoryMinProductRepository.getCategoryMinProduct(categoryId),
                categoryMaxProductRepository.getCategoryMaxProduct(categoryId)
        );
    }

}
