package com.springmvc.demo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "products")
public class Product {
//    +-------------+--------------+------+-----+---------+-------+
//            | Field       | Type         | Null | Key | Default | Extra |
//            +-------------+--------------+------+-----+---------+-------+
//            | productID   | varchar(10)  | NO   | PRI | NULL    |       |
//            | categoryID  | varchar(10)  | YES  |     | NULL    |       |
//            | productName | varchar(50)  | YES  |     | NULL    |       |
//            | price       | int          | YES  |     | NULL    |       |
//            | description | varchar(200) | YES  |     | NULL    |       |
//            +-------------+--------------+------+-----+---------+-------+

    @Id
    @Column(name = "productID")
    private String productID;

    @Column(name = "categoryID")
    private String categoryID;

    @Column(name = "productName")
    @NotNull
    @NotBlank(message = "Product's name cannot be null")
    @Size(min = 3, max = 300)
    private String productName;

    @Column(name = "price")
    @NotNull
    @Min(0)
    private int price;

    @NotNull
    @NotBlank(message = "Product's description cannot be null")
    @Size(min = 5, max = 1000)
    @Column(name = "description")
    private String description;

    public Product(){}

    public Product(String productID, String categoryID, String productName, int price, String description) {
        this.productID = productID;
        this.categoryID = categoryID;
        this.productName = productName;
        this.price = price;
        this.description = description;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFormattedPrice() {
        return String.format("$%.2f", this.price);
    }
}
