package com.itb.inf2am.divulgai.dto;

public class ServicoDTO {

    private String nome;
    private String descricao;
    private String foto; // BASE64 STRING

    private Long prestadorId;
    private Long categoriaId;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getFoto() { return foto; }
    public void setFoto(String foto) { this.foto = foto; }

    public Long getPrestadorId() { return prestadorId; }
    public void setPrestadorId(Long prestadorId) { this.prestadorId = prestadorId; }

    public Long getCategoriaId() { return categoriaId; }
    public void setCategoriaId(Long categoriaId) { this.categoriaId = categoriaId; }
}