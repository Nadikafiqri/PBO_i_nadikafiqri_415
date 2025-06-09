package com.praktikum.users;

import com.praktikum.actions.MahasiswaActions;
import com.praktikum.models.Item;
import com.praktikum.main.LoginSystem;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Mahasiswa extends User implements MahasiswaActions {

    public Mahasiswa(String nama, String nim) {
        super(nama, nim);
    }

    @Override
    public boolean login(String inputNama, String inputNim) {
        return inputNama.equalsIgnoreCase(getNama()) && inputNim.equals(getNim());
    }

    @Override
    public void displayAppMenu() {
        Scanner scanner = new Scanner(System.in);
        int pilihan = -1;

        while (pilihan != 0) {
            System.out.println("\n=== Menu Mahasiswa ===");
            System.out.println("1. Laporkan Barang Temuan/Hilang");
            System.out.println("2. Lihat Daftar Laporan");
            System.out.println("0. Logout");
            System.out.print("Pilih menu: ");
            try {
                pilihan = scanner.nextInt();
                scanner.nextLine();

                switch (pilihan) {
                    case 1 -> reportItem();
                    case 2 -> viewReportedItems();
                    case 0 -> System.out.println("Logout berhasil.");
                    default -> System.out.println("Pilihan tidak valid.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input harus berupa angka!");
                scanner.nextLine();
            }
        }
    }

    @Override
    public void reportItem() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nama Barang: ");
        String namaBarang = scanner.nextLine();
        System.out.print("Deskripsi Barang: ");
        String deskripsi = scanner.nextLine();
        System.out.print("Lokasi Ditemukan/Hilang: ");
        String lokasi = scanner.nextLine();

        Item item = new Item(namaBarang, deskripsi, lokasi, "Reported");
        LoginSystem.reportedItems.add(item);
        System.out.println(">> Laporan diterima. Terima kasih atas laporannya! <<");
    }

    @Override
    public void viewReportedItems() {
        if (LoginSystem.reportedItems.isEmpty()) {
            System.out.println("Belum ada laporan barang.");
        } else {
            System.out.println("=== Daftar Barang Dilaporkan ===");
            for (Item item : LoginSystem.reportedItems) {
                if ("Reported".equalsIgnoreCase(item.getStatus())) {
                    System.out.printf("- %s | %s | %s [%s]\n", item.getItemName(), item.getDescription(), item.getLocation(), item.getStatus());
                }
            }
        }
    }
}