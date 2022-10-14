package br.com.fiap.productcrud.controller;

import br.com.fiap.productcrud.core.domain.dto.ProductDto;
import br.com.fiap.productcrud.core.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @CrossOrigin
    @PostMapping
    @Transactional
    public ResponseEntity<ProductDto> create(@RequestBody ProductDto product, UriComponentsBuilder uriBuilder) {
        ProductDto savedMail = service.create(product);

        URI uri = uriBuilder.path("/product/{id}").buildAndExpand(savedMail.getId()).toUri();
        return ResponseEntity.created(uri).body(savedMail);
    }

    @CrossOrigin
    @GetMapping
    public  ResponseEntity<?> findAll() {
        List<ProductDto> response = service.findAll();

        if (response.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(response);
    }
    
    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<?> detail(@PathVariable(required = true, value = "id") Integer id) {
        ProductDto response = service.detail(id);

        if (response == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(response);
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable(required = true, value = "id") Integer id) {
        boolean response = service.delete(id);

        if (!response) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }

    @CrossOrigin
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> update(@PathVariable(required = true, value = "id") Integer id, @RequestBody ProductDto product) {
        ProductDto response = service.update(id, product);

        if (response == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(response);
    }

}
