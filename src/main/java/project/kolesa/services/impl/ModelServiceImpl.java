package project.kolesa.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.kolesa.model.Brand;
import project.kolesa.model.Model;
import project.kolesa.repository.ModelRepository;
import project.kolesa.services.ModelService;

import java.util.List;
import java.util.Optional;

@Service
public class ModelServiceImpl implements ModelService {
    @Autowired
    private ModelRepository modelRepository;

    @Override
    public Optional<Model> findModel(Long id) {
        return modelRepository.findById(id);
    }

    @Override
    public Model getModel(Long id) {
        return modelRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Model> getAllModel() {
        return modelRepository.findAll();
    }

    @Override
    public List<Model> getAllByBrand(Brand brand) {
        return modelRepository.findAllByBrand(brand);
    }
}
