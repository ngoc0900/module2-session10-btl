package ra.book;

import ra.ctgy.Category;
import ra.iey.IEntity;
import ra.run.QLBook;
import ra.run.QLCategory;

import java.security.cert.CertificateRevokedException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

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
    public static Scanner scanner = new Scanner(System.in);
    private boolean checkCurrentId(String id) {
        for (Book book : QLBook.getInstance().getListBook()) {
            if (book.getId().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }
    private boolean checkCurrentTitle(String title) {
        for (Book book : QLBook.getInstance().getListBook()) {
            if (book.getTitle().equalsIgnoreCase(title)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void input(Scanner scanner, int index) {
        System.out.println("Sách thứ: "+ index);
        inputBookId();
        inputTitle();
        inputAuthor();
        inputNXB();
        inputNamXB();
        inpputMota();
        chonMaVaTHeLoai();
    }
    public void inputBookId(){
        boolean check = false;
        do {
            System.out.println("Nhập vào mã sách: ");
            id = scanner.nextLine();
            boolean result = Pattern.matches("(B.{3})", id);
            if (!checkCurrentId(id) && result) {
                check = true;
            } else {
                System.out.println("Định dạng không hợp lệ, vui lòng nhập lại.");
            }
        }while (!check);

    }
    public void inputTitle(){
        boolean check = false;
        do {
            System.out.println("Nhập vào tiêu đề sách: ");
            String newTitle = scanner.nextLine();
            boolean result = Pattern.matches("(.{6,50})", newTitle);
            if (result && !checkCurrentTitle(newTitle)) {
                check = true;
                title = newTitle;
            } else {
                System.err.println("Định dạng không hợp lệ, vui lòng nhập lại.");
            }

        } while (!check);
    }
    public void inputAuthor(){
        boolean check = false;
        do {
            System.out.println("Nhập vào tên tác giả: ");
            String newAuthor = scanner.nextLine();
            boolean result = Pattern.matches("(.{1,})", newAuthor);
            if (result) {
                check = true;
                author = newAuthor;
            } else {
                System.err.println("Không bỏ trống tên tác giả, vui lòng nhập lại");
            }
        } while (!check);
    }

    public void inputNXB(){
        boolean check = false;
        do {
            System.out.println("Nhập vào nhà xuất bản: ");
            String newPublisher = scanner.nextLine();
            boolean result = Pattern.matches("(.{1,})", newPublisher);
            if (result) {
                check = true;
                publisher = newPublisher;
            } else {
                System.err.println("Không bỏ trống nhà xuất bản, vui lòng nhập lại.");
            }
        } while (!check);

    }
    public void inputNamXB(){
        boolean check = false;
        do {
            try {
                System.out.println("Nhập vào năm xuất bản: ");
                year = Integer.parseInt(scanner.nextLine());
                LocalDate currentDate = LocalDate.now();    // lấy ra năm hiện tại
                int currentYear = currentDate.getYear();
                if (year >= 1970 && year <= currentYear) {
                    check = true;
                } else {
                    System.out.println("Sách có năm xuất bản từ 1970-nay, vui lòng nhập lại.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Năm xuất bản không hợp lệ, vui lòng nhập lại.");
            }

        } while (!check);
    }

    public void inpputMota(){
        boolean check = false;
        do {
            System.out.println("Nhập vào mô tả sách: ");
            String newDescription = scanner.nextLine();
            boolean result = Pattern.matches("(.{1,})", newDescription);
            if (result) {
                check = true;
                description = newDescription;
            } else {
                System.err.println("Không bỏ trống mô tả, vui lòng nhập lại.");
            }
        } while (!check);
    }

    public void chonMaVaTHeLoai(){
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

    public void editBoook(){
        inputTitle();
        inputAuthor();
        inputNXB();
        inputNamXB();
        inpputMota();
        chonMaVaTHeLoai();
    }
//    private static Category getCategoryById(List<Category> categories, int categoryId) {
//        for (Category category : categories) {
//            if (category.getId() == categoryId);
//            return category;
//        }
//        return null;
//    }

    @Override
    public void output() {
        System.out.printf("Mã sách: %s, Tiêu đề: %s, Tác giả: %s ",id,title,author);
        System.out.printf("NXB: %s, Năm xuất bản: %d, Mô tả sách: %s,",publisher,year,description);
        System.out.println(" Thể loại: "+ categoryId );   //

    }

}
