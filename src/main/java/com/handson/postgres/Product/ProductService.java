package com.handson.postgres.Product;


import com.handson.postgres.json.Product;
import com.handson.postgres.json.SortDirection;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductService {


    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;

    public List<Product> searchProducts(Long ProductId, String title, String category,
                                            Integer limit, Integer offset, Integer sort, SortDirection sortDirection) {
        List<Product> queryRes = null;
        if (SortDirection.asc.equals(sortDirection)) {
            queryRes = productRepository.searchProducts(ProductId, likeLowerOrNull(title),
                    likeLowerOrNull(category), limit, offset, sort);
        } else {
            queryRes = productRepository.searchProductsDesc(ProductId, likeLowerOrNull(title),
                    likeLowerOrNull(category), limit, offset, sort);
        }
        return queryRes;
    }

    public Integer countProducts(Long productId, String title, String category) {
        return productRepository.countProducts(productId, likeLowerOrNull(title), likeLowerOrNull(category));
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public static String likeLowerOrNull(String str) {
        return str != null ? "%" + str.toLowerCase() + "%" : null;
    }

}