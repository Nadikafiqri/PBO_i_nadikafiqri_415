package com.praktikum.main;

import com.praktikum.users.Admin;
import com.praktikum.users.Mahasiswa;
import com.praktikum.users.User;

import java.util.Scanner;

public class LoginSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Admin admin = new Admin("Admin", "");
        Mahasiswa mahasiswa = new Mahasiswa("nadika fiqri", "202410370110415");

        System.out.println("Pilih login:");
        System.out.println("1. Admin");
        System.out.println("2. Mahasiswa");
        System.out.print("Masukkan pilihan (1/2): ");

        if (!scanner.hasNextInt()) {
            System.out.println("Input harus berupa angka!");
            return;
        }

        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        User currentUser = null;

        if (choice == 1) {
            System.out.print("Masukkan username: ");
            String username = scanner.nextLine();
            System.out.print("Masukkan password: ");
            String password = scanner.nextLine();

            if (admin.login(username, password)) {
                currentUser = admin;
            } else {
                System.out.println("Login Admin gagal!");
            }
        } else if (choice == 2) {
            System.out.print("Masukkan nama: ");
            String name = scanner.nextLine();
            System.out.print("Masukkan NIM: ");
            String nim = scanner.nextLine();

            if (mahasiswa.login(name, nim)) {
                currentUser = mahasiswa;
            } else {
                System.out.println("Login Mahasiswa gagal!");
            }
        } else {
            System.out.println("Pilihan tidak valid.");
        }

        if (currentUser != null) {
            currentUser.displayAppMenu(); // Polymorphic
        }

        scanner.close();
    }
}