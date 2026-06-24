package com.itb.inf2am.divulgai.dto;

import com.itb.inf2am.divulgai.model.entity.Servico;
import java.util.Base64;

public class ServicoDTO {

    private String nome;
    private String descricao;
    private String foto; // BASE64 STRING

    private Long prestadorId;
    private Long categoriaId;

    // obrigatório para Jackson
    public ServicoDTO() {
    }

    // Entity -> DTO
    public ServicoDTO(Servico servicoSalvo) {
        this.nome = servicoSalvo.getNome();
        this.descricao = servicoSalvo.getDescricao();

        this.foto = servicoSalvo.getFoto() != null
                ? Base64.getEncoder().encodeToString(servicoSalvo.getFoto())
                : null;

        this.prestadorId = servicoSalvo.getPrestador() != null
                ? servicoSalvo.getPrestador().getId()
                : null;

        this.categoriaId = servicoSalvo.getCategoria() != null
                ? servicoSalvo.getCategoria().getId()
                : null;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }


    public Long getPrestadorId() {
        return prestadorId;
    }

    public void setPrestadorId(Long prestadorId) {
        this.prestadorId = prestadorId;
    }


    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public Integer getContador() {
        return 0;
    }
}