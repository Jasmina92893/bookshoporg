package com.example.library;

public class Book {
    private final String id;
    private final String name;
    private final String author;
    private final String category;
    private final double price;

    public Book(String id, String name, String author, String category, double price) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.category = category;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }
}