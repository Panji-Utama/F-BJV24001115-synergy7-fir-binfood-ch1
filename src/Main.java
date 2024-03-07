import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    static int allQty = 0;
    static int allPrice = 0;

    // Throws InterruptedException supaya dapat menggunakan function TimeUnit.SECONDS.sleep
    public static void main(String[] args) throws InterruptedException {
        int jumlah;
        Food makanan;

        // Menggunakan arraylist supaya tidak perlu memikirkan jumlah pesanan
        ArrayList<Food> foodCart = new ArrayList<>();

        menuList();

        Scanner sc = new Scanner(System.in);
        System.out.print("Masukkan pilihan anda: ");
        int choice = sc.nextInt();
        System.out.println("\n");

        // Switch case untuk memilih menu/makanan
        while (choice != 0) {
            switch (choice) {
                case 1:
                    System.out.println("1. Lontong Kari\t\t | 18000");
                    System.out.print("Jumlah yang ingin dibeli: ");
                    jumlah = sc.nextInt();
                    if (jumlah <= 0) {
                        break;
                    } else {
                        makanan = new Food("Lontong Kari", 18000, jumlah, 18000 * jumlah);
                        System.out.printf("%s ditambahkan ke dalam keranjang sebanyak %d buah dengan satu porsi seharga %d dengan total %d", makanan.getName(), jumlah, makanan.getPrice(), makanan.getTotalPrice());
                        TimeUnit.SECONDS.sleep(1);
                        foodCart.add(makanan);
                    }
                    break;
                case 2:
                    System.out.println("2. Bakso\t\t\t | 12000");
                    System.out.print("Jumlah yang ingin dibeli: ");
                    jumlah = sc.nextInt();
                    if (jumlah <= 0) {
                        break;
                    } else {
                        makanan = new Food("Bakso", 12000, jumlah, 12000 * jumlah);
                        System.out.printf("%s ditambahkan ke dalam keranjang sebanyak %d buah dengan satu porsi seharga %d dengan total %d", makanan.getName(), jumlah, makanan.getPrice(), makanan.getTotalPrice());
                        TimeUnit.SECONDS.sleep(1);
                        foodCart.add(makanan);
                    }
                    break;
                case 3:
                    System.out.println("3. Nasi Goreng\t\t | 15000");
                    System.out.print("Jumlah yang ingin dibeli: ");
                    jumlah = sc.nextInt();
                    if (jumlah <= 0) {
                        break;
                    } else {
                        makanan = new Food("Nasi Goreng", 15000, jumlah, 15000 * jumlah);
                        System.out.printf("%s ditambahkan ke dalam keranjang sebanyak %d buah dengan satu porsi seharga %d dengan total %d", makanan.getName(), jumlah, makanan.getPrice(), makanan.getTotalPrice());
                        TimeUnit.SECONDS.sleep(1);
                        foodCart.add(makanan);
                    }
                    break;
                case 4:
                    System.out.println("4. Indomie\t\t\t | 8000");
                    System.out.print("Jumlah yang ingin dibeli: ");
                    jumlah = sc.nextInt();
                    if (jumlah <= 0) {
                        break;
                    } else {
                        makanan = new Food("Indomie", 8000, jumlah, 8000 * jumlah);
                        System.out.printf("%s ditambahkan ke dalam keranjang sebanyak %d buah dengan satu porsi seharga %d dengan total %d", makanan.getName(), jumlah, makanan.getPrice(), makanan.getTotalPrice());
                        TimeUnit.SECONDS.sleep(1);
                        foodCart.add(makanan);
                    }
                    break;
                case 99:
                    if (foodCart.isEmpty()) {
                        System.out.println("Tidak ada makanan yang ditambahkan!");
                        TimeUnit.SECONDS.sleep(1);
                    } else {
                        checkout(foodCart);
                    }
                    break;
                default:
                    System.out.println("Pilihan yang anda masukkan salah!");
                    break;
            }
            menuList();
            System.out.print("Masukkan pilihan anda: ");
            choice = sc.nextInt();
            System.out.println("\n");
        }

        sc.close();

        System.out.println("Program selesai!");
    }

    // Method
    static void menuList() {
        System.out.println("\n\n================================================");
        System.out.println("Selamat datang di BinarFud cabang Firman Panji Utama");
        System.out.println("================================================\n");
        System.out.println("Silahkan pilih makanan:");
        System.out.println("1. Lontong Kari\t\t | 18000");
        System.out.println("2. Bakso\t\t\t | 12000");
        System.out.println("3. Nasi Goreng\t\t | 15000");
        System.out.println("4. Indomie\t\t\t | 8000");
        System.out.println("99. Checkout");
        System.out.println("0. Exit");
    }

    // Fitur Checkout atau Membayar Makanan
    static void checkout(ArrayList<Food> cart) {
        Scanner sc = new Scanner(System.in);
        int checkoutChoice;

        System.out.println("================================");
        System.out.println("Konfirmasi & Pembayaran");
        System.out.println("================================\n");

        for (Food f : cart) {
            allQty += f.getQty();
            allPrice += f.getTotalPrice();
            System.out.printf("%-15s %-10d %-10d\n", f.getName(), f.getQty(), f.getTotalPrice());
        }
        System.out.println("-------------------------------- +");
        System.out.printf("%-15s %-10d %-10d\n", "Total", allQty, allPrice);

        System.out.println("1. Konfirmasi dan bayar");
        System.out.println("2. Kembali ke menu utama");
        System.out.println("0. Keluar aplikasi");
        System.out.print("Silahkan memilih: ");
        checkoutChoice = sc.nextInt();

        switch (checkoutChoice) {
            case 1:
                // Membuat receipt dengan format txt
                writeToFile(cart);
                break;
            case 2:
                break;
            case 0:
                System.out.println("Akan keluar aplikasi!");
                // Stop program
                System.exit(0);
                break;
            default:
                System.out.println("Pilihan yang anda masukkan salah!");
                break;
        }
    }

    static void writeToFile(ArrayList<Food> cart) {
        try {
            FileWriter fileWriter = new FileWriter("receipt.txt");
            fileWriter.write("""
                    ================================================
                    PanjiFud
                    ================================================
                                        
                    Terima kasih sudah memesan. Berikut adalah pesanan anda:
                    """);
            for (Food f : cart) {
                fileWriter.write(String.format("%-15s %-10d %-10d\n", f.getName(), f.getQty(), f.getPrice()));
            }
            fileWriter.write("-------------------------------- +\n");
            fileWriter.write((String.format("%-15s %-10d %-10d\n\n", "Total", allQty, allPrice)));
            fileWriter.write("""
                    Pembayaran : Gadai STNK Motor
                                        
                    ================================================================
                    Simpan struk ini sebagai bukti gadai sah
                    ================================================================
                    """);
            fileWriter.close();
            System.out.println("Struk pembelian sudah tercetak, dan anda akan dikeluarkan dari program ini...");
            TimeUnit.SECONDS.sleep(3);
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}