package com.disciplinaapirest.gerenciador_tarefas.controller;

import com.disciplinaapirest.gerenciador_tarefas.model.Tarefa;
import com.disciplinaapirest.gerenciador_tarefas.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid; // Necessário para habilitar a validação

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tarefas")
public class TarefaController {

    @Autowired
    private TarefaRepository repository;

    // GET: Lista todas as tarefas
    @GetMapping
    public List<Tarefa> listarTodas() {
        return repository.findAll();
    }

    // POST: Cria uma nova tarefa
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Tarefa criarTarefa(@Valid @RequestBody Tarefa novaTarefa) { // <-- Adicionada a anotação @Valid aqui
        return repository.save(novaTarefa);
    }

    // PUT: Atualiza uma tarefa
    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizarTarefa(@PathVariable Long id, @Valid @RequestBody Tarefa tarefaAtualizada) {
        Optional<Tarefa> tarefaExistente = repository.findById(id);

        if (tarefaExistente.isPresent()) {
            Tarefa tarefa = tarefaExistente.get();
            tarefa.setTitulo(tarefaAtualizada.getTitulo());
            tarefa.setDescricao(tarefaAtualizada.getDescricao());
            tarefa.setConcluida(tarefaAtualizada.isConcluida());
            return ResponseEntity.ok(repository.save(tarefa));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE: Exclui uma tarefa
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTarefa(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}