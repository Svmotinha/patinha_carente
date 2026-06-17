package com.proj_social.carente.model;

import com.proj_social.carente.model.enums.TipoDoacao;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "doacao_apadrinhamento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoacaoApadrinhamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoDoacao tipo;

    @Column(name = "data_registro", updatable = false)
    @Builder.Default
    private LocalDateTime dataRegistro = LocalDateTime.now();
}
