package by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.service.api;

import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.dto.ProductDto;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface IProductService {
    Product get(Long id);
    Product save(Product product);
    Product update(Product product, Long id, LocalDateTime dateUpdate);
    void delete (Long id, LocalDateTime dateUpdate);
    Page<Product> getAll(Pageable pageable);
    Page<Product> getAll(String name, Pageable pageable);
    List<Product> getByName(String name);
}
