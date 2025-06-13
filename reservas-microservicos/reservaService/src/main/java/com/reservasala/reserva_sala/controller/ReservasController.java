package com.reservasala.reserva_sala.controller;

import com.reservasala.reserva_sala.dto.Reserva;
import com.reservasala.reserva_sala.dto.SalaDTO;
import com.reservasala.reserva_sala.dto.UsuarioDTO;
import com.reservasala.reserva_sala.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

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
        SalaDTO sala = restTemplate.getForObject(SALA_API_URL + "/" + salaId, SalaDTO.class);
        UsuarioDTO usuario = restTemplate.getForObject(USUARIO_API_URL + "/" + usuarioId, UsuarioDTO.class);

        Reserva reserva = new Reserva(null, LocalDateTime.parse(dataHora), sala, usuario);
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
        SalaDTO sala = restTemplate.getForObject(SALA_API_URL + "/" + salaId, SalaDTO.class);
        UsuarioDTO usuario = restTemplate.getForObject(USUARIO_API_URL + "/" + usuarioId, UsuarioDTO.class);

        Reserva reserva = new Reserva(id, LocalDateTime.parse(dataHora), sala, usuario);
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
