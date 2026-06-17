package com.proj_social.carente.model;

import com.proj_social.carente.model.enums.*;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pet")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Especie especie;

    @Column(length = 50)
    private String raca;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Porte porte;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Sexo sexo;

    @Column(name = "idade_estimada", length = 50)
    private String idadeEstimada;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(columnDefinition = "TEXT")
    private String historia;

    @Column(name = "local_resgate")
    private String localResgate;

    @Column(name = "tempo_ong", length = 50)
    private String tempoOng;

    @Builder.Default
    private boolean vacinado = false;
    
    @Builder.Default
    private boolean castrado = false;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_adocao")
    @Builder.Default
    private StatusAdocao statusAdocao = StatusAdocao.Disponível;

    @Column(name = "condicao_saude", columnDefinition = "TEXT")
    private String condicaoSaude;

    @Column(name = "urgencia_apadrinhamento")
    @Builder.Default
    private boolean urgenciaApadrinhamento = false;

    @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FotoPet> fotos;

    @Column(name = "data_cadastro", updatable = false)
    @Builder.Default
    private LocalDateTime dataCadastro = LocalDateTime.now();
}
