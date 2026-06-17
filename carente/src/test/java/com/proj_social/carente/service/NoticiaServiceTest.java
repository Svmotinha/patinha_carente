package com.proj_social.carente.service;

import com.proj_social.carente.model.Noticia;
import com.proj_social.carente.model.enums.CategoriaNoticia;
import com.proj_social.carente.repository.NoticiaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NoticiaServiceTest {

    @Mock
    private NoticiaRepository noticiaRepository;

    @InjectMocks
    private NoticiaService noticiaService;

    @Test
    void salvar_DevePersistirNoticia() {
        Noticia noticia = Noticia.builder().titulo("Novo Resgate").categoria(CategoriaNoticia.Resgate).build();
        when(noticiaRepository.save(any())).thenReturn(noticia);

        Noticia resultado = noticiaService.salvar(noticia);

        assertNotNull(resultado);
        assertEquals("Novo Resgate", resultado.getTitulo());
        verify(noticiaRepository).save(noticia);
    }
}
