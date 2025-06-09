package app;

import perpustakaan.*;

public class Main {
    public static void main(String[] args) {
        // Buku
        Buku b1 = new NonFiksi("Madilog", "Tan Malaka", "Sejarah & Ilmu Pengetahuan");
        Buku b2 = new Fiksi("Hainuwele: Sang Putri Kelapa", "Lilis Hu", "Dongeng");

        b1.displayInfo();
        b2.displayInfo();

        // Anggota
        Anggota a1 = new Anggota("dika", "415");
        Anggota a2 = new Anggota("bowo", "490");
        System.out.println();

        a1.tampilkanInfo();
        a2.tampilkanInfo();
        System.out.println();

        // Peminjaman
        a1.pinjamBuku("Madilog");
        a2.pinjamBuku("Hainuwele: Sang Putri Kelapa", 7);
        System.out.println();

        // Pengembalian
        a1.kembalikanBuku("Madilog");
        a2.kembalikanBuku("Hainuwele: Sang Putri Kelapa");
    }
}
