package com.proj_social.carente.service;

import com.proj_social.carente.exception.BusinessException;
import com.proj_social.carente.exception.ResourceNotFoundException;
import com.proj_social.carente.model.InteresseAdocao;
import com.proj_social.carente.model.Pet;
import com.proj_social.carente.model.enums.StatusAdocao;
import com.proj_social.carente.model.enums.StatusSolicitacao;
import com.proj_social.carente.repository.InteresseAdocaoRepository;
import com.proj_social.carente.repository.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InteresseAdocaoServiceTest {

    @Mock
    private InteresseAdocaoRepository interesseRepository;

    @Mock
    private PetRepository petRepository;

    @InjectMocks
    private InteresseAdocaoService interesseService;

    private Pet pet;
    private InteresseAdocao interesse;

    @BeforeEach
    void setUp() {
        pet = Pet.builder()
                .id(1L)
                .nome("Fubá")
                .statusAdocao(StatusAdocao.Disponível)
                .build();

        interesse = InteresseAdocao.builder()
                .id(1L)
                .pet(pet)
                .statusSolicitacao(StatusSolicitacao.Pendente)
                .build();
    }

    @Test
    void confirmarAgendamento_DeveSucesso_QuandoDataFutura() {
        LocalDateTime dataFutura = LocalDateTime.now().plusDays(2);
        when(interesseRepository.findById(1L)).thenReturn(Optional.of(interesse));
        when(interesseRepository.save(any(InteresseAdocao.class))).thenReturn(interesse);
        when(petRepository.save(any(Pet.class))).thenReturn(pet);

        InteresseAdocao resultado = interesseService.confirmarAgendamento(1L, dataFutura);

        assertNotNull(resultado);
        assertEquals(StatusSolicitacao.Confirmado, resultado.getStatusSolicitacao());
        assertEquals(dataFutura, resultado.getDataVisita());
        assertEquals(StatusAdocao.Em_Processo, pet.getStatusAdocao());
        
        verify(interesseRepository).save(interesse);
        verify(petRepository).save(pet);
    }

    @Test
    void confirmarAgendamento_DeveLancarExcecao_QuandoDataPassada() {
        LocalDateTime dataPassada = LocalDateTime.now().minusDays(1);
        when(interesseRepository.findById(1L)).thenReturn(Optional.of(interesse));

        assertThrows(BusinessException.class, () -> 
            interesseService.confirmarAgendamento(1L, dataPassada)
        );

        verify(interesseRepository, never()).save(any());
        verify(petRepository, never()).save(any());
    }

    @Test
    void confirmarAgendamento_DeveLancarExcecao_QuandoInteresseNaoEncontrado() {
        when(interesseRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> 
            interesseService.confirmarAgendamento(1L, LocalDateTime.now().plusDays(1))
        );
    }
}
