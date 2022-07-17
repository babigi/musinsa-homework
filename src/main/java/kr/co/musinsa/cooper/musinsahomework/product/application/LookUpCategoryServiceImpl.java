package kr.co.musinsa.cooper.musinsahomework.product.application;

import kr.co.musinsa.cooper.musinsahomework.product.domain.LookUpCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LookUpCategoryServiceImpl implements LookupCategoryService {

    private final LookUpCategoryRepository lookUpCategoryRepository;

    @Override
    public boolean existCategory(Long categoryId) {
        return lookUpCategoryRepository.existCategory(categoryId);
    }

}
