package main.modul6;

import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;


import javafx.util.Duration;
import main.modul6.Model.Item;
import main.modul6.Organization.Admin;
import main.modul6.Organization.Mahasiswa;
import main.modul6.Organization.User;
import main.modul6.Alert.AlertBox;

import java.util.stream.Collectors;

public class SceneControl {
    public Stage PrimaryStage;
    Mahasiswa mahasiswa;
    Admin admin;

    public SceneControl(Stage PrimaryStage){
        this.PrimaryStage = PrimaryStage;
        this.mahasiswa = new Mahasiswa();
        this.admin = new Admin();
        PrimaryStage.setTitle("Lost & Found Kampus");
    }

    public void getLoginScene(){

        if (PrimaryStage.isShowing()){
            PrimaryStage = (Stage) Window.getWindows().filtered(Window::isShowing).getFirst();
        }
        Scene scene;

        //Layout Utama
        VBox Layout = new VBox();
        VBox ChildLayout = new VBox();

        //Item
        Label LoginLabel = new Label("Login");
        LoginLabel.getStyleClass().add("LoginLabel");
        Label AppTittle = new Label("Sistem Lost & Found");
        ComboBox<String> AccessAccountBox;
        TextField UsernameInput;
        PasswordField PasswordInput;
        Button LoginBtn;
        Label AnounceLabel;



        Layout.getStyleClass().add("Loginlayout");
        ChildLayout.getStyleClass().add("childlayout");
        AppTittle.getStyleClass().add("Apptitle");
        VBox.setVgrow(ChildLayout, Priority.NEVER);


        //Pilihan Menu Akses
        AccessAccountBox = new ComboBox<>();
        AccessAccountBox.getItems().addAll(
                "Pilih Access",
                "Mahasiswa",
                    "Admin"
        );
        AccessAccountBox.getSelectionModel().selectFirst();

        UsernameInput = new TextField();
        UsernameInput.setPromptText("Username");
        UsernameInput.getStyleClass().add("userinput");
        PasswordInput = new PasswordField();
        PasswordInput.setPromptText("Password");
        PasswordInput.getStyleClass().add("userinput");


        //Tombol
        LoginBtn = new Button("Login");
        LoginBtn.getStyleClass().add("loginBtn");

        //Anounce Label
        AnounceLabel = new Label("Login Gagal,Periksa Kembali Koneksi!!");
        AnounceLabel.getStyleClass().add("AnnounceLabel");
        AnounceLabel.setVisible(false);

        ChildLayout.getChildren().addAll(AccessAccountBox,UsernameInput,PasswordInput,LoginBtn,AnounceLabel);
        Layout.getChildren().addAll(LoginLabel,AppTittle,ChildLayout);

        scene = new Scene(Layout,500,400);
        scene.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
        PrimaryStage.setScene(scene);
        PrimaryStage.show();

        LoginBtn.setOnAction(e-> CheckLogin(UsernameInput,PasswordInput,AccessAccountBox,AnounceLabel));
        UsernameInput.setOnAction(e-> PasswordInput.requestFocus());
        PasswordInput.setOnAction(e -> LoginBtn.fire());

    }

    public void getAdminsccene(){
        Label Greeting;
        GridPane gridPane;


        if (PrimaryStage.isShowing()){
            PrimaryStage = (Stage) Window.getWindows().filtered(Window::isShowing).getFirst();
        }


        gridPane = new GridPane(15,8);
        gridPane.setPadding(new Insets(10,10,10,25));
        gridPane.getStyleClass().add("GridBackground");


        Greeting = new Label("Halo administrator!!");
        Greeting.getStyleClass().add("greeting");


        Label Reporttittle;
        TableView<Item> TabelLostItem = new TableView<>();
        TableColumn<Item,String> ColumnNameItem;
        TableColumn<Item,String> ColumnDescriptionItem;
        TableColumn<Item,String> ColumnLocationItem;
        TableColumn<Item,String> ColumnStatusItem;
        Button ClaimedButton;
        TabelLostItem.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);


        Reporttittle = new Label("Lost Report Items");
        Reporttittle.getStyleClass().add("ReportTitle");
        ClaimedButton = new Button("Claimed");
        ClaimedButton.getStyleClass().add("Claimed");


        ColumnNameItem = new TableColumn<>("Name");
        ColumnNameItem.setCellValueFactory(new PropertyValueFactory<>("barangHilang"));
        ColumnNameItem.setMinWidth(100);

