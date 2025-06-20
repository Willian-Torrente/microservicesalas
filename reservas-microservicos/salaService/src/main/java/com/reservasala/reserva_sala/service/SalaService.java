package com.reservasala.reserva_sala.service;
import com.reservasala.reserva_sala.model.Sala;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reservasala.reserva_sala.repository.SalaRepository;

@Service
public class SalaService {
    @Autowired
    private SalaRepository repository;

    public List<Sala> listar() {
        return repository.findAll();
    }

    public Sala salvar(Sala sala) {
        return repository.save(sala);
    }
}
