package com.proj_social.carente.model;

import com.proj_social.carente.model.enums.StatusSolicitacao;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "interesse_adocao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InteresseAdocao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet pet;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String motivo;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_solicitacao")
    @Builder.Default
    private StatusSolicitacao statusSolicitacao = StatusSolicitacao.Pendente;

    @Column(name = "data_solicitacao", updatable = false)
    @Builder.Default
    private LocalDateTime dataSolicitacao = LocalDateTime.now();

    @Column(name = "data_visita")
    private LocalDateTime dataVisita;
}
