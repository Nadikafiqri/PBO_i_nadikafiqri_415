package com.praktikum.users;

import com.praktikum.actions.AdminActions;
import com.praktikum.models.Item;
import com.praktikum.main.LoginSystem;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Admin extends User implements AdminActions {

    private String username;
    private String password;

    public Admin(String nama, String nim, String username, String password) {
        super(nama, nim);
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean login(String inputUsername, String inputPassword) {
        return inputUsername.equals(username) && inputPassword.equals(password);
    }

    @Override
    public void displayAppMenu() {
        Scanner scanner = new Scanner(System.in);
        int pilihan = -1;

        while (pilihan != 0) {
            System.out.println("\n=== Menu Admin ===");
            System.out.println("1. Kelola Laporan Barang");
            System.out.println("2. Kelola Data Mahasiswa");
            System.out.println("0. Logout");
            System.out.print("Pilih menu: ");
            try {
                pilihan = scanner.nextInt();
                scanner.nextLine();

                switch (pilihan) {
                    case 1 -> manageItems();
                    case 2 -> manageUsers();
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
    public void manageItems() {
        Scanner scanner = new Scanner(System.in);
        int pilihan = -1;

        while (pilihan != 0) {
            System.out.println("\n=== Kelola Laporan Barang ===");
            System.out.println("1. Lihat Semua Laporan");
            System.out.println("2. Tandai Barang Telah Diambil");
            System.out.println("0. Kembali");
            System.out.print("Pilih menu: ");
            try {
                pilihan = scanner.nextInt();
                scanner.nextLine();

                switch (pilihan) {
                    case 1 -> {
                        if (LoginSystem.reportedItems.isEmpty()) {
                            System.out.println("Belum ada laporan.");
                        } else {
                            for (int i = 0; i < LoginSystem.reportedItems.size(); i++) {
                                Item item = LoginSystem.reportedItems.get(i);
                                System.out.printf("%d. %s - %s - %s [%s]\n",
                                    i, item.getItemName(), item.getDescription(), item.getLocation(), item.getStatus());
                            }
                        }
                    }
                    case 2 -> {
                        System.out.print("Masukkan indeks barang: ");
                        try {
                            int index = scanner.nextInt();
                            scanner.nextLine();
                            Item item = LoginSystem.reportedItems.get(index);
                            item.setStatus("Claimed");
                            System.out.println("Barang berhasil ditandai sebagai 'Claimed'.");
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Indeks tidak valid.");
                        } catch (InputMismatchException e) {
                            System.out.println("Input harus berupa angka!");
                            scanner.nextLine();
                        }
                    }
                    case 0 -> {}
                    default -> System.out.println("Pilihan tidak valid.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input harus berupa angka!");
                scanner.nextLine();
            }
        }
    }

    @Override
    public void manageUsers() {
        Scanner scanner = new Scanner(System.in);
        int pilihan = -1;

        while (pilihan != 0) {
            System.out.println("\n=== Kelola Mahasiswa ===");
            System.out.println("1. Tambah Mahasiswa");
            System.out.println("2. Hapus Mahasiswa");
            System.out.println("0. Kembali");
            System.out.print("Pilih menu: ");
            try {
                pilihan = scanner.nextInt();
                scanner.nextLine();

                switch (pilihan) {
                    case 1 -> {
                        System.out.print("Nama: ");
                        String nama = scanner.nextLine();
                        System.out.print("NIM: ");
                        String nim = scanner.nextLine();
                        LoginSystem.userList.add(new Mahasiswa(nama, nim));
                        System.out.println("Mahasiswa berhasil ditambahkan.");
                    }
                    case 2 -> {
                        System.out.print("Masukkan NIM mahasiswa yang akan dihapus: ");
                        String nim = scanner.nextLine();
                        boolean removed = LoginSystem.userList.removeIf(user ->
                            user instanceof Mahasiswa && user.getNim().equals(nim));
                        System.out.println(removed ? "Mahasiswa berhasil dihapus." : "Mahasiswa tidak ditemukan.");
                    }
                    case 0 -> {}
                    default -> System.out.println("Pilihan tidak valid.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input harus berupa angka!");
                scanner.nextLine();
            }
        }
    }
}