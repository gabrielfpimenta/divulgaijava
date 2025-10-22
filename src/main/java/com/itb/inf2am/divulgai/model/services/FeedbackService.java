package com.itb.inf2am.divulgai.model.services;

import com.itb.inf2am.divulgai.model.entity.Feedback;
import com.itb.inf2am.divulgai.model.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    @Autowired       // Injeção de dependência
    private FeedbackRepository feedbackRepository;

    // Método responsável em listar todos os Feedbacks cadastrados no banco de dados

    public List<Feedback> findAll() {
        return feedbackRepository.findAll();

    }

    // Método responsável em Criar o Feedback no banco de dados

    public Feedback save(Feedback Feedback) {
        Feedback.setStatusFeedback("ATIVO");
        return feedbackRepository.save(Feedback);
    }


    // Método responsável em listar o feedback por ID
    public Feedback findById (Long id) {
        return feedbackRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com o id " + id));
    }

    // Método responsável em atualizar em atualizar o feedback
    public Feedback update(Long id, Feedback feedback) {
        Feedback feedbackExistente = findById(id);
        feedbackExistente.setDescricao(feedbackExistente.getDescricao());
        feedbackExistente.setStatusFeedback(feedback.getStatusFeedback());

        return feedbackRepository.save(feedbackExistente);
    }

    // Método responsável em excluir o feedback ( exclusão física )
    public void delete(Long id) {


        Feedback feedbackExistente = findById(id);
        feedbackRepository.delete(feedbackExistente);
    }
}
