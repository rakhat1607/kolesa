package project.kolesa.services;

import project.kolesa.model.EngineType;

import java.util.List;
import java.util.Optional;

public interface EngineTypeService {
    EngineType getEngineType(Long id);
    Optional<EngineType> findEngineType(Long id);
    List<EngineType> getAllEngineType();
}
