package ra.ctgy;

import ra.iey.IEntity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

// Thể loại sách

public class Category implements IEntity {
    private  int id;   // Mã thể loại (số nguyên lớn hơn 0, duy nhất)
    private  String name;   //Tên thể loại (không trùng nhau, từ 6-30 kí tự)
    private boolean status;     //Trạng thái thể loại (chỉ nhận true/false khi nhập)

    public Category() {
    }

    public Category(int id, String name, boolean status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public   int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public  String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public static final  List<Category> categories = new ArrayList<>();
    @Override
    public void input(Scanner scanner,int index) {
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("Thể loại sách thứ: "+ index);
        System.out.println("Nhập vào mã thể loại");
        id = Integer.parseInt(scanner1.nextLine());
        System.out.println("Nhập vào tên thể loại");
        name = scanner1.nextLine();
        System.out.println("Nhập vào trạng thái thể loại, nhập vào true/false");
        status = scanner1.nextBoolean();

    }
    public void editCategory(){
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("Nhập vào tên thể loại");
        name = scanner1.nextLine();
        System.out.println("Nhập vào trạng thái thể loại, nhập vào true/false");
        status = scanner1.nextBoolean();
    }

    @Override
    public void output() {
        System.out.printf("Mã thể loại: %d, Tên thể loại: %s ",id,name);
        System.out.printf(" Trạng thái: %s \n",(status ? "Hoạt động" :"Không hoạt động "));

    }




}
