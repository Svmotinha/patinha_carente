package com.proj_social.carente.model;

import com.proj_social.carente.model.enums.CategoriaNoticia;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "noticia")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Noticia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String conteudo;

    @Column(length = 500)
    private String resumo;

    @Column(name = "url_imagem")
    private String urlImagem;

    @Column(name = "link_detalhes")
    private String linkDetalhes;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoriaNoticia categoria;

    @Column(name = "data_publicacao", updatable = false)
    @Builder.Default
    private LocalDateTime dataPublicacao = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Usuario autor;
}
