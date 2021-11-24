package by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.controller;

import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.model.Product;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.service.api.IProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Product> get(@PathVariable Long id) {
        try {
            Product product = productService.get(id);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Product> save(@RequestBody Product product) {
        try {
            Product product1 = productService.save(product);
            return new ResponseEntity<>(product1, HttpStatus.CREATED);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @RequestMapping(value = "/{id}/dt_update/{dt_update}", method = RequestMethod.PUT)
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product product,
                                          @PathVariable (name = "dt_update") Long dateUpdate) {

        try {
            LocalDateTime date = LocalDateTime.ofInstant(Instant.ofEpochMilli(dateUpdate), ZoneId.systemDefault());
            Product product1 = this.productService.update(product, id, date);
            return new ResponseEntity<>(product1, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @RequestMapping(value = "/{id}/dt_update/{dt_update}", method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> delete(@PathVariable (name = "id") long id,
                                                    @PathVariable (name = "dt_update") long dateUpdate){

        try {
            LocalDateTime date = LocalDateTime.ofInstant(Instant.ofEpochMilli(dateUpdate), ZoneId.systemDefault());
            productService.delete(id, date);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Product>> getList(@RequestParam String name) {
        List<Product> products = productService.getByName(name);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<Product>> getAllProducts(@RequestParam(value = "page", defaultValue = "0") int page,
                                                        @RequestParam(value = "size", defaultValue = "10") int size,
                                                        @RequestParam(value = "name", required = false) String name){

        Pageable pageable = PageRequest.of(page, size, Sort.by("name"));
        Page<Product> products;
        if (name != null) {
            products = productService.getAll(name, pageable);
        } else {
            products = productService.getAll(pageable);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
