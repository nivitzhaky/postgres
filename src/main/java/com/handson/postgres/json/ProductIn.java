package com.handson.postgres.json;

import com.handson.postgres.Product.Product;

import java.util.Objects;

public class ProductIn {
    String title;
    String category;

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public Product createProduct(String username) {
        Product res = new Product();
        res.setCategory(this.category);
        res.setTitle(this.title);
        res.setUsername(username);
        return res;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductIn productIn = (ProductIn) o;
        return Objects.equals(title, productIn.title) &&
                Objects.equals(category, productIn.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, category);
    }
}
