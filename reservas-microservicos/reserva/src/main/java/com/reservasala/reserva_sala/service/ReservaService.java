package com.reservasala.reserva_sala.service;

import com.reservasala.reserva_sala.model.Reserva;
import com.reservasala.reserva_sala.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository repository;

    public List<Reserva> listar() {
        return repository.findAll();
    }

    public Reserva salvar(Reserva reserva) {
        return repository.save(reserva);
    }

    public Reserva atualizar(Reserva reserva) {
        return repository.save(reserva);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    public Reserva buscarPorId(Long id) {
        Optional<Reserva> reserva = repository.findById(id);
        return reserva.orElse(null);
    }

    public List<Reserva> listarReservasPorUsuario(Long usuarioId) {
        return repository.findByUsuarioId(usuarioId);
    }

    public List<Reserva> listarReservasPorSala(Long salaId) {
        return repository.findBySalaId(salaId);
    }
}