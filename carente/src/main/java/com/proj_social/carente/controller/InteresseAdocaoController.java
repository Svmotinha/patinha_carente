package com.proj_social.carente.controller;

import com.proj_social.carente.model.InteresseAdocao;
import com.proj_social.carente.model.enums.StatusSolicitacao;
import com.proj_social.carente.service.InteresseAdocaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/adocoes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class InteresseAdocaoController {

    private final InteresseAdocaoService interesseService;

    @PostMapping("/registrar")
    public ResponseEntity<InteresseAdocao> registrarInteresse(@Valid @RequestBody InteresseAdocao interesse) {
        return ResponseEntity.status(HttpStatus.CREATED).body(interesseService.registrarInteresse(interesse));
    }

    @GetMapping("/pet/{petId}")
    public ResponseEntity<List<InteresseAdocao>> listarPorPet(@PathVariable Long petId) {
        return ResponseEntity.ok(interesseService.listarPorPet(petId));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> atualizarStatus(@PathVariable Long id, @RequestParam StatusSolicitacao status) {
        interesseService.atualizarStatus(id, status);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/confirmar")
    public ResponseEntity<InteresseAdocao> confirmarAgendamento(
            @PathVariable Long id, 
            @RequestParam LocalDateTime dataVisita) {
        return ResponseEntity.ok(interesseService.confirmarAgendamento(id, dataVisita));
    }
}
