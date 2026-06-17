package com.proj_social.carente.service;

import com.proj_social.carente.exception.ResourceNotFoundException;
import com.proj_social.carente.model.Pet;
import com.proj_social.carente.model.enums.Especie;
import com.proj_social.carente.model.enums.StatusAdocao;
import com.proj_social.carente.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;

    public List<Pet> listarTodos() {
        return petRepository.findAll();
    }

    public Pet buscarPorId(Long id) {
        return petRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pet não encontrado com o ID: " + id));
    }

    public List<Pet> filtrarPorEspecie(Especie especie) {
        return petRepository.findByEspecie(especie);
    }

    public List<Pet> listarUrgentes() {
        return petRepository.findByUrgenciaApadrinhamentoTrue();
    }

    public List<Pet> buscarPorNome(String nome) {
        if (nome == null || nome.isBlank()) return listarTodos();
        return petRepository.findByNomeContainingIgnoreCase(nome);
    }

    @Transactional
    public Pet salvar(Pet pet) {
        // Garantir que um novo pet comece como Disponível se não especificado
        if (pet.getStatusAdocao() == null) {
            pet.setStatusAdocao(StatusAdocao.Disponível);
        }
        return petRepository.save(pet);
    }

    @Transactional
    public void deletar(Long id) {
        Pet pet = buscarPorId(id); // Garante que existe antes de deletar
        petRepository.delete(pet);
    }

    @Transactional
    public Pet atualizarStatus(Long id, StatusAdocao novoStatus) {
        Pet pet = buscarPorId(id);
        pet.setStatusAdocao(novoStatus);
        return petRepository.save(pet);
    }
}
