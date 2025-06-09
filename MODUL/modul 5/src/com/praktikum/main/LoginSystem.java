package com.praktikum.main;

import com.praktikum.users.*;
import com.praktikum.models.Item;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class LoginSystem {

    public static ArrayList<User> userList = new ArrayList<>();
    public static ArrayList<Item> reportedItems = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        userList.add(new Admin("Admin", "", "dika123", "password123"));
        userList.add(new Mahasiswa("nadika fiqri", "202410370110415"));


        while (true) {
            System.out.println("\nPilih login:");
            System.out.println("1. Admin");
            System.out.println("2. Mahasiswa");
            System.out.println("0. Keluar");
            System.out.print("Masukkan pilihan (0/1/2): ");

            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Input harus berupa angka!");
                scanner.nextLine();
                continue;
            }

            if (choice == 0) {
                System.out.println("Terima kasih telah menggunakan sistem.");
                break;
            }

            User currentUser = null;

            if (choice == 1) {
                System.out.print("Masukkan username: ");
                String username = scanner.nextLine();
                System.out.print("Masukkan password: ");
                String password = scanner.nextLine();

                for (User user : userList) {
                    if (user instanceof Admin admin && admin.login(username, password)) {
                        currentUser = admin;
                        break;
                    }
                }

                if (currentUser == null) {
                    System.out.println("Login Admin gagal!");
                }

            } else if (choice == 2) {
                System.out.print("Masukkan nama: ");
                String name = scanner.nextLine();
                System.out.print("Masukkan NIM: ");
                String nim = scanner.nextLine();

                for (User user : userList) {
                    if (user instanceof Mahasiswa mahasiswa && mahasiswa.login(name, nim)) {
                        currentUser = mahasiswa;
                        break;
                    }
                }

                if (currentUser == null) {
                    System.out.println("Login Mahasiswa gagal!");
                }

            } else {
                System.out.println("Pilihan tidak valid.");
            }

            if (currentUser != null) {
                currentUser.displayAppMenu();
            }
        }

        scanner.close();
    }
}