        ColumnDescriptionItem = new TableColumn<>("Description");
        ColumnDescriptionItem.setCellValueFactory(new PropertyValueFactory<>("deskripsiHilang"));
        ColumnDescriptionItem.setMinWidth(150);

        ColumnLocationItem = new TableColumn<>("Location");
        ColumnLocationItem.setCellValueFactory(new PropertyValueFactory<>("lokasiBarang"));
        ColumnLocationItem.setMinWidth(100);

        ColumnStatusItem = new TableColumn<>("Status");
        ColumnStatusItem.setCellValueFactory(new PropertyValueFactory<>("status"));
        ColumnStatusItem.setMinWidth(50);

        TabelLostItem.getColumns().addAll(ColumnNameItem,ColumnDescriptionItem,ColumnLocationItem,ColumnStatusItem);

        ObservableList<Item>  databaseItem = FXCollections.observableArrayList(
                Item.getItemlist()
        );

        TabelLostItem.setItems(databaseItem);


        TabelLostItem.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                PauseTransition delay = new PauseTransition(Duration.seconds(0.25)); // jeda 1 detik
                delay.setOnFinished(event -> TabelLostItem.getSelectionModel().clearSelection());
                delay.play();
            }
        });

        //Aksi Button
        ClaimedButton.setOnAction(e-> admin.setClaimed(TabelLostItem));

        Label Mahasiwadatalabel = new Label("Data Mahasiswa");
        TableView<User> MahasiswaTable = new TableView<>();
        TableColumn<User,String> NameColumn;
        TableColumn<User,String> NIMColumn;


        MahasiswaTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        Mahasiwadatalabel.getStyleClass().add("Mahasiwadatalabel");

        NameColumn = new TableColumn<>("Name");
        NameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        NameColumn.setMinWidth(100);

        NIMColumn = new TableColumn<>("NIM");
        NIMColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        NIMColumn.setMinWidth(100);


        MahasiswaTable.getColumns().addAll(NameColumn,NIMColumn);
        ObservableList<User>  databaseUser = FXCollections.observableArrayList(
                User.getUserlist().stream()
                        .filter(e-> e instanceof Mahasiswa)
                        .collect(Collectors.toList())
        );

        MahasiswaTable.setItems(databaseUser);

        MahasiswaTable.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                PauseTransition delay = new PauseTransition(Duration.seconds(0.25));
                delay.setOnFinished(event -> MahasiswaTable.getSelectionModel().clearSelection());
            }
        });


            //============Menambah Mahasiswa========================//
        HBox MahasiswaaddBox = new HBox(5);
        TextField UsernameInputField = new TextField();
        TextField NimInputField = new TextField();
        Button AddBtn;
        Button DelBtn;


        UsernameInputField.getStyleClass().add("UsernameInputField");
        UsernameInputField.setPromptText("Enter Mahasiswa Name");

        NimInputField.getStyleClass().add("NimInputField");
        NimInputField.setPromptText("NIM");

        AddBtn = new Button("Add");
        DelBtn = new Button("Del");
        AddBtn.getStyleClass().add("Add");
        DelBtn.getStyleClass().add("Del");

        MahasiswaaddBox.getChildren().addAll(UsernameInputField,NimInputField,AddBtn,DelBtn);


        AddBtn.setOnAction(e-> admin.AddMahasiswa(MahasiswaTable,UsernameInputField,NimInputField));
        DelBtn.setOnAction(e-> admin.RemoveMahasiswa(MahasiswaTable));
        UsernameInputField.setOnAction(e-> NimInputField.requestFocus());
        NimInputField.setOnAction(e-> AddBtn.fire());


        Button LogoutBtn = new Button("Logout");
        LogoutBtn.getStyleClass().add("Logout");
        LogoutBtn.setOnAction(e-> getLoginScene());


        gridPane.add(Greeting, 0, 0, 2, 1);
        gridPane.add(Reporttittle, 0, 1);
        gridPane.add(Mahasiwadatalabel, 1, 1);
        gridPane.add(TabelLostItem, 0, 2);
        gridPane.add(MahasiswaTable, 1, 2);
        gridPane.add(ClaimedButton, 0, 3);
        gridPane.add(MahasiswaaddBox, 1, 3);
        gridPane.add(LogoutBtn, 0, 4);



        Scene scene = new Scene(gridPane,900,550);
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                LogoutBtn.fire();
            }
        });
        scene.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
        SceneControl.this.PrimaryStage.setScene(scene);
        PrimaryStage.show();

    }

    public void getMahasiswaScene(String Username){
        if (PrimaryStage.isShowing()){
            PrimaryStage = (Stage) Window.getWindows().filtered(Window::isShowing).getFirst();
        }

        //Layout Utama
        VBox layout = new VBox(10);
        layout.getStyleClass().add("GridBackground");
        layout.setPadding(new Insets(10));

        //Greeting
        Label GreetingLabel = new Label("Welcome ," + Username + "!!!");
        GreetingLabel.getStyleClass().add("greeting");
        //Himbauan Pelaporan
        Label AnnounceLabel = new Label("Report Lost or findings Item ");
        AnnounceLabel.getStyleClass().add("Announce");


        //======================Tabel barang Hilang==============================
        Label ReportTabelLabel = new Label("Lost Item Table");
        ReportTabelLabel.getStyleClass().add("ReportTitle");

        TableView<Item> ItemTableReported = new TableView<>();


        TableColumn<Item,String> ColumnNameItem = new TableColumn<>("Name");
        ColumnNameItem.setCellValueFactory(new PropertyValueFactory<>("barangHilang"));
        //ColumnNameItem.setMinWidth(100);

        TableColumn<Item,String> ColumnDescriptionItem = new TableColumn<>("Description");
        ColumnDescriptionItem.setCellValueFactory(new PropertyValueFactory<>("deskripsiHilang"));
        //ColumnDescriptionItem.setMinWidth(150);

        TableColumn<Item,String> ColumnLocationItem = new TableColumn<>("Location");
        ColumnLocationItem.setCellValueFactory(new PropertyValueFactory<>("lokasiBarang"));
        //ColumnLocationItem.setMinWidth(100);

        ItemTableReported.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        ItemTableReported.getColumns().addAll(ColumnNameItem,ColumnDescriptionItem,ColumnLocationItem);

        ObservableList<Item>  databaseItem = FXCollections.observableArrayList(
                Item.getItemlist().stream()
                        .filter(e-> e.getStatus().equalsIgnoreCase("Reported"))
                        .collect(Collectors.toList())
        );

        ItemTableReported.setItems(databaseItem);

        AnnounceLabel.setOnMouseClicked(e-> AlertBox.FormReport(ItemTableReported,"Report Lost or findings Item"));
        Button LogoutBtn = new Button("Log out");
        LogoutBtn.getStyleClass().add("Logout");
        LogoutBtn.setTranslateY(10);
        LogoutBtn.setOnAction(e-> getLoginScene());

        layout.getChildren().addAll(GreetingLabel,AnnounceLabel,ReportTabelLabel,ItemTableReported,LogoutBtn);
        Scene scene = new Scene(layout,800,600);
        AnnounceLabel.setOnMouseClicked(e-> AlertBox.FormReport(ItemTableReported,"Report Lost or findings Item"));
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                LogoutBtn.fire();
            }
        });

        scene.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
        PrimaryStage.setScene(scene);
        PrimaryStage.show();

    }

    public void  CheckLogin(TextField Username,TextField Password,ComboBox comboBox,Label WarningLabel) {
        String Access;
        boolean val = false;

        try {
            Access = comboBox.getValue().toString();
            if (Access == null) {
                System.out.println("Akses Null");
                throw new NullPointerException("Akses Tidak dipilih");
            }
        }catch (NullPointerException e){
            WarningLabel.setVisible(true);
            System.out.println("Eror : " + e.getMessage());
            AlertBox.EROR(e.getMessage());
            return;
        }

        switch(Access){
            case "Mahasiswa" :
                System.out.println("Akses Mahasiwa");
                val = mahasiswa.inputCheck(Username.getText(),Password.getText());
                SceneChange(val,Access,Username.getText());
                WarningLabel.setVisible(true);
                Username.clear();
                Password.clear();
               break;
            case "Admin" :
                System.out.println("Akses Admin");
                val = admin.inputCheck(Username.getText(),Password.getText());
                SceneChange(val,Access,Username.getText());
                WarningLabel.setVisible(true);
                Username.clear();
                Password.clear();
               break;
        }
        if (!val){
            WarningLabel.setVisible(true);
            Username.clear();
            Password.clear();
        }

    }

    public void SceneChange(boolean changevalue,String ComboboxChoice,String Username){
        if (changevalue){
            switch (ComboboxChoice){
                case "Admin" -> getAdminsccene();
                case "Mahasiswa" -> getMahasiswaScene(Username);
            }
        }
    }

}
