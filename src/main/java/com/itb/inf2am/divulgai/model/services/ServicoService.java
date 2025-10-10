package com.itb.inf2am.divulgai.model.services;


import com.itb.inf2am.divulgai.model.entity.Servico;
import com.itb.inf2am.divulgai.model.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicoService {

    @Autowired       // Injeção de dependência
    private ServicoRepository servicoRepository;

    // Método responsável em listar todos as Servicos cadastradas no banco de dados
    //READ DO CRUD
    public List<Servico> findAll() {

        return servicoRepository.findAll();
    }

    // Método responsável em criar a Servico no banco de dados
    //CREATE DO CRUD
    public Servico save(Servico Servico) {
        Servico.setStatusServico(true);
        return servicoRepository.save(Servico);
    }

    // Método responsável em listar o servico por ID
    public Servico findById (Long id) {
        return servicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com o id " + id));
    }

}
