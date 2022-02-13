package com.desafio.desafiocompass.controller;

import com.desafio.desafiocompass.exception.ResourceNotFoundException;
import com.desafio.desafiocompass.model.Produto;
import com.desafio.desafiocompass.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;


@RestController
@RequestMapping("/products")
public class ProdutoController {
    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping
    public @ResponseBody
    Produto novoProduto(@Valid Produto produto) {
        produtoRepository.save(produto);
        return produto;

    }

    @PutMapping(path = "/{id}")
    public @ResponseBody
    Produto atualizarProduto(@PathVariable String id, @Valid Produto produto) {
        verificarExistencia(id);
        produtoRepository.save(produto);
        return produto;
    }


    @GetMapping
    public Iterable<Produto> obterProdutos() {
        return produtoRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Optional<Produto> obterProdutoId(@PathVariable String id) {
        verificarExistencia(id);
        return produtoRepository.findById(id);
    }

    @GetMapping("/search")
    public Iterable<Produto> ObterProdutoPrecoMinPrecoMaxNameDesc
            (@RequestParam(required = false, name = "min_price") Double
                     minPrice, @RequestParam(required = false, name = "max_price")
                     Double maxPrice, @RequestParam(required = false, name = "q") String
                     q1, @RequestParam(required = false, name = "q") String q2) {
        if (minPrice != null && q1 == null && q2 == null && maxPrice == null) {
            return produtoRepository.findByPriceGreaterThan(minPrice - 1);
        } else if (minPrice == null && q1 == null && q2 == null && maxPrice != null) {
            return produtoRepository.findByPriceLessThan(maxPrice + 1);
        } else if ((minPrice == null && q1 != null && q2 != null && maxPrice == null)) {
            return produtoRepository.findByNameOrDescription(q1, q2);
        } else if ((minPrice != null && q1 != null && q2 != null && maxPrice == null)) {
            return produtoRepository.findByPriceGreaterThanAndNameOrDescription(minPrice - 1, q1, q2);
        } else if ((minPrice == null && q1 != null && q2 != null && maxPrice != null)) {
            return produtoRepository.findByPriceLessThanAndNameOrDescription(maxPrice + 1, q1, q2);
        } else if ((minPrice != null && q1 == null && q2 == null && maxPrice != null)) {
            return produtoRepository.findByPriceBetween(minPrice - 1, maxPrice + 1);
        } else
            return produtoRepository.findByPriceBetweenAndNameOrDescription(minPrice, maxPrice, q1, q2);
    }

    @DeleteMapping("/{id}")
    public void delProduto(@PathVariable String id) {
        verificarExistencia(id);
        produtoRepository.deleteById(id);
    }

    private void verificarExistencia(String id) {
        if (!produtoRepository.findById(id).isPresent()) {
            throw new ResourceNotFoundException("ID não encontrado");
        }
    }
}

