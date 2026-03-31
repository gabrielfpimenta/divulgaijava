package com.itb.inf2am.divulgai.model.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "Usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(length = 100, nullable = false, unique = true)
    private String email;

    @Column(length = 100, nullable = false)
    private String senha;

    @Column(length = 10, nullable = true)
    private String nivelAcesso; // ADMIN, PRESTADOR, CLIENTE


    @Column(length = 100, nullable = false)
    private String ps_01;


    @Column(length = 100, nullable = false)
    private String ps_02;

    @Lob
    @Column(nullable = true)
    private byte[] foto;

    @Column(name = "data_cadastro", nullable = false)
    private LocalDateTime dataCadastro;

    @PrePersist
    protected void onCreate() {
        this.dataCadastro = LocalDateTime.now();
    }

    @Column
    private boolean statusUsuario;

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNivelAcesso() {
        return nivelAcesso;
    }

    public void setNivelAcesso(String nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }

    public String getPs_01() {
        return ps_01;
    }

    public void setPs_01(String ps_01) {
        this.ps_01 = ps_01;
    }

    public String getPs_02() {
        return ps_02;
    }

    public void setPs_02(String ps_02) {
        this.ps_02 = ps_02;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public boolean getStatusUsuario() {
        return statusUsuario;
    }

    public void setStatusUsuario(boolean statusUsuario) {
        this.statusUsuario = statusUsuario;
    }

}
