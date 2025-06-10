package main.modul6.Model;

import java.util.ArrayList;

public class Item {
    private int idLostitem;
    private int Indeks;
    private String BarangHilang;
    private String DeskripsiHilang;
    private String LokasiBarang;
    private String Status;

    public static ArrayList<Item> Itemlist = new ArrayList<>();

    static {
        Itemlist.add(new Item("buku","warna biru","lab"));
        Itemlist.add(new Item("bolpen","hitam","lab E"));
    }

    public Item(String barangHilang, String deskripsiHilang, String lokasiBarang) {
        idLostitem = (int)(Math.random()*101);
        Indeks = Itemlist.size() + 1;
        BarangHilang = barangHilang;
        DeskripsiHilang = deskripsiHilang;
        LokasiBarang = lokasiBarang;
        Status = "Reported";
    }

    public void AddItem(Item item){
        Itemlist.add(item);
    }
    
    public int getIndeks() {
        return Indeks;
    }

    public int getIdLostitem() {
        return idLostitem;
    }
    public String getBarangHilang() {
        return BarangHilang;
    }
    public String getDeskripsiHilang() {
        return DeskripsiHilang;
    }
    public String getLokasiBarang() {
        return LokasiBarang;
    }

    public String getStatus() {
        return Status;
    }

    public Item setStatus(String status) {
        Status = status;
        return null;
    }

    public void setIdLostitem(int idLostitem) {
        this.idLostitem = idLostitem;
    }

    public void setIndeks(int indeks) {
        Indeks = indeks;
    }

    public void setBarangHilang(String barangHilang) {
        BarangHilang = barangHilang;
    }

    public void setDeskripsiHilang(String deskripsiHilang) {
        DeskripsiHilang = deskripsiHilang;
    }

    public void setLokasiBarang(String lokasiBarang) {
        LokasiBarang = lokasiBarang;
    }

    public static ArrayList<Item> getItemlist() {
        return Itemlist;
    }

    public static void setItemlist(ArrayList<Item> itemlist) {
        Itemlist = itemlist;
    }

    @Override
    public String toString(){
        return
        "===============Data Barang================\n"
        + "Id Barang    : " + this.idLostitem + "\n"
        + "Indeks Barang: " + this.Indeks + "\n"
        + "Nama Barang  : " + this.BarangHilang + "\n"
        + "Deskripsi    : " + this.DeskripsiHilang + "\n"
        + "Lokasi       : " + this.LokasiBarang + "\n"
        + "Status Barang: " + this.Status + "\n"
        +"=========================================="
        + "\n";
    }



}
