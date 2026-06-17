package com.proj_social.carente.repository;

import com.proj_social.carente.model.FotoPet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FotoPetRepository extends JpaRepository<FotoPet, Long> {
    List<FotoPet> findByPetId(Long petId);
}
