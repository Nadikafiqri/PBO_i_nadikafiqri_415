package main.modul6.Alert;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.modul6.Organization.Mahasiswa;

public class AlertBox {

    private static Alert alert;

    public static void WARNING(String Message){
        alert = new Alert(Alert.AlertType.WARNING);;
        alert.setTitle("Warning!!");
        alert.setHeaderText(null);
        alert.setContentText(Message);
        alert.show();
    }

    public static void EROR(String Message){
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(Message);
        alert.setHeaderText(null);
        alert.setTitle("EROR!!");
        alert.showAndWait();
    }

    public static void INFORMATION(String Message){
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setContentText(Message);
        alert.setHeaderText(null);
        alert.show();
    }
    public static void FormReport(TableView tableReport, String Tittle){
        Stage stage = new Stage();
        stage.setTitle(Tittle);
        Mahasiswa mahasiswa = new Mahasiswa();
        VBox Box = new VBox(10);
        Box.getStyleClass().add("backgroundAlert");
        Box.setPadding(new Insets(10,10,10,10));
        Label namaBarang = new Label("Nama Barang");
        Label deskripsiBarang = new Label("Deskripsi Barang");
        Label lokasiBarang = new Label("Lokasi Barang");

        TextField namaBarangTxt = new TextField();
        namaBarangTxt.setPromptText("Nama Barang");
        TextField deskripsiBarangTxt = new TextField();
        deskripsiBarangTxt.setPromptText("Deskripsi Barang");
        deskripsiBarangTxt.getStyleClass().add("Description");
        TextField lokasiBarangTxt = new TextField();
        lokasiBarangTxt.setPromptText("Lokasi Barang");

        HBox BoxReport = new HBox();
        Button Report = new Button("Report");
        BoxReport.getChildren().add(Report);
        BoxReport.setAlignment(Pos.CENTER);

        Report.setOnAction(event -> {
            mahasiswa.ReportItem(tableReport,namaBarangTxt,deskripsiBarangTxt,lokasiBarangTxt);
            stage.close();
        });

        Box.getChildren().addAll(namaBarang,namaBarangTxt,lokasiBarang,lokasiBarangTxt,deskripsiBarang,deskripsiBarangTxt,BoxReport);
        Scene AlertScene = new Scene(Box,350,350);
        AlertScene.getStylesheets().add(AlertBox.class.getResource("AlertStyle.css").toExternalForm());
        namaBarangTxt.setOnAction(e->lokasiBarangTxt.requestFocus());
        lokasiBarangTxt.setOnAction(e->deskripsiBarangTxt.requestFocus());
        deskripsiBarangTxt.setOnAction(e-> Report.fire());
        stage.setScene(AlertScene);
        stage.show();

    }

}
