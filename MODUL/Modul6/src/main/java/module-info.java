module main.modul6 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    opens main.modul6 to javafx.fxml;
    opens main.modul6.Actions to javafx.fxml;
    opens main.modul6.Model to javafx.fxml,javafx.base;
    opens main.modul6.Organization to javafx.base;
    opens main.modul6.Alert to javafx.fxml;

    exports main.modul6;
    exports main.modul6.Alert;
}