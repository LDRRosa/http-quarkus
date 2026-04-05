package com.leandro.service;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import com.leandro.model.Mensagem;
import com.leandro.repository.MensagemRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class MensagemService {

    @Inject
    MensagemRepository repository;

    public List<Mensagem> listar() {
        return repository.listar();
    }

    public Optional<Mensagem> buscarPorId(Long id) {
        return repository.buscarPorId(id);
    }

    public Mensagem criar(Mensagem mensagem) {
        mensagem.setTimestamp(LocalDateTime.now());
        return repository.salvar(mensagem);
    }

    public boolean deletar(Long id) {
        return repository.deletar(id);
    }
}