class Hewan {
    String nama;
    String jenis;
    String suara;

    public Hewan(String nama, String jenis, String suara) {
        this.nama = nama;
        this.jenis = jenis;
        this.suara = suara;
    }

    public void tampilkanInfo() {
        System.out.println("Nama: " + nama);
        System.out.println("Jenis: " + jenis);
        System.out.println("Suara: " + suara);
        System.out.println();
    }
}

public class Main {
    public static void main(String[] args) {
        Hewan hewan1 = new Hewan("Kucing", "Mamalia", "Meow");
        Hewan hewan2 = new Hewan("Anjing", "Mamalia", "woof-woof");

        hewan1.tampilkanInfo();
        hewan2.tampilkanInfo();
    }
}
