package main.modul6.Organization;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import main.modul6.Actions.AdminAction;
import main.modul6.Model.*;
import main.modul6.Alert.AlertBox;


public class Admin extends User implements AdminAction {
    public Admin(String Username, String Password) {
        super(Username, Password);
    }
    public Admin(){}
    @Override
    public void DisplayAppMenu() {}


    @Override
    public boolean inputCheck(String UsernameInput, String PasswordInput) {
        for (User user : Userlist) {
            if (user instanceof Admin) {
                if (user.getUsername().equals(UsernameInput) && user.getPassword().equals(PasswordInput)) {
                    System.out.println("Selamat Datang Admin!!!.");
                    return true;
                }
            }
        }
        return false;
    }


    @Override
    public void ManageItems() {}

    @Override
    public void ManageUsers(){}


    public void AddMahasiswa(TableView<User> MahasiswaTable, TextField Username,TextField NIM){
        Mahasiswa mahasiswa = new Mahasiswa();
        try {
            if (Username.getText().equals("") || NIM.getText().equals("")) {
                throw new NullPointerException("Username or NIM tidak boleh kosong");
            }
            mahasiswa.setUsername(Username.getText());
            mahasiswa.setPassword(NIM.getText());
            MahasiswaTable.getItems().add(mahasiswa);
        }catch (NullPointerException e){
            AlertBox.WARNING("Username or NIM tidak boleh kosong");
        }
        Userlist.add(mahasiswa);
        Username.clear();
        NIM.clear();
    }
    public void RemoveMahasiswa(TableView MahasiswaTable){
        ObservableList<Mahasiswa> MahasiswaSelected = MahasiswaTable.getSelectionModel().getSelectedItems();
        ObservableList<Mahasiswa> AllAtribut = MahasiswaTable.getItems();

        try {
            if (MahasiswaSelected.isEmpty() || MahasiswaSelected == null) {
                throw new IndexOutOfBoundsException("Tabel Mahasiswa Sekarang Kosong");
            }

            List<Mahasiswa> RemoveIndeks = new ArrayList<>(MahasiswaSelected);
            AllAtribut.removeAll(RemoveIndeks);
        }catch (IndexOutOfBoundsException e){
            System.out.println(e.getMessage());
            AlertBox.INFORMATION("Tabel Mahasiswa Sekarang Kosong");
        }
    }

    public void setClaimed(TableView TabelLostItem){
        ObservableList<Item> ItemSelected = TabelLostItem.getSelectionModel().getSelectedItems();
            for (Item item : ItemSelected){
                String Status = item.getStatus();
                if (Status.equalsIgnoreCase("Reported")){
                    item.setStatus("Claimed");
                } else if (Status.equalsIgnoreCase("Claimed")) {
                    AlertBox.INFORMATION("Status Barang Sudah DI Ubah");
                }else {
                    AlertBox.WARNING("Status Barang Tidak Terdeteksi : " + Status);
                }

            }
        TabelLostItem.refresh();
    }

}
