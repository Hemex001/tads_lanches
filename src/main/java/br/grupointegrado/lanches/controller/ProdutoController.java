package br.grupointegrado.lanches.controller;

import br.grupointegrado.lanches.dto.ProdutoRequestDTO;
import br.grupointegrado.lanches.model.Produto;
import br.grupointegrado.lanches.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")

public class ProdutoController {
    @Autowired
    private ProdutoRepository repository;

    // Ler todos os registros
    @GetMapping
    public ResponseEntity<List<Produto>> findAll() {
        //return this.repository.findAll();
        return ResponseEntity.ok(this.repository.findAll());
    }

    // Ler um registro pelo Id
    @GetMapping("/{id}")
    public ResponseEntity<Produto> findById(@PathVariable Integer id) {
        //return this.repository.findById(id)
        //        .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        Produto produto = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        return ResponseEntity.ok(produto);
    }

    // Gravar um registro
    @PostMapping
    public Produto save(@RequestBody ProdutoRequestDTO dto) {
        Produto produto = new Produto();
        produto.setNome(dto.nome());
        produto.setValor(dto.valor());
        produto.setDescricao(dto.descricao());

        return this.repository.save(produto);
    }

    // Editar um registro pelo id
    @PutMapping("/{id}")
    public Produto update(@PathVariable Integer id, @RequestBody ProdutoRequestDTO dto) {
        Produto produto = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        produto.setNome(dto.nome());
        produto.setValor(dto.valor());
        produto.setDescricao(dto.descricao());

        return this.repository.save(produto);
    }

    // Apagar um registro pelo id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Produto produto = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));
        this.repository.delete(produto);
        return ResponseEntity.noContent().build();
    }



}
