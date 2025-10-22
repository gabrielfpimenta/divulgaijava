package com.itb.inf2am.divulgai.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity

public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;


    @Column(length = 200, nullable = false)
    private String titulo;

    @Column(length = 200, nullable = false)
   private String descricao;


    @Column(length = 50, nullable = false)
    private String tipoFeedback;


    @Column(nullable = false)
   private LocalDateTime dataCadastro;

    @Column(length = 20, nullable = false)
   private String statusFeedback;

    @Column(nullable = false)
    private Long usuarioId;

    @Column(nullable = false)
    private Long prestadorId;

    // getters e setters
    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

    public Long getPrestadorId() { return prestadorId; }
    public void setPrestadorId(Long prestadorId) { this.prestadorId = prestadorId; }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipoFeedback() {
        return tipoFeedback;
    }

    public void setTipoFeedback(String tipoFeedback) {
        this.tipoFeedback = tipoFeedback;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getStatusFeedback() {
        return statusFeedback;
    }

    public void setStatusFeedback(String statusFeedback) {
        this.statusFeedback = statusFeedback;
    }
}
