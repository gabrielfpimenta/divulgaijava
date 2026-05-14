package com.itb.inf2am.divulgai.model.services;

import com.itb.inf2am.divulgai.model.entity.Prestador;
import com.itb.inf2am.divulgai.model.repository.PrestadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrestadorService {

    @Autowired
    private PrestadorRepository prestadorRepository;

    public List<Prestador> findAll() {
        return prestadorRepository.findAll();
    }

    /**
     * Cadastro:
     * - Se não vier status, entra em EM_ANALISE.
     * - Se vier status, normaliza.
     */
    public Prestador save(Prestador prestador) {
        String status = normalizeStatus(prestador.getStatusPrestador());

        if (status == null) {
            prestador.setStatusPrestador("EM_ANALISE");
        } else {
            prestador.setStatusPrestador(status);
        }

        return prestadorRepository.save(prestador);
    }

    public Prestador findById(Long id) {
        return prestadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prestador não encontrado com o id " + id));
    }

    /**
     * Atualização:
     * - NÃO força EM_ANALISE.
     * - Atualiza status apenas se vier no body.
     * - Mantém os outros campos existentes quando não enviados.
     */
    public Prestador update(Long id, Prestador prestador) {
        Prestador existente = findById(id);

        if (prestador.getNome() != null) existente.setNome(prestador.getNome());
        if (prestador.getDataNascimento() != null) existente.setDataNascimento(prestador.getDataNascimento());
        if (prestador.getCpf() != null) existente.setCpf(prestador.getCpf());
        if (prestador.getGenero() != null) existente.setGenero(prestador.getGenero());
        if (prestador.getTelefone() != null) existente.setTelefone(prestador.getTelefone());
        if (prestador.getLogradouro() != null) existente.setLogradouro(prestador.getLogradouro());
        if (prestador.getNumeroResidencial() != null) existente.setNumeroResidencial(prestador.getNumeroResidencial());
        if (prestador.getComplemento() != null) existente.setComplemento(prestador.getComplemento());
        if (prestador.getCep() != null) existente.setCep(prestador.getCep());
        if (prestador.getBairro() != null) existente.setBairro(prestador.getBairro());
        if (prestador.getCidade() != null) existente.setCidade(prestador.getCidade());
        if (prestador.getUf() != null) existente.setUf(prestador.getUf());
        if (prestador.getUsuario() != null) existente.setUsuario(prestador.getUsuario());

        String novoStatus = normalizeStatus(prestador.getStatusPrestador());
        if (novoStatus != null) {
            existente.setStatusPrestador(novoStatus);
        }

        return prestadorRepository.save(existente);
    }

    private String normalizeStatus(String status) {
        if (status == null) return null;

        String s = status.trim().toUpperCase();
        if (s.isBlank()) return null;

        return switch (s) {
            case "ATIVO", "INATIVO", "EM_ANALISE" -> s;
            default -> throw new IllegalArgumentException("Status inválido: " + status);
        };
    }
}