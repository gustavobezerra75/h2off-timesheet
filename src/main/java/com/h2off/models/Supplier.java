package com.h2off.models;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Supplier {
    @Id private Long id;
    private String name;
    private String product;
    private String productDetails;
    private String coverageAllowed;

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(String productDetails) {
        this.productDetails = productDetails;
    }

    public String getCoverageAllowed() {
        return coverageAllowed;
    }

    public void setCoverageAllowed(String coverageAllowed) {
        this.coverageAllowed = coverageAllowed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
