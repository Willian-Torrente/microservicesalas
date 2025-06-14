package com.reservasala.reserva_sala.repository;

import com.reservasala.reserva_sala.model.Reserva;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    List<Reserva> findByUsuarioId(Long usuarioId);
    List<Reserva> findBySalaId(Long salaId);
}