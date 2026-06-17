package com.proj_social.carente.service;

import com.proj_social.carente.exception.ResourceNotFoundException;
import com.proj_social.carente.model.DoacaoApadrinhamento;
import com.proj_social.carente.model.Pet;
import com.proj_social.carente.model.enums.TipoDoacao;
import com.proj_social.carente.repository.DoacaoApadrinhamentoRepository;
import com.proj_social.carente.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoacaoApadrinhamentoService {

    private final DoacaoApadrinhamentoRepository doacaoRepository;
    private final PetRepository petRepository;

    @Transactional
    public DoacaoApadrinhamento registrar(DoacaoApadrinhamento doacao) {
        if (doacao.getPet() != null && doacao.getPet().getId() != null) {
            Pet pet = petRepository.findById(doacao.getPet().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Pet não encontrado"));
            
            // Se for apadrinhamento, podemos marcar que a urgência diminuiu ou o pet está assistido
            if (doacao.getTipo() == TipoDoacao.Apadrinhamento_Mensal) {
                pet.setUrgenciaApadrinhamento(false);
                petRepository.save(pet);
            }
        }
        return doacaoRepository.save(doacao);
    }

    public List<DoacaoApadrinhamento> listarTodas() {
        return doacaoRepository.findAll();
    }

    public List<DoacaoApadrinhamento> listarPorUsuario(Long usuarioId) {
        return doacaoRepository.findByUsuarioId(usuarioId);
    }

    public List<DoacaoApadrinhamento> listarPorPet(Long petId) {
        return doacaoRepository.findByPetId(petId);
    }
}
