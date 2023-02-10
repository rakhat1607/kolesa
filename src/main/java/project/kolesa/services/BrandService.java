package project.kolesa.services;

import project.kolesa.model.Brand;
import project.kolesa.model.Model;

import java.util.List;
import java.util.Optional;

public interface BrandService {
    Brand getBrand(Long id);
    Optional<Brand> findBrand(Long id);
    List<Brand> getAllBrand();

}
