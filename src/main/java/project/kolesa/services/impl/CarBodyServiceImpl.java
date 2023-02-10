package project.kolesa.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.kolesa.model.CarBody;
import project.kolesa.repository.CarBodyRepository;
import project.kolesa.services.CarBodyService;

import java.util.List;
import java.util.Optional;

@Service
public class CarBodyServiceImpl implements CarBodyService {
    @Autowired
    private CarBodyRepository carBodyRepository;

    @Override
    public CarBody getCarBody(Long id) {
        return carBodyRepository.findById(id).orElseThrow();
    }

    @Override
    public List<CarBody> getAllCarBody() {
        return carBodyRepository.findAll();
    }

    @Override
    public Optional<CarBody> findCarBody(Long id) {
        return carBodyRepository.findById(id);
    }
}
