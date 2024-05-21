module com.mysql.mysqljdbcvostro {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.logging; // Add this line to require the java.logging module
    requires java.sql;     // Add this line to require the java.sql module

    opens com.mysql.mysqljdbcvostro to javafx.fxml;
    exports com.mysql.mysqljdbcvostro;
}

