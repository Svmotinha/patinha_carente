package com.proj_social.carente.controller;

import com.proj_social.carente.model.FotoPet;
import com.proj_social.carente.service.FotoPetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/fotos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class FotoPetController {

    private final FotoPetService fotoPetService;

    @PostMapping("/pet/{petId}")
    public ResponseEntity<FotoPet> uploadFoto(
            @PathVariable Long petId,
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "isPrincipal", defaultValue = "false") boolean isPrincipal) {
        
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(fotoPetService.adicionarFoto(petId, file, isPrincipal));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirFoto(@PathVariable Long id) {
        fotoPetService.excluirFoto(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/principal")
    public ResponseEntity<Void> definirPrincipal(@PathVariable Long id) {
        fotoPetService.definirPrincipal(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/pet/{petId}")
    public ResponseEntity<List<FotoPet>> listarPorPet(@PathVariable Long petId) {
        return ResponseEntity.ok(fotoPetService.listarPorPet(petId));
    }
}
