package ra.run;

import ra.book.Book;
import ra.ctgy.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QLBook {
    private static QLBook instance ;
    private static ArrayList<Book> books;
    private QLBook(){
        books = new ArrayList<Book>();
    }

    public static QLBook getInstance() {
        if(instance == null) {
            instance = new QLBook();
        }

        return instance;
    }
    public ArrayList<Book> getListBook() {
        return  books;
    }

    private static java.lang.String[] String;
    public  void addBook() {
        Scanner scanner = new Scanner(System.in);
//        List<Book> books = new ArrayList<>();
        do {
            System.out.println("========= QUẢN LÝ SÁCH ========= \n" +
                    "1.\tThêm mới sách \n" +
                    "2.\tCập nhật thông tin sách \n" +
                    "3.\tXóa sách \n" +
                    "4.\tTìm kiếm sách \n" +
                    "5.\tHiển thị danh sách sách, theo nhóm  thể loại \n" +
                    "6.\tQuay lại \n");
            System.out.println("Mời bạn chọn: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice){
                case 1:
                    System.out.println("Mời nhập số sách");
                    int bookNumber = Integer.parseInt(scanner.nextLine());
                    for (int i = 0; i < bookNumber; i++) {
                        Book book = new Book();
                        book.input(scanner,i+1);
                        books.add(book);
                        book.output();
                    }
                    break;
                case 2:
                    System.out.println("Hiển thị thông tin sách");
                    System.out.println("Cập nhật thông tin sách theo mã sách");
                    System.out.println("mời bạn nhập ID book ");
                    String id = scanner.nextLine();
                    Book book = getBookById(books,id);
                    if (book != null){
                        book.output();
                        System.out.println("bạn có muốn sửa lại thông tin k? (c/k)" );
                        String choice1 = scanner.nextLine();
                        if (choice1.equalsIgnoreCase("c")){
                            book.editBoook();
                            System.out.println("đã edit xong");
                            break;
                        } else {
                            System.out.println("huỷ sửa thông tin");
                        }
                    } else {
                        System.out.println("Không tồn tại cuốn sách có ID "+ id);
                    }
                    break;
                case 3:
                    System.out.println("Hiển thị thông tin sách");
                    System.out.println("Xoá sách theo mã sách");
                    System.out.println("mời bạn nhập ID book ");
                    String id1 = scanner.nextLine();
                    Book book1 = getBookById(books,id1);
                    if (book1 != null){
                        book1.output();
                        System.out.println("bạn có muốn xoá sách k? (c/k)" );
                        String choice1 = scanner.nextLine();
                        if (choice1.equalsIgnoreCase("c")){
                            books.remove(book1);
                            System.out.println("đã xoá xong");
                            break;
                        } else {
                            System.out.println("huỷ xoá sách");
                        }
                    } else {
                        System.out.println("Không tồn tại cuốn sách có ID "+ id1);
                    }
                    break;
                case 4:
                    System.out.println("Tìm kiếm sách theo tên tác giả");
                    System.out.println("Mời nhập tên tác giả");
                    String title = scanner.nextLine();
                    boolean checck = true;
                    for (Book b : books) {
                        if (b.getAuthor().toLowerCase().contains(title.toLowerCase())) {
                            b.output();
                            checck = false;
                        }
                    }
                    if (checck){
                        System.err.println("không tồn tại sách mà bạn mốn tìm.");
                    }
                    break;
                case 5:
                    System.out.println("Hiển thị danh sách sách, theo nhóm  thể loại");
                    for (Category category : QLCategory.getInstance().getListCategory()) {
                        System.out.println(category.getName());
                        Book book3 = getBookByIdCategory(books,category.getId());
                        if (book3 != null){
                            getBookByIdCategory(books,category.getId());
                            break;
                        }
                    }
                    break;

                case 6:
                    Library.qlMain();
                    break;
                default:
                    System.out.println("Mời chọn lại từ 1-6");
            }

        }while (true);
    }

    private static Book getBookById(List<Book> books, String id) {
        for (Book book : books) {
            if (book.getId().equalsIgnoreCase(id)){
                return book;
            }
        }
        return null;
    }

    private static Book getBookByTitle(List<Book> books, String title){
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                return book;
            }
        }
        return null;
    }

    private static Book getBookByIdCategory(List<Book> books, int id) {
        for (Book book : books) {
            if (book.getCategoryId() == id) {
                System.out.println("\tSách: "+book.getTitle());
            }
        }
        return null;
    }

}
