package com.reservasala.reserva.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
