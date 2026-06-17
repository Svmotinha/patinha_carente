package com.proj_social.carente.service;

import com.proj_social.carente.model.DoacaoApadrinhamento;
import com.proj_social.carente.model.Pet;
import com.proj_social.carente.model.enums.TipoDoacao;
import com.proj_social.carente.repository.DoacaoApadrinhamentoRepository;
import com.proj_social.carente.repository.PetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DoacaoApadrinhamentoServiceTest {

    @Mock
    private DoacaoApadrinhamentoRepository doacaoRepository;

    @Mock
    private PetRepository petRepository;

    @InjectMocks
    private DoacaoApadrinhamentoService doacaoService;

    @Test
    void registrar_DeveDesativarUrgencia_QuandoApadrinhamento() {
        Pet pet = Pet.builder().id(1L).urgenciaApadrinhamento(true).build();
        DoacaoApadrinhamento doacao = DoacaoApadrinhamento.builder()
                .pet(pet)
                .tipo(TipoDoacao.Apadrinhamento_Mensal)
                .build();

        when(petRepository.findById(1L)).thenReturn(Optional.of(pet));
        when(doacaoRepository.save(any())).thenReturn(doacao);

        doacaoService.registrar(doacao);

        assertFalse(pet.isUrgenciaApadrinhamento());
        verify(petRepository).save(pet);
    }
}
