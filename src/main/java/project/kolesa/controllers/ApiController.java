package project.kolesa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.kolesa.dto.ModelDto;
import project.kolesa.model.Brand;
import project.kolesa.model.Model;
import project.kolesa.services.BrandService;
import project.kolesa.services.ModelService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private BrandService brandService;
    @Autowired
    private ModelService modelService;

    @GetMapping("/brands")
    public List<Brand> findAll() {
        return brandService.getAllBrand();
    }

    @GetMapping("/brands/{id}/models")
    public List<ModelDto> findByBrand(@PathVariable Long id) {
        return modelService.getAllByBrand(brandService.getBrand(id))
                .stream().map(e -> new ModelDto(e.getModel(), e.getId())).toList();
    }
}