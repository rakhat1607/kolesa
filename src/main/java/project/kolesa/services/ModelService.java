package project.kolesa.services;

import project.kolesa.model.Brand;
import project.kolesa.model.Model;

import java.util.List;
import java.util.Optional;

public interface ModelService {
    Model getModel(Long id);
    List<Model> getAllModel();
    Optional<Model> findModel(Long id);
    List<Model> getAllByBrand(Brand brand);
}
