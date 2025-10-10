package com.itb.inf2am.divulgai.model.services;


import com.itb.inf2am.divulgai.model.entity.Regiao;
import com.itb.inf2am.divulgai.model.repository.RegiaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RegiaoService {

    @Autowired       // Injeção de dependência
    private RegiaoRepository regiaoRepository;

    // Método responsável em listar todos as Regiaos cadastradas no banco de dados
    //READ DO CRUD
    public List<Regiao> findAll() {

        return regiaoRepository.findAll();
    }

    // Método responsável em criar a Regiao no banco de dados
    //CREATE DO CRUD
    public Regiao save(Regiao Regiao) {
        Regiao.setStatusRegiao(true);
        return regiaoRepository.save(Regiao);
    }

    // Método responsável em listar o Regiao por ID
    public Regiao findById (Long id) {
        return regiaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com o id " + id));
    }



}
