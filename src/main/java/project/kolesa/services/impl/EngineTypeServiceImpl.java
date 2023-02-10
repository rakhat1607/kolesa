package project.kolesa.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.kolesa.model.EngineType;
import project.kolesa.repository.EngineTypeRepository;
import project.kolesa.services.EngineTypeService;

import java.util.List;
import java.util.Optional;

@Service
public class EngineTypeServiceImpl implements EngineTypeService {
    @Autowired
    private EngineTypeRepository engineTypeRepository;

    @Override
    public Optional<EngineType> findEngineType(Long id) {
        return engineTypeRepository.findById(id);
    }

    @Override
    public EngineType getEngineType(Long id) {
        return engineTypeRepository.findById(id).orElseThrow();
    }

    @Override
    public List<EngineType> getAllEngineType() {
        return engineTypeRepository.findAll();
    }
}
