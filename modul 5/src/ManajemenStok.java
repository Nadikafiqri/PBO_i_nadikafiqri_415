import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ManajemenStok {
    public static void main(String[] args) {
        ArrayList<Barang> daftarBarang = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        daftarBarang.add(new Barang("Buku", 10));
        daftarBarang.add(new Barang("Pulpen", 20));
        daftarBarang.add(new Barang("Penghapus", 15));

        int pilihan = -1;

        while (pilihan != 0) {
            System.out.println("\nMenu:");
            System.out.println("1. Tambah Barang Baru");
            System.out.println("2. Tampilkan Semua Barang");
            System.out.println("3. Kurangi Stok Barang");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");

            try {
                pilihan = scanner.nextInt();
                scanner.nextLine(); // Buang newline

                switch (pilihan) {
                    case 1:
                        System.out.print("Masukkan nama barang: ");
                        String nama = scanner.nextLine();
                        System.out.print("Masukkan stok awal: ");
                        try {
                            int stok = scanner.nextInt();
                            scanner.nextLine();
                            daftarBarang.add(new Barang(nama, stok));
                            System.out.println("Barang berhasil ditambahkan.");
                        } catch (InputMismatchException e) {
                            System.out.println("Input stok harus berupa angka!");
                            scanner.nextLine();
                        }
                        break;

                    case 2:
                        if (daftarBarang.isEmpty()) {
                            System.out.println("Stok barang kosong.");
                        } else {
                            System.out.println("Daftar Barang:");
                            for (int i = 0; i < daftarBarang.size(); i++) {
                                Barang b = daftarBarang.get(i);
                                System.out.println(i + ". " + b.getNama() + " - Stok: " + b.getStok());
                            }
                        }
                        break;

                    case 3:
                        if (daftarBarang.isEmpty()) {
                            System.out.println("Tidak ada barang untuk dikurangi.");
                            break;
                        }
                        System.out.println("Pilih barang yang akan dikurangi stoknya:");
                        for (int i = 0; i < daftarBarang.size(); i++) {
                            Barang b = daftarBarang.get(i);
                            System.out.println(i + ". " + b.getNama() + " - Stok: " + b.getStok());
                        }

                        try {
                            System.out.print("Masukkan indeks barang: ");
                            int index = scanner.nextInt();
                            scanner.nextLine();

                            Barang b = daftarBarang.get(index);

                            System.out.print("Masukkan jumlah stok yang akan dikurangi: ");
                            int jumlah = scanner.nextInt();
                            scanner.nextLine();

                            if (jumlah > b.getStok()) {
                                throw new StokTidakCukupException("Stok untuk " + b.getNama() + " hanya tersisa " + b.getStok());
                            }

                            b.setStok(b.getStok() - jumlah);
                            System.out.println("Stok berhasil dikurangi.");
                        } catch (InputMismatchException e) {
                            System.out.println("Input harus berupa angka!");
                            scanner.nextLine();
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Indeks tidak valid!");
                        } catch (StokTidakCukupException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case 0:
                        System.out.println("Terima kasih!");
                        break;

                    default:
                        System.out.println("Pilihan tidak tersedia.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input harus berupa angka!");
                scanner.nextLine();
            }
        }

        scanner.close();
    }
}