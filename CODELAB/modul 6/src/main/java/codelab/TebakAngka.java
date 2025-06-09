package codelab;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Random;

public class TebakAngka extends Application {

    private int angkaRahasia;
    private int jumlahTebakan = 0;
    private final Random random = new Random();

    @Override
    public void start(Stage primaryStage) {
        generateAngkaBaru();

        Label labelJudul = new Label("🎯 Tebak Angka 1–100");
        labelJudul.setFont(Font.font("Arial", 22));
        labelJudul.setTextFill(Color.DARKBLUE);

        Label labelInput = new Label("Masukkan tebakkanmu!");
        labelInput.setFont(Font.font("Arial", 16));

        TextField textField = new TextField();
        textField.setPromptText("Masukkan angka di sini");
        textField.setMaxWidth(200);

        Button button = new Button("🧠 Coba Tebak!");
        button.setStyle("-fx-background-color: #28a745; -fx-text-fill: white;");
        button.setFont(Font.font(14));

        Label labelFeedback = new Label(" ");
        labelFeedback.setFont(Font.font(16));
        Label labelSkor = new Label("Jumlah percobaan: 0");

        button.setOnAction(e -> {
            if (button.getText().equals("Main Lagi")) {
                generateAngkaBaru();
                jumlahTebakan = 0;
                labelSkor.setText("Jumlah percobaan: 0");
                labelFeedback.setText(" ");
                button.setText("🧠 Coba Tebak!");
                button.setStyle("-fx-background-color: #28a745; -fx-text-fill: white;");
                textField.clear();
                textField.setDisable(false);
                return;
            }

            String input = textField.getText().trim();
            try {
                int tebakan = Integer.parseInt(input);
                jumlahTebakan++;

                if (tebakan < 1 || tebakan > 100) {
                    labelFeedback.setText("⚠ Masukkan angka antara 1 sampai 100!");
                    labelFeedback.setTextFill(Color.ORANGE);
                } else if (tebakan > angkaRahasia) {
                    labelFeedback.setText("▲ Terlalu besar!");
                    labelFeedback.setTextFill(Color.ORANGE);
                } else if (tebakan < angkaRahasia) {
                    labelFeedback.setText("▼ Terlalu kecil!");
                    labelFeedback.setTextFill(Color.ORANGE);
                } else {
                    labelFeedback.setText("✔ Tebakan benar!");
                    labelFeedback.setTextFill(Color.GREEN);
                    button.setText("Main Lagi");
                    button.setStyle("-fx-background-color: #0d6efd; -fx-text-fill: white;");
                    textField.setDisable(true);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Selamat!");
                    alert.setHeaderText(null);
                    alert.setContentText("Kamu berhasil menebak dalam " + jumlahTebakan + " kali percobaan.");
                    alert.showAndWait();
                }

                labelSkor.setText("Jumlah percobaan: " + jumlahTebakan);
                textField.clear();
            } catch (NumberFormatException ex) {
                labelFeedback.setText("⛔ Input harus berupa angka!");
                labelFeedback.setTextFill(Color.RED);
            }
        });

        VBox layout = new VBox(12, labelJudul, labelInput, textField, button, labelFeedback, labelSkor);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #eaf4fc;");

        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setTitle("Tebak Angka Advance");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void generateAngkaBaru() {
        angkaRahasia = random.nextInt(100) + 1;
        System.out.println("DEBUG: Angka Rahasia = " + angkaRahasia);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
