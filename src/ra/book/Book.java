package ra.book;

import ra.ctgy.Category;
import ra.iey.IEntity;
import ra.run.QLCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Character.getName;

public class Book implements IEntity {
    private String id;   //Mã sách (bắt đầu bằng “B”, độ dài 4 kí tự, duy nhất)
    private String title;    //Tiêu đề sách (từ 6-50 kí tự, duy nhất)
    private String author;      //Tên tác giả (không bỏ trống)
    private String publisher;      //Nhà xuất bản (không bỏ trống)
    private int year;          //Năm xuất bản (tối thiểu từ năm 1970 và không lớn hơn năm hiện tại)
    private String description;            //Mô tả sách (không bỏ trống)
    private int categoryId; //= Category.getId();

    public Book() {
    }

    public Book(String id, String title, String author, String publisher, int year, String description) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategoryId() {
        return categoryId;
    }

    List<Category> categories = new ArrayList<>();

    @Override
    public void input(Scanner scanner, int index) {
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("Sách thứ: "+ index);
        System.out.println("Nhập vào mã sách");
        id = scanner1.nextLine();
        System.out.println("Nhập vào tiêu đề sách");
        title = scanner1.nextLine();
        System.out.println("Nhập vào tên tác giả");
        author = scanner1.nextLine();
        System.out.println("Nhập vào nhà xuất bản");
        publisher = scanner1.nextLine();
        System.out.println("Nhập vào năm xuất bản");
        year = Integer.parseInt(scanner1.nextLine());
        System.out.println("Nhập vào mô tả sách");
        description = scanner1.nextLine();
        System.out.println("Chọn mã và thể loại sách");
        for (Category category : QLCategory.getInstance().getListCategory()) {
            System.out.println(category.getId()+ " " +category.getName());
        }
        boolean check = false;
        do {
            try {
                System.out.println("Nhập vào mã thể loại sách");
                categoryId = Integer.parseInt(scanner1.nextLine());
                boolean check1 = false;
                for (Category category : QLCategory.getInstance().getListCategory()) {
                    if (category.getId() == categoryId){
                        check1 = true;
                    }
                }
                if (check1){
                    check = true;
                } else {
                    System.out.println("Không tồn tại ID này");
                }
            }catch (Exception exception){
                System.out.println("Nhập vào số không phải chữ");
            }

        }while (!check);
    }

    public void editBoook(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập vào tiêu đề sách");
        title = scanner.nextLine();
        System.out.println("Nhập vào tên tác giả");
        author = scanner.nextLine();
        System.out.println("Nhập vào nhà xuất bản");
        publisher = scanner.nextLine();
        System.out.println("Nhập vào năm xuất bản");
        year = Integer.parseInt(scanner.nextLine());
        System.out.println("Nhập vào mô tả sách");
        description = scanner.nextLine();
        System.out.println("Chọn mã và thể loại sách");
        for (Category category : QLCategory.getInstance().getListCategory()) {
            System.out.println(category.getId()+ " " +category.getName());
        }
        boolean check = false;
        do {
            try {
                System.out.println("Nhập vào mã thể loại sách");
                categoryId = Integer.parseInt(scanner.nextLine());
                boolean check1 = false;
                for (Category category : QLCategory.getInstance().getListCategory()) {
                    if (category.getId() == categoryId){
                        check1 = true;
                    }
                }
                if (check1){
                    check = true;
                } else {
                    System.out.println("Không tồn tại ID này");
                }
            }catch (Exception exception){
                System.out.println("Nhập vào số không phải chữ");
            }

        }while (!check);
    }
    private static Category getCategoryById(List<Category> categories, int categoryId) {
        for (Category category : categories) {
            if (category.getId() == categoryId);
            return category;
        }
        return null;
    }

    @Override
    public void output() {
        System.out.printf("Mã sách: %s, Tiêu đề: %s, Tác giả: %s ",id,title,author);
        System.out.printf("NXB: %s, Năm xuất bản: %d, Mô tả sách: %s,",publisher,year,description);
        System.out.println(" Thể loại: "+ categoryId );   //

    }

}
