package com.itb.inf2am.divulgai.model.services;


import com.itb.inf2am.divulgai.model.entity.Contato;
import com.itb.inf2am.divulgai.model.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContatoService {

    @Autowired       // Injeção de dependência
    private ContatoRepository contatoRepository;

    // Método responsável em listar todos os Contatos cadastrados no banco de dados

    public List<Contato> findAll() {
        return contatoRepository.findAll();

    }

    // Método responsável em criar a contato no banco de dados
    //CREATE DO CRUD
    public Contato save(Contato Contato) {
        Contato.setStatusContato("ATIVO");
        return contatoRepository.save(Contato);
    }

    // Método responsável em listar o contato por ID
    public Contato findById (Long id) {
        return contatoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com o id " + id));
    }

}
