package com.handson.postgres.Product;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Map;

public interface ProductRepository extends CrudRepository<Product,Long> {
    final String fields = "SELECT p.product_id, p.title , p.category ";

    final String fieldsFormatted = "SELECT  p.title , p.category ";

    final String from =" FROM products p ";

    final String where =
            "where (:title = '' or lower(p.title) like :title) " +
                    " and (:category = '' or lower(p.category) like :category)";

    final String order =   "  order by :sort  limit :limit offset :offset  ";

    final String orderdesc =   "  order by :sort desc limit :limit offset :offset  ";

    @Query(value = fields + from + where +  order , nativeQuery = true)
    List<Product> searchProducts(
                                 @Nullable @Param("title") String title,
                                 @Nullable @Param("category") String category,
                                 @Param("limit") Integer limit, @Param("offset") Integer offset, @Param("sort") Integer sort
    );

    @Query(value = fields + from + where + orderdesc , nativeQuery = true)
    List<Product> searchProductsDesc(
                                 @Nullable @Param("title") String title,
                                 @Nullable @Param("category") String category,
                                 @Param("limit") Integer limit, @Param("offset") Integer offset, @Param("sort") Integer sort
    );

    @Query(value = "SELECT count(*)  " + from + where, nativeQuery = true)
    Integer countProducts(
                          @Nullable @Param("title") String title,
                          @Nullable @Param("category") String category);

    @Query(value = fieldsFormatted + from + where +  order , nativeQuery = true)
    List<Map<String, Object>> searchProductsFormatted(
            @Nullable @Param("title") String title,
            @Nullable @Param("category") String category,
            @Param("limit") Integer limit, @Param("offset") Integer offset, @Param("sort") Integer sort
    );

    public enum SortField {
        productId,  title, category;
    }

}
