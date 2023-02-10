package project.kolesa.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.kolesa.model.Gearbox;
import project.kolesa.repository.GearBoxRepository;
import project.kolesa.services.GearboxService;

import java.util.List;
import java.util.Optional;

@Service
public class GearboxServiceImpl implements GearboxService {
    @Autowired
    private GearBoxRepository gearBoxRepository;

    @Override
    public Gearbox getGearbox(Long id) {
        return gearBoxRepository.findById(id).orElseThrow();
    }

    @Override
    public Optional<Gearbox> findGearbox(Long id) {
        return gearBoxRepository.findById(id);
    }

    @Override
    public List<Gearbox> getAllGearbox() {
        return gearBoxRepository.findAll();
    }
}
