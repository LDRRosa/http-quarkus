package com.leandro.repository;

import jakarta.enterprise.context.ApplicationScoped;
import com.leandro.model.Mensagem;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
public class MensagemRepository {

    private List<Mensagem> mensagens = new ArrayList<>();
    private AtomicLong contador = new AtomicLong(1);

    public List<Mensagem> listar() {
        return mensagens;
    }

    public Optional<Mensagem> buscarPorId(Long id) {
        return mensagens.stream()
                .filter(m -> m.getId().equals(id))
                .findFirst();
    }

    public Mensagem salvar(Mensagem mensagem) {
        mensagem.setId(contador.getAndIncrement());
        mensagens.add(mensagem);
        return mensagem;
    }

    public boolean deletar(Long id) {
        return mensagens.removeIf(m -> m.getId().equals(id));
    }
}