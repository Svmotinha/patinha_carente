package com.proj_social.carente.repository;

import com.proj_social.carente.model.InteresseAdocao;
import com.proj_social.carente.model.enums.StatusSolicitacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface InteresseAdocaoRepository extends JpaRepository<InteresseAdocao, Long> {
    List<InteresseAdocao> findByUsuarioId(Long usuarioId);
    List<InteresseAdocao> findByPetId(Long petId);
    List<InteresseAdocao> findByStatusSolicitacao(StatusSolicitacao status);
}
