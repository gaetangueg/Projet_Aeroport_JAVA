module com.example.ensea3dtp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ensea3dtp to javafx.fxml;
    exports com.example.ensea3dtp;
}