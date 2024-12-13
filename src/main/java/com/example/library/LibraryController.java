package com.example.library;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibraryController {

    @FXML
    private TextField bookIdField, bookNameField, bookAuthorField, bookCategoryField, bookPriceField;
    @FXML
    private TableView<Book> bookTable;
    @FXML
    private TableColumn<Book, String> columnId, columnName, columnAuthor, columnCategory;
    @FXML
    private TableColumn<Book, Double> columnPrice;
    @FXML
    private TextArea resultArea;

    private final Connection connection;

    public LibraryController() {
        connection = DatabaseUtil.getConnection();
    }

    @FXML
    public void initialize() {
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        columnCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        loadBooks();
    }
    public void addBook() {
        String sql = "INSERT INTO Book (Book_ID, Book_Name, Book_Author, Book_Category, Book_Price) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, bookIdField.getText());
            stmt.setString(2, bookNameField.getText());
            stmt.setString(3, bookAuthorField.getText());
            stmt.setString(4, bookCategoryField.getText());
            stmt.setDouble(5, Double.parseDouble(bookPriceField.getText()));

            stmt.executeUpdate();
            resultArea.setText("Book added successfully.");
            loadBooks();
        } catch (SQLException e) {
            resultArea.setText("Error: " + e.getMessage());
        }
    }

    public void loadBooks() {
        String sql = "SELECT * FROM Book";
        List<Book> books = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                books.add(new Book(
                        rs.getString("Book_ID"),
                        rs.getString("Book_Name"),
                        rs.getString("Book_Author"),
                        rs.getString("Book_Category"),
                        rs.getDouble("Book_Price")
                ));
            }
            bookTable.getItems().setAll(books);
        } catch (SQLException e) {
            resultArea.setText("Error: " + e.getMessage());
        }
    }

    public void deleteBook() {
        String sql = "DELETE FROM Book WHERE Book_ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, bookIdField.getText());
            stmt.executeUpdate();

            resultArea.setText("Book deleted successfully.");
            loadBooks();
        } catch (SQLException e) {
            resultArea.setText("Error: " + e.getMessage());
        }
    }
}