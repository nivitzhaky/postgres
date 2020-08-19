package com.handson.postgres.Product;

import com.handson.postgres.json.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ProductRepository extends CrudRepository<Product,Long> {
    final String fields = "SELECT p.product_id , p.title , p.category ";

    final String from =" FROM products p ";

    final String where =
            " where (:product_id is null or product_id = :product_id)" +
                    " and (:title is null or lower(p.title) like :title) " +
                    " and (:category is null or lower(p.category) like :category)";

    final String order =   "  order by :sort  limit :limit offset :offset  ";

    final String orderdesc =   "  order by :sort desc limit :limit offset :offset  ";

    @Query(value = fields + from + where +  order , nativeQuery = true)
    List<Product> searchProducts(@Nullable @Param("product_id") Long productId,
                                 @Nullable @Param("title") String title,
                                 @Nullable @Param("category") String category,
                                 @Param("limit") Integer limit, @Param("offset") Integer offset, @Param("sort") Integer sort
    );

    @Query(value = fields + from + where + orderdesc , nativeQuery = true)
    List<Product> searchProductsDesc(@Nullable @Param("product_id") Long productId,
                                 @Nullable @Param("title") String title,
                                 @Nullable @Param("category") String category,
                                 @Param("limit") Integer limit, @Param("offset") Integer offset, @Param("sort") Integer sort
    );

    @Query(value = "SELECT count(*)  " + from + where, nativeQuery = true)
    Integer countProducts(@Nullable @Param("product_id") Long productId,
                          @Nullable @Param("title") String title,
                          @Nullable @Param("category") String category);

    public enum SortField {
        patientId,  title, category;
    }

}
