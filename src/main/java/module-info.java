module org.example.bookshoporg {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql; // This is required for using java.sql package

    opens com.example.library to javafx.fxml;
    exports com.example.library;
}

