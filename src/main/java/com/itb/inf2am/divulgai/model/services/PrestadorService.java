package com.itb.inf2am.divulgai.model.services;


import com.itb.inf2am.divulgai.model.entity.Prestador;
import com.itb.inf2am.divulgai.model.repository.PrestadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrestadorService {

    @Autowired       // Injeção de dependência
    private PrestadorRepository prestadorRepository;

    // Método responsável em listar todos os Prestadors cadastrados no banco de dados

    public List<Prestador> findAll() {
        return prestadorRepository.findAll();

    }

    // Método responsável em Criar o Prestador no banco de dados

    public Prestador save(Prestador Prestador) {
        Prestador.setStatusPrestador(true);
        return prestadorRepository.save(Prestador);
    }


    // Método responsável em listar o prestador por ID
    public Prestador findById (Long id) {
        return prestadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com o id " + id));
    }

}
