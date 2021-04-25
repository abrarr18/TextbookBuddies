package com.example.textbookbuddies.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Book {

    private String title;
    private String isbn;
    private String author;
    private String classes;
    private double price;
    private int number;
    private String email;

    public Book(){

    }
    public Book(JSONObject jsonObject) throws JSONException {
        this.title = jsonObject.getString("title");
        this.isbn = jsonObject.getString("isbn");
        this.author = jsonObject.getString("author");
        this.classes = jsonObject.getString("classes");
        this.price = jsonObject.getDouble("price");
        this.number = jsonObject.getInt("number");
        this.email = jsonObject.getString("email");

    }

    public Book(String title, String isbn, String author, String classes, double price, int number, String email) {
        this.title = title;
        this.isbn = isbn;
        this.author = author;
        this.classes = classes;
        this.price = price;
        this.number = number;
        this.email = email;
    }

    public static List<Book> fromJSONArray(JSONArray movieJsonArray) throws JSONException {
        List<Book> books = new ArrayList<>();
        for (int i=0; i<movieJsonArray.length(); i++){
            books.add(new Book(movieJsonArray.getJSONObject(i)));
        }
        return books;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getPrice() {
        return String.valueOf(price);
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getNumber() {
        return String.valueOf(number);
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}