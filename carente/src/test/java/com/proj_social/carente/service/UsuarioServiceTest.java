package com.proj_social.carente.service;

import com.proj_social.carente.model.Usuario;
import com.proj_social.carente.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    void buscarPorEmail_DeveRetornarUsuario() {
        Usuario usuario = Usuario.builder().email("teste@email.com").build();
        when(usuarioRepository.findByEmail("teste@email.com")).thenReturn(Optional.of(usuario));

        Usuario resultado = usuarioService.buscarPorEmail("teste@email.com");

        assertNotNull(resultado);
        assertEquals("teste@email.com", resultado.getEmail());
    }
}
