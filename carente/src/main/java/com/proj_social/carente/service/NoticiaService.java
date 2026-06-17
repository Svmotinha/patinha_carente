package com.proj_social.carente.service;

import com.proj_social.carente.exception.ResourceNotFoundException;
import com.proj_social.carente.model.Noticia;
import com.proj_social.carente.model.enums.CategoriaNoticia;
import com.proj_social.carente.repository.NoticiaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticiaService {

    private final NoticiaRepository noticiaRepository;

    @Transactional
    public Noticia salvar(Noticia noticia) {
        return noticiaRepository.save(noticia);
    }

    public List<Noticia> listarTodas() {
        return noticiaRepository.findAllByOrderByDataPublicacaoDesc();
    }

    public Noticia buscarPorId(Long id) {
        return noticiaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notícia não encontrada"));
    }

    @Transactional
    public void deletar(Long id) {
        if (!noticiaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Notícia não encontrada");
        }
        noticiaRepository.deleteById(id);
    }
}
