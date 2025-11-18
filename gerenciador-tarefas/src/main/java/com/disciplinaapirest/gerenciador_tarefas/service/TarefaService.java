package com.disciplinaapirest.gerenciador_tarefas.service;

import com.disciplinaapirest.gerenciador_tarefas.model.Tarefa;
import com.disciplinaapirest.gerenciador_tarefas.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Marca a classe como um Componente de Serviço Spring
public class TarefaService {

    @Autowired
    private TarefaRepository repository;

    public List<Tarefa> buscarTodas() {
        return repository.findAll();
    }

    public Optional<Tarefa> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Tarefa salvar(Tarefa tarefa) {
        return repository.save(tarefa);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    public boolean existePorId(Long id) {
        return repository.existsById(id);
    }
}