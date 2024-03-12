package ra.run;

import ra.book.Book;
import ra.ctgy.Category;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;


import static java.util.Collections.sort;
import static ra.ctgy.Category.categories;

public class QLCategory {
    private static QLCategory instance;
    private static ArrayList<Category> categories;

    private QLCategory() {
        categories = new ArrayList<Category>();
    }

    public static QLCategory getInstance() {
        if (instance == null) {
            instance = new QLCategory();
        }

        return instance;
    }

    // vào book để trỏ vào category
    public ArrayList<Category> getListCategory() {
        return categories;
    }

    private static java.lang.String[] String;

    public void addCategory() {
        Scanner scanner = new Scanner(System.in);
//        List<Category> categories = new ArrayList<>();
        do {
            System.out.println("========= QUẢN LÝ THỂ LOẠI ========= \n" +
                    "1.\tThêm mới thể loại \n" +
                    "2.\tHiển thị danh sách theo tên A–Z \n" +
                    "3.\tThống kê thể loại và số sách có trong mỗi thể loại \n" +
                    "4.\tCập nhật thể loại \n" +
                    "5.\tXóa thể loại \n" +
                    "6.\tQuay lại \n");
            System.out.println("Mời bạn chọn: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    System.out.println("Số thể loại sách mà bạn muốn nhập ");
                    int n = Integer.parseInt(scanner.nextLine());
                    for (int i = 0; i < n; i++) {
                        Category category = new Category();
                        category.input(scanner, i + 1);
                        categories.add(category);
                    }
                    break;
                case 2:
                    System.out.println("Hiển thị danh sách theo tên A–Z");
                    categories.sort(Comparator.comparing(Category::getName));   //lambda của sắp xếp
                    for (Category category : categories) {
                        category.output();
                    }
                    break;
                case 3:
                    System.out.println("Thống kê thể loại và số sách có trong mỗi thể loại");
                    // kt xem categoryid của book có trùng vs id của category k
                    for (Category category : categories) {
                        int sum = 0;
                        for (Book book : QLBook.getInstance().getListBook()) {
                            if (category.getId() == book.getCategoryId()) {
                                sum++;
                            }
                        }
                        System.out.printf("Mã thể loại: %d, Tên thể loại: %s, Số sách: %d \n", category.getId(), category.getName(), sum);
                    }
                    break;
                case 4:
                    System.out.println("Cập nhật thể loại");
                    System.out.println("Nhập vào mã thể loại muốn thay đổi");
                    int id = Integer.parseInt(scanner.nextLine());
                    Category category = getCategoryByID(categories, id);
                    if (category != null) {
                        category.output();
                        System.out.println("bạn có muốn sửa lại thông tin k? (c/k)");
                        String choice1 = scanner.nextLine();
                        if (choice1.equalsIgnoreCase("c")) {
                            category.editCategory();
                            System.out.println("đã cập nhật thành công");
                            break;
                        } else {
                            System.out.println("huỷ cập nhật thông tin");
                        }
                    } else {
                        System.out.println("Không tồn tại mã thể loại có ID " + id);
                    }
                    break;

                case 5:
                    System.out.println("Xoá thể loại");
                    System.out.println("Nhập vào mã thể loại muốn xoá");
                    int id1 = Integer.parseInt(scanner.nextLine());
                    Category category1 = getCategoryByID(categories, id1);
                    if (category1 != null) {
                        category1.output();
                        System.out.println("bạn có muốn xoá thông tin k? (c/k)");
                        String choice1 = scanner.nextLine();
                        if (choice1.equalsIgnoreCase("c")) {
                            Book book = getBookByCategoryID(id1);
                            if (book == null) {
                                categories.remove(category1);
                                System.out.println("đã xóa xong");
                            } else {
                                System.out.println("thể loại đang có sách");
                            }
                        } else {
                            System.out.println("huỷ xoá thông tin");
                        }
                    } else {
                        System.out.println("Không tồn tại mã thể loại có ID " + id1);
                    }
                    break;
                case 6:
                    Library.qlMain();
                    break;
                default:
                    System.out.println("Mời chọn lại từ 1-6");
            }

        } while (true);

    }

    public static Category getCategoryByID(List<Category> categories, int id) {
        for (Category category : categories) {
            if (category.getId() == id) {
                return category;
            }
        }
        return null;
    }

    public static Book getBookByCategoryID(int id) {
        int i = 0;
        for (Book book : QLBook.getInstance().getListBook()) {
            if (book.getCategoryId() == id) {
                i++;
                return book;
            }
        }
        return null;
    }

//    public static void sapxep(){
//        categories.sort(new Comparator<Category>() {
//            @Override
//            public int compare(Category o1, Category o2) {
//                return o1.getName().compareTo(o2.getName());
//            }
//        });
//    }
}
