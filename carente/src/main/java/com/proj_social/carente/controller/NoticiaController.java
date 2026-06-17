package com.proj_social.carente.controller;

import com.proj_social.carente.model.Noticia;
import com.proj_social.carente.service.NoticiaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/noticias")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class NoticiaController {

    private final NoticiaService noticiaService;

    @PostMapping
    public ResponseEntity<Noticia> salvar(@Valid @RequestBody Noticia noticia) {
        return ResponseEntity.status(HttpStatus.CREATED).body(noticiaService.salvar(noticia));
    }

    @GetMapping
    public ResponseEntity<List<Noticia>> listarTodas() {
        return ResponseEntity.ok(noticiaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Noticia> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(noticiaService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        noticiaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
