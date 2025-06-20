package com.reservasala.reserva_sala.controller;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.reservasala.reserva_sala.dto.SalaDTO;
import com.reservasala.reserva_sala.dto.UsuarioDTO;
import com.reservasala.reserva_sala.model.Reserva;
import com.reservasala.reserva_sala.service.ReservaService;

@Controller
@RequestMapping("/reservas")
public class ReservasController {

    @Autowired
    private ReservaService reservaService;

    @Autowired
    private RestTemplate restTemplate;

    private final String SALA_API_URL = "http://app-sala:8083/salas";
    private final String USUARIO_API_URL = "http://app-usuario:8084/usuarios";

    @GetMapping
    public String listar(Model model) {
        List<Reserva> reservas = reservaService.listar();
        List<SalaDTO> salas = Arrays.asList(
                restTemplate.getForObject(SALA_API_URL, SalaDTO[].class)
        );
        List<UsuarioDTO> usuarios = Arrays.asList(
                restTemplate.getForObject(USUARIO_API_URL, UsuarioDTO[].class)
        );

        model.addAttribute("reservas", reservas);
        model.addAttribute("salas", salas);
        model.addAttribute("usuarios", usuarios);
        return "reservas";
    }

    @PostMapping("/salvar")
    public String salvar(@RequestParam Long salaId, @RequestParam Long usuarioId, @RequestParam String dataHora) {
        Reserva reserva = new Reserva();
        reserva.setDataHora(LocalDateTime.parse(dataHora));
        reserva.setSalaId(salaId);
        reserva.setUsuarioId(usuarioId);

        reservaService.salvar(reserva);
        return "redirect:/reservas";
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        reservaService.deletar(id);
        return "redirect:/reservas";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Reserva reserva = reservaService.buscarPorId(id);
        List<SalaDTO> salas = Arrays.asList(
                restTemplate.getForObject(SALA_API_URL, SalaDTO[].class)
        );
        List<UsuarioDTO> usuarios = Arrays.asList(
                restTemplate.getForObject(USUARIO_API_URL, UsuarioDTO[].class)
        );

        model.addAttribute("reserva", reserva);
        model.addAttribute("salas", salas);
        model.addAttribute("usuarios", usuarios);
        return "editar_reserva";
    }

    @PostMapping("/atualizar")
    public String atualizar(@RequestParam Long id, @RequestParam Long salaId, @RequestParam Long usuarioId, @RequestParam String dataHora) {
        Reserva reserva = new Reserva();
        reserva.setId(id);
        reserva.setDataHora(LocalDateTime.parse(dataHora));
        reserva.setSalaId(salaId);
        reserva.setUsuarioId(usuarioId);

        reservaService.atualizar(reserva);
        return "redirect:/reservas";
    }

    @GetMapping("/reservas-usuario/{usuarioId}")
    public ResponseEntity<List<Reserva>> listarReservasPorUsuario(@PathVariable Long usuarioId) {
        List<Reserva> reservas = reservaService.listarReservasPorUsuario(usuarioId);
        return ResponseEntity.ok(reservas);
    }

    @GetMapping("/reservas-sala/{salaId}")
    public ResponseEntity<List<Reserva>> listarReservasPorSala(@PathVariable Long salaId) {
        List<Reserva> reservas = reservaService.listarReservasPorSala(salaId);
        return ResponseEntity.ok(reservas);
    }
}
