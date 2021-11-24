package by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.service;

import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.dao.api.IProductDao;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.model.Product;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.security.UserHolder;
import by.it_academy.jd2.Mk_JD2_82_21.calorie_countig_app.service.api.IProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductService implements IProductService {
    private final IProductDao productDao;
    private final UserHolder userHolder;

    public ProductService(IProductDao productDao, UserHolder userHolder) {
        this.productDao = productDao;
        this.userHolder = userHolder;
    }

    @Override
    public Product get (Long id) {
        return productDao.findById(id)
                .orElseThrow(
                        () -> new IllegalArgumentException("Product по ID не найден!")
                );
    }

    @Override
    public Product save(Product product) {
        product.setUser(userHolder.getUser());
        LocalDateTime localDateTime = LocalDateTime.now();
        product.setDateCreate(localDateTime);
        product.setDateUpdate(product.getDateCreate());
        return this.productDao.save(product);
    }

    @Override
    public Product update(Product product, Long id, LocalDateTime dateUpdate) {
        Product product1 = get(id);
        product1.setName(product.getName());
        product1.setBrand(product.getBrand());
        product1.setCalories(product.getCalories());
        product1.setProtein(product.getProtein());
        product1.setFats(product.getFats());
        product1.setCarbohydrates(product.getCarbohydrates());
        product1.setMeasure(product.getMeasure());
        product1.setUser(product.getUser());
        product1.setDateUpdate(LocalDateTime.now());
        return productDao.save(product1);
    }

    @Override
    public void delete(Long id, LocalDateTime dateUpdate)  {
        Product product = get(id);
        if (product == null) {
            throw new IllegalArgumentException("Product по ID не найден!");
        }
        productDao.deleteById(id);
    }

    @Override
    public Page<Product> getAll(Pageable pageable) {
        return productDao.findAll(pageable);
    }

    @Override
    public Page<Product> getAll(String name, Pageable pageable) {
        return productDao.findProductsByNameContains(name, pageable);
    }

    @Override
    public List<Product> getByName(String name) {
        return this.productDao.findByName(name);
    }
}
