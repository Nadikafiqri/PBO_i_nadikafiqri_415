import java.util.Scanner;

public class LoginProgram {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String adminUsername = "admin";
        String adminPassword = "admin415";

        String mahasiswaNama = "nadika fiqri";
        String mahasiswaNIM = "202410370110415";

        System.out.println("=== Program Login ===");
        System.out.println("Pilih jenis login:");
        System.out.println("1. Admin");
        System.out.println("2. Mahasiswa");
        System.out.print("Masukkan pilihan (1/2): ");
        String pilihan = scanner.nextLine();

        if (pilihan.equals("1")) {
            System.out.print("Masukkan username: ");
            String inputUsername = scanner.nextLine();
            System.out.print("Masukkan password: ");
            String inputPassword = scanner.nextLine();

            if (inputUsername.equals(adminUsername) && inputPassword.equals(adminPassword)) {
                System.out.println("Login berhasil sebagai Admin!");
            } else {
                System.out.println("Login gagal! Username atau password salah.");
            }

        } else if (pilihan.equals("2")) {
            System.out.print("Masukkan nama: ");
            String inputNama = scanner.nextLine();
            System.out.print("Masukkan NIM: ");
            String inputNIM = scanner.nextLine();

            if (inputNama.equalsIgnoreCase(mahasiswaNama) && inputNIM.equals(mahasiswaNIM)) {
                System.out.println("Login berhasil sebagai Mahasiswa!");
                System.out.println("Informasi Mahasiswa:");
                System.out.println("Nama: " + mahasiswaNama);
                System.out.println("NIM : " + mahasiswaNIM);
            } else {
                System.out.println("Login gagal! Nama atau NIM salah.");
            }

        } else {
            System.out.println("Pilihan tidak valid. Silakan pilih 1 atau 2.");
        }

        scanner.close();
    }
}
