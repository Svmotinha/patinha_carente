package com.proj_social.carente.repository;

import com.proj_social.carente.model.DoacaoApadrinhamento;
import com.proj_social.carente.model.enums.TipoDoacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DoacaoApadrinhamentoRepository extends JpaRepository<DoacaoApadrinhamento, Long> {
    List<DoacaoApadrinhamento> findByUsuarioId(Long usuarioId);
    List<DoacaoApadrinhamento> findByPetId(Long petId);
    List<DoacaoApadrinhamento> findByTipo(TipoDoacao tipo);
}
