package com.proj_social.carente.service;

import com.proj_social.carente.exception.ResourceNotFoundException;
import com.proj_social.carente.model.FotoPet;
import com.proj_social.carente.model.Pet;
import com.proj_social.carente.repository.FotoPetRepository;
import com.proj_social.carente.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FotoPetService {

    private final FotoPetRepository fotoRepository;
    private final PetRepository petRepository;
    private final FileStorageService fileStorageService;

    @Transactional
    public FotoPet adicionarFoto(Long petId, MultipartFile file, boolean isPrincipal) {
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new ResourceNotFoundException("Pet não encontrado"));

        String fileName = fileStorageService.storeFile(file);
        
        // Se for marcada como principal, desmarcar as outras
        if (isPrincipal) {
            desmarcarFotosPrincipais(petId);
        }

        FotoPet foto = FotoPet.builder()
                .pet(pet)
                .urlImagem(fileName)
                .isPrincipal(isPrincipal)
                .build();

        return fotoRepository.save(foto);
    }

    @Transactional
    public void excluirFoto(Long fotoId) {
        FotoPet foto = fotoRepository.findById(fotoId)
                .orElseThrow(() -> new ResourceNotFoundException("Foto não encontrada"));

        fileStorageService.deleteFile(foto.getUrlImagem());
        fotoRepository.delete(foto);
    }

    @Transactional
    public void definirPrincipal(Long fotoId) {
        FotoPet foto = fotoRepository.findById(fotoId)
                .orElseThrow(() -> new ResourceNotFoundException("Foto não encontrada"));

        desmarcarFotosPrincipais(foto.getPet().getId());
        
        foto.setPrincipal(true);
        fotoRepository.save(foto);
    }

    public List<FotoPet> listarPorPet(Long petId) {
        return fotoRepository.findByPetId(petId);
    }

    private void desmarcarFotosPrincipais(Long petId) {
        List<FotoPet> fotos = fotoRepository.findByPetId(petId);
        fotos.forEach(f -> {
            if (f.isPrincipal()) {
                f.setPrincipal(false);
                fotoRepository.save(f);
            }
        });
    }
}
