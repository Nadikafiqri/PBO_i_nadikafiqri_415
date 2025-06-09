import java.util.Scanner;


class User {
    private String nama;
    private String nim;

    public User(String nama, String nim) {
        this.nama = nama;
        this.nim = nim;
    }


    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }


    public boolean login(String input1, String input2) {
        return false;
    }

    public void displayInfo() {
        System.out.println("User login berhasil.");
    }
}


class Admin extends User {
    private String username;
    private String password;

    public Admin(String nama, String nim, String username, String password) {
        super(nama, nim);
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    @Override
    public void displayInfo() {
        System.out.println("Login Admin berhasil. Selamat datang, " + getNama() + " (NIM: " + getNim() + ")");
    }
}


class Mahasiswa extends User {
    public Mahasiswa(String nama, String nim) {
        super(nama, nim);
    }

    @Override
    public boolean login(String inputNama, String inputNim) {
        return getNama().equalsIgnoreCase(inputNama) && getNim().equals(inputNim);
    }

    @Override
    public void displayInfo() {
        System.out.println("Login Mahasiswa berhasil. Selamat datang, " + getNama() + " (NIM: " + getNim() + ")");
    }
}


public class LoginSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Admin admin = new Admin("dika", "415", "admin123", "password123");
        Mahasiswa mhs = new Mahasiswa("nadika", "202410370110415");

        System.out.println("Login sebagai:");
        System.out.println("1. Admin");
        System.out.println("2. Mahasiswa");
        System.out.print("Masukkan pilihan (1/2): ");
        int pilihan = scanner.nextInt();
        scanner.nextLine();

        boolean loginBerhasil = false;

        if (pilihan == 1) {
            System.out.print("Username: ");
            String user = scanner.nextLine();
            System.out.print("Password: ");
            String pass = scanner.nextLine();

            loginBerhasil = admin.login(user, pass);
            if (loginBerhasil) {
                admin.displayInfo();
            } else {
                System.out.println("Login Admin gagal!");
            }

        } else if (pilihan == 2) {
            System.out.print("Nama: ");
            String nama = scanner.nextLine();
            System.out.print("NIM: ");
            String nim = scanner.nextLine();

            loginBerhasil = mhs.login(nama, nim);
            if (loginBerhasil) {
                mhs.displayInfo();
            } else {
                System.out.println("Login Mahasiswa gagal!");
            }

        } else {
            System.out.println("Pilihan tidak valid.");
        }

        scanner.close();
    }
}
