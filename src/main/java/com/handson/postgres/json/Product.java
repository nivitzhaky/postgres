package com.handson.postgres.json;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="products")
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="product_id")
    private long productId;
    @Column(name="title")
    private String title;
    @Column(name="category")
    private String category;

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


}