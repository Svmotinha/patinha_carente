package com.proj_social.carente.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "foto_pet")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FotoPet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet pet;

    @Column(name = "url_imagem", nullable = false)
    private String urlImagem;

    @Column(name = "is_principal")
    @Builder.Default
    private boolean isPrincipal = false;
}
