package main.modul6.Organization;


import java.util.Scanner;
//import java.util.stream.Collector;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;

import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import main.modul6.Alert.AlertBox;
import main.modul6.Model.*;
import main.modul6.Actions.MhsAction;

public class Mahasiswa extends User implements MhsAction{
    
    public Mahasiswa(String Username,String Password) {
        super(Username, Password);
    }
    public Mahasiswa(){

    };
    static Scanner Input = new Scanner(System.in);

    @Override
    public void ReportItem() {}
    public void ReportItem(TableView ReportItemtable, TextField ItemName, TextField DescriptionItem, TextField ItemLocation){

        String nama = ItemName.getText();
        String Description = DescriptionItem.getText();
        String location = ItemLocation.getText();

        try {
            if (nama.isEmpty() || Description.isEmpty() || location.isEmpty()){
                throw new Exception("Empty Field");
            }
            Item item = new Item(nama,Description,location);
            ReportItemtable.getItems().add(item);
            Item.Itemlist.add(item);
            ItemName.clear();
            DescriptionItem.clear();
            ItemLocation.clear();
        }catch (Exception e){
            AlertBox.WARNING(e.getMessage());
        }
    }


    @Override
    public void ViewReportItem(){}

    @Override
    public boolean inputCheck(String UsernameInput, String PasswordInput) {
        for (User user : Userlist) {
            if (user instanceof Mahasiswa) {
                if (user.getUsername().equals(UsernameInput) && user.getPassword().equals(PasswordInput)) {
                    System.out.println("Selamat Datang " + UsernameInput);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void DisplayAppMenu(){}

    
}