package com.handson.postgres.Product;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.handson.postgres.json.ProductItem;
import com.handson.postgres.json.SortDirection;
import com.handson.postgres.utils.JsonUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    @Autowired
    ObjectMapper om;

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;

    public List<Product> searchProducts(String username, String title, String category,
                                        Integer limit, Integer offset, Integer sort, SortDirection sortDirection) {
        List<Product> queryRes = null;
        if (SortDirection.asc.equals(sortDirection)) {
            queryRes = productRepository.searchProducts(username, likeLowerOrEmptyString(title),
                    likeLowerOrEmptyString(category), limit, offset, sort);
        } else {
            queryRes = productRepository.searchProductsDesc(username, likeLowerOrEmptyString(title),
                    likeLowerOrEmptyString(category), limit, offset, sort);
        }
        return queryRes;
    }

    public List<ProductItem> searchProductsFormatted(String username, String title, String category,
                                                     Integer limit, Integer offset, Integer sort, SortDirection sortDirection) {
        List<Map<String, Object>> queryRes = productRepository.searchProductsFormatted(username, likeLowerOrEmptyString(title),
                    likeLowerOrEmptyString(category), limit, offset, sort);
        return JsonUtils.makeList(queryRes, ProductItem.class, om);
    }
    public Integer countProducts(String username, String title, String category) {
        return productRepository.countProducts(username, likeLowerOrEmptyString(title), likeLowerOrEmptyString(category));
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public static String likeLowerOrEmptyString(String str) {
        return str != null ? "%" + str.toLowerCase() + "%" : "";
    }

}