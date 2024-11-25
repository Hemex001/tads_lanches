package br.grupointegrado.lanches.controller;

import br.grupointegrado.lanches.dto.ClienteRequestDTO;
import br.grupointegrado.lanches.model.Cliente;
import br.grupointegrado.lanches.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")

public class ClienteController {
    @Autowired
    private ClienteRepository repository;

    // Ler todos os registros
    @GetMapping
    public ResponseEntity<List<Cliente>> findAll() {
        //return this.repository.findAll();
        return ResponseEntity.ok(this.repository.findAll());
    }

    // Ler um registro pelo Id
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Integer id) {
        //return this.repository.findById(id)
        //        .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

        Cliente cliente = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

        return ResponseEntity.ok(cliente);
    }

    // Gravar um registro
    @PostMapping
    public Cliente save(@RequestBody ClienteRequestDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setNome(dto.nome());
        cliente.setEmail(dto.email());
        cliente.setTelefone(dto.telefone());

        return this.repository.save(cliente);
    }

    // Editar um registro pelo id
    @PutMapping("/{id}")
    public Cliente update(@PathVariable Integer id, @RequestBody ClienteRequestDTO dto) {
        Cliente cliente = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

        cliente.setNome(dto.nome());
        cliente.setEmail(dto.email());
        cliente.setTelefone(dto.telefone());

        return this.repository.save(cliente);
    }

    // Apagar um registro pelo id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Cliente cliente = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
        this.repository.delete(cliente);
        return ResponseEntity.noContent().build();
    }



}
