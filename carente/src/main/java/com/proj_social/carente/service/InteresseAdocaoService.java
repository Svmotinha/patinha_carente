package com.proj_social.carente.service;

import com.proj_social.carente.exception.BusinessException;
import com.proj_social.carente.exception.ResourceNotFoundException;
import com.proj_social.carente.model.InteresseAdocao;
import com.proj_social.carente.model.Pet;
import com.proj_social.carente.model.enums.StatusAdocao;
import com.proj_social.carente.model.enums.StatusSolicitacao;
import com.proj_social.carente.repository.InteresseAdocaoRepository;
import com.proj_social.carente.repository.PetRepository;
import com.proj_social.carente.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InteresseAdocaoService {

    private final InteresseAdocaoRepository interesseRepository;
    private final PetRepository petRepository;
    private final UsuarioRepository usuarioRepository;

    @Transactional
    public InteresseAdocao registrarInteresse(InteresseAdocao interesse) {
        // Validar Pet
        Pet pet = petRepository.findById(interesse.getPet().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Pet não encontrado"));

        if (pet.getStatusAdocao() == StatusAdocao.Adotado) {
            throw new BusinessException("Este pet já foi adotado e não está mais disponível.");
        }

        // Validar Usuário
        if (!usuarioRepository.existsById(interesse.getUsuario().getId())) {
            throw new ResourceNotFoundException("Usuário solicitante não encontrado");
        }

        // Evitar duplicidade (Mesmo usuário interessado no mesmo pet)
        boolean jaExiste = interesseRepository.findByUsuarioId(interesse.getUsuario().getId())
                .stream()
                .anyMatch(i -> i.getPet().getId().equals(pet.getId()) && 
                          i.getStatusSolicitacao() != StatusSolicitacao.Cancelado);
        
        if (jaExiste) {
            throw new BusinessException("Você já possui uma solicitação ativa para este pet.");
        }

        interesse.setStatusSolicitacao(StatusSolicitacao.Pendente);
        return interesseRepository.save(interesse);
    }

    public List<InteresseAdocao> listarPorPet(Long petId) {
        return interesseRepository.findByPetId(petId);
    }

    @Transactional
    public void atualizarStatus(Long id, StatusSolicitacao status) {
        InteresseAdocao interesse = interesseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Registro de interesse não encontrado"));
        
        interesse.setStatusSolicitacao(status);
        interesseRepository.save(interesse);
    }

    @Transactional
    public InteresseAdocao confirmarAgendamento(Long id, LocalDateTime dataVisita) {
        InteresseAdocao interesse = interesseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Registro de interesse não encontrado"));

        if (dataVisita.isBefore(LocalDateTime.now())) {
            throw new BusinessException("A data da visita não pode ser no passado.");
        }

        interesse.setDataVisita(dataVisita);
        interesse.setStatusSolicitacao(StatusSolicitacao.Confirmado);

        // Ao confirmar agendamento, o pet passa para "Em Processo"
        Pet pet = interesse.getPet();
        pet.setStatusAdocao(StatusAdocao.Em_Processo);
        petRepository.save(pet);

        return interesseRepository.save(interesse);
    }
}
