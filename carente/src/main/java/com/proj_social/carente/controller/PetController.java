package com.proj_social.carente.controller;

import com.proj_social.carente.model.Pet;
import com.proj_social.carente.model.enums.Especie;
import com.proj_social.carente.model.enums.StatusAdocao;
import com.proj_social.carente.service.PetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pets")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // Permitir acesso do Angular durante desenvolvimento
public class PetController {

    private final PetService petService;

    @GetMapping
    public ResponseEntity<List<Pet>> listarTodos() {
        return ResponseEntity.ok(petService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pet> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(petService.buscarPorId(id));
    }

    @GetMapping("/especie/{especie}")
    public ResponseEntity<List<Pet>> filtrarPorEspecie(@PathVariable Especie especie) {
        return ResponseEntity.ok(petService.filtrarPorEspecie(especie));
    }

    @GetMapping("/urgentes")
    public ResponseEntity<List<Pet>> listarUrgentes() {
        return ResponseEntity.ok(petService.listarUrgentes());
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Pet>> buscarPorNome(@RequestParam(required = false) String nome) {
        return ResponseEntity.ok(petService.buscarPorNome(nome));
    }

    @PostMapping
    public ResponseEntity<Pet> salvar(@Valid @RequestBody Pet pet) {
        return ResponseEntity.status(HttpStatus.CREATED).body(petService.salvar(pet));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Pet> atualizarStatus(@PathVariable Long id, @RequestParam StatusAdocao status) {
        return ResponseEntity.ok(petService.atualizarStatus(id, status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        petService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
