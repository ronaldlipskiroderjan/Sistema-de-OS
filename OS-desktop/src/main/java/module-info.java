module com.sistema.os.osdesktop {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;
    requires static lombok;

    // Permite que a tela (View) converse com o seu Controller
    opens com.sistema.os.controller to javafx.fxml;

    // Abre a pasta model tanto para o Jackson quanto para o JavaFX na MESMA LINHA!
    opens com.sistema.os.model to com.fasterxml.jackson.databind, javafx.base;

    // Exporta a raiz para o Java conseguir dar o Play no HelloApplication
    exports com.sistema.os;
}