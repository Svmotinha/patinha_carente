package com.proj_social.carente.controller;

import com.proj_social.carente.model.DoacaoApadrinhamento;
import com.proj_social.carente.service.DoacaoApadrinhamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doacoes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class DoacaoApadrinhamentoController {

    private final DoacaoApadrinhamentoService doacaoService;

    @PostMapping
    public ResponseEntity<DoacaoApadrinhamento> registrar(@Valid @RequestBody DoacaoApadrinhamento doacao) {
        return ResponseEntity.status(HttpStatus.CREATED).body(doacaoService.registrar(doacao));
    }

    @GetMapping
    public ResponseEntity<List<DoacaoApadrinhamento>> listarTodas() {
        return ResponseEntity.ok(doacaoService.listarTodas());
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<DoacaoApadrinhamento>> listarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(doacaoService.listarPorUsuario(usuarioId));
    }

    @GetMapping("/pet/{petId}")
    public ResponseEntity<List<DoacaoApadrinhamento>> listarPorPet(@PathVariable Long petId) {
        return ResponseEntity.ok(doacaoService.listarPorPet(petId));
    }
}
