package com.proj_social.carente.service;

import com.proj_social.carente.model.Pet;
import com.proj_social.carente.model.enums.StatusAdocao;
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
class PetServiceTest {

    @Mock
    private PetRepository petRepository;

    @InjectMocks
    private PetService petService;

    @Test
    void atualizarStatus_DeveAlterarStatusDoPet() {
        Pet pet = Pet.builder().id(1L).statusAdocao(StatusAdocao.Disponível).build();
        when(petRepository.findById(1L)).thenReturn(Optional.of(pet));
        when(petRepository.save(any(Pet.class))).thenReturn(pet);

        Pet resultado = petService.atualizarStatus(1L, StatusAdocao.Adotado);

        assertEquals(StatusAdocao.Adotado, resultado.getStatusAdocao());
        verify(petRepository).save(pet);
    }
}
