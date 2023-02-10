package project.kolesa.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.kolesa.model.Brand;
import project.kolesa.model.Model;
import project.kolesa.repository.BrandRepository;
import project.kolesa.services.BrandService;

import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository brandRepository;


    @Override
    public Brand getBrand(Long id) {
        return brandRepository.findById(id).orElseThrow();
    }

    @Override
    public Optional<Brand> findBrand(Long id) {
        return brandRepository.findById(id);
    }


    @Override
    public List<Brand> getAllBrand() {
        return brandRepository.findAll();
    }

}
