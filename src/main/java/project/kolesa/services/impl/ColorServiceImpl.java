package project.kolesa.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.kolesa.model.Color;
import project.kolesa.repository.ColorRepository;
import project.kolesa.services.ColorService;

import java.util.List;
import java.util.Optional;

@Service
public class ColorServiceImpl implements ColorService {
    @Autowired
    private ColorRepository colorRepository;

    @Override
    public Optional<Color> findColor(Long id) {
        return colorRepository.findById(id);
    }

    @Override
    public Color getColor(Long id) {
        return colorRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Color> getAllColor() {
        return colorRepository.findAll();
    }
}
