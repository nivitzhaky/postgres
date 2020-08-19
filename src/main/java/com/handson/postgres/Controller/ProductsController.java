package com.handson.postgres.Controller;

import com.handson.postgres.Product.ProductRepository;
import com.handson.postgres.Product.ProductService;
import com.handson.postgres.json.Pagination;
import com.handson.postgres.json.PaginationAndList;
import com.handson.postgres.json.Product;
import com.handson.postgres.json.SortDirection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static com.handson.postgres.Product.ProductRepository.*;

@RestController
@RequestMapping("/api/products")
public class ProductsController {

    @Autowired
    ProductService productService;

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public ResponseEntity<Void> addNewProduct(@RequestBody Product product) {
        productService.save(product);
        return new ResponseEntity<Void>(new HttpHeaders(), HttpStatus.CREATED);
    }


    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<PaginationAndList> search(Long productId, String title, String category,
                                                    @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "50")  Integer count,
                                                    @RequestParam(defaultValue = "patientCreatedTime") SortField sort ,
                                                    @RequestParam(defaultValue = "desc") SortDirection sortDirection)
    {
        List<Product> patients = productService.searchProducts(productId, title, category,
                count, (page - 1) * count, sort.ordinal() + 1, sortDirection);
        Integer total = productService.countProducts(productId, title, category);
        return ResponseEntity.ok(
                PaginationAndList.of(Pagination.of(page, (total / count) + 1, total), patients));
    }


}
