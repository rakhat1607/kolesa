package project.kolesa.services;

import project.kolesa.model.Gearbox;

import java.util.List;
import java.util.Optional;

public interface GearboxService {
    Gearbox getGearbox(Long id);
    Optional<Gearbox> findGearbox(Long id);
    List<Gearbox> getAllGearbox();
}
