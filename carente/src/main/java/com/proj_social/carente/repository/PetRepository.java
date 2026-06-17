package com.proj_social.carente.repository;

import com.proj_social.carente.model.Pet;
import com.proj_social.carente.model.enums.Especie;
import com.proj_social.carente.model.enums.StatusAdocao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findByEspecie(Especie especie);
    List<Pet> findByStatusAdocao(StatusAdocao status);
    List<Pet> findByUrgenciaApadrinhamentoTrue();
    List<Pet> findByNomeContainingIgnoreCase(String nome);
}
