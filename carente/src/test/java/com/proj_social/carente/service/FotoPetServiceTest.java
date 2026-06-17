package com.proj_social.carente.service;

import com.proj_social.carente.model.FotoPet;
import com.proj_social.carente.model.Pet;
import com.proj_social.carente.repository.FotoPetRepository;
import com.proj_social.carente.repository.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FotoPetServiceTest {

    @Mock
    private FotoPetRepository fotoRepository;

    @Mock
    private PetRepository petRepository;

    @Mock
    private FileStorageService fileStorageService;

    @InjectMocks
    private FotoPetService fotoPetService;

    private Pet pet;
    private FotoPet fotoExistente;

    @BeforeEach
    void setUp() {
        pet = Pet.builder().id(1L).nome("Bidu").build();
        fotoExistente = FotoPet.builder().id(1L).pet(pet).urlImagem("antiga.jpg").isPrincipal(true).build();
    }

    @Test
    void adicionarFoto_DeveDesmarcarAntigaPrincipal_QuandoNovaForPrincipal() {
        MockMultipartFile file = new MockMultipartFile("file", "test.jpg", "image/jpeg", "content".getBytes());
        List<FotoPet> fotosDoPet = new ArrayList<>();
        fotosDoPet.add(fotoExistente);

        when(petRepository.findById(1L)).thenReturn(Optional.of(pet));
        when(fileStorageService.storeFile(file)).thenReturn("nova.jpg");
        when(fotoRepository.findByPetId(1L)).thenReturn(fotosDoPet);
        when(fotoRepository.save(any(FotoPet.class))).thenAnswer(i -> i.getArguments()[0]);

        FotoPet resultado = fotoPetService.adicionarFoto(1L, file, true);

        assertNotNull(resultado);
        assertTrue(resultado.isPrincipal());
        assertFalse(fotoExistente.isPrincipal()); // A antiga deve ter sido desmarcada
        verify(fotoRepository, times(2)).save(any(FotoPet.class)); // Uma para desmarcar a antiga, outra para salvar a nova
    }

    @Test
    void excluirFoto_DeveRemoverArquivoEFisico() {
        when(fotoRepository.findById(1L)).thenReturn(Optional.of(fotoExistente));

        fotoPetService.excluirFoto(1L);

        verify(fileStorageService).deleteFile("antiga.jpg");
        verify(fotoRepository).delete(fotoExistente);
    }
}
