package ra.run;

import java.util.Scanner;

public class Library {
    private static java.lang.String[] String;

    public static void main(String[] args) {
       qlMain();
    }
    public static void qlMain(){
        QLCategory qlCategory = QLCategory.getInstance();
        QLBook qlBook = QLBook.getInstance();
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("========= QUẢN LÝ THƯ VIỆN ========= \n" +
                    "1.\tQuản lý Thể loại \n" +
                    "2.\tQuản lý Sách \n" +
                    "3.\tThoát \n");
            System.out.println("Mời bạn chọn: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice){
                case 1:
                    qlCategory.addCategory();
                    break;
                case 2:
                    qlBook.addBook();
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Mời chọn lại từ 1-3");
            }

        }while (true);

    }
}
