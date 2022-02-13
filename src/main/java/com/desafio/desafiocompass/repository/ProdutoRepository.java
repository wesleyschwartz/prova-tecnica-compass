package com.desafio.desafiocompass.repository;

import com.desafio.desafiocompass.model.Produto;
import org.springframework.data.repository.CrudRepository;

public interface ProdutoRepository extends CrudRepository<Produto, String> {
    Iterable<Produto> findByPriceBetweenAndNameOrDescription(Double minPrice, Double maxPrice, String q1, String q2);

    Iterable<Produto> findByPriceGreaterThan(Double minPrice);

    Iterable<Produto> findByPriceLessThan(Double maxPrice);

    Iterable<Produto> findByNameOrDescription(String q1, String q2);

    Iterable<Produto> findByPriceGreaterThanAndNameOrDescription(double v, String q1, String q2);

    Iterable<Produto> findByPriceLessThanAndNameOrDescription(double v, String q1, String q2);

    Iterable<Produto> findByPriceBetween(double v, double v1);

}
