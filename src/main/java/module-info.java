module com.cripto.cripto_javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.cripto.cripto_javafx to javafx.fxml;
    exports com.cripto.cripto_javafx;
}