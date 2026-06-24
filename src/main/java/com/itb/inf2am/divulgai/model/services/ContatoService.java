package com.itb.inf2am.divulgai.model.services;

import com.itb.inf2am.divulgai.model.entity.Contato;
import com.itb.inf2am.divulgai.model.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContatoService {

    @Autowired
    private ContatoRepository contatoRepository;

    public List<Contato> findAll() {
        return contatoRepository.findAll();
    }

    public Contato save(Contato contato) {
        contato.setStatusContato("ATIVO");
        return contatoRepository.save(contato);
    }

    public Contato findById(Long id) {
        return contatoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contato não encontrado com o id " + id));
    }

    public Contato update(Long id, Contato contato) {
        Contato existente = findById(id);

        if (contato.getTipoContato() != null) {
            existente.setTipoContato(contato.getTipoContato());
        }

        if (contato.getLink() != null) {
            existente.setLink(contato.getLink());
        }

        if (contato.getPrestadorId() != null) {
            existente.setPrestadorId(contato.getPrestadorId());
        }

        if (contato.getStatusContato() != null && !contato.getStatusContato().isBlank()) {
            existente.setStatusContato(contato.getStatusContato());
        } else {
            existente.setStatusContato("ATIVO");
        }

        return contatoRepository.save(existente);
    }
}