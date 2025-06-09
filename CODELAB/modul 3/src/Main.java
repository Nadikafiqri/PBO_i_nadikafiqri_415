// Superclass KarakterGame
class KarakterGame {
    private String nama;
    private int kesehatan;

    // Constructor
    public KarakterGame(String nama, int kesehatan) {
        this.nama = nama;
        this.kesehatan = kesehatan;
    }

    public String getNama() {
        return nama;
    }

    public int getKesehatan() {
        return kesehatan;
    }

    public void setKesehatan(int kesehatan) {
        this.kesehatan = kesehatan;
    }

    // Method serang (akan di-override)
    public void serang(KarakterGame target) {
        System.out.println(nama + " menyerang " + target.getNama());
    }
}

// Pahlawan
class Pahlawan extends KarakterGame {

    public Pahlawan(String nama, int kesehatan) {
        super(nama, kesehatan);
    }

    public void serang(KarakterGame target) {
        System.out.println(getNama() + " menyerang " + target.getNama() + " menggunakan pedang!");
        int kesehatanBaru = target.getKesehatan() - 20;
        target.setKesehatan(kesehatanBaru);
        System.out.println("Kesehatan " + target.getNama() + " sekarang: " + target.getKesehatan());
    }
}

// Musuh
class Musuh extends KarakterGame {

    public Musuh(String nama, int kesehatan) {
        super(nama, kesehatan);
    }

    public void serang(KarakterGame target) {
        System.out.println(getNama() + " menyerang " + target.getNama() + " menggunakan sihir!");
        int kesehatanBaru = target.getKesehatan() - 15;
        target.setKesehatan(kesehatanBaru);
        System.out.println("Kesehatan " + target.getNama() + " sekarang: " + target.getKesehatan());
    }
}

// Kelas Utama
public class Main {
    public static void main(String[] args) {
        // Membuat objek
        KarakterGame karakterUmum = new KarakterGame("Karakter Umum", 100);
        Pahlawan brimstone = new Pahlawan("Brimstone", 150);
        Musuh viper = new Musuh("Viper", 200);

        // Menampilkan status awal
        System.out.println("Status Awal:");
        System.out.println(brimstone.getNama() + " Kesehatan: " + brimstone.getKesehatan());
        System.out.println(viper.getNama() + " Kesehatan: " + viper.getKesehatan());
        System.out.println();

        // Pertarungan
        System.out.println("Brimstone menyerang Viper menggunakan Orbital Strike!");
        viper.setKesehatan(viper.getKesehatan() - 20);
        System.out.println("Kesehatan " + viper.getNama() + " sekarang: " + viper.getKesehatan());
        System.out.println();

        System.out.println("Viper menyerang Brimstone menggunakan Snake Bite!");
        brimstone.setKesehatan(brimstone.getKesehatan() - 15);
        System.out.println("Kesehatan " + brimstone.getNama() + " sekarang: " + brimstone.getKesehatan());
    }
}
