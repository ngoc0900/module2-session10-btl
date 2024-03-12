package ra.ctgy;

import ra.iey.IEntity;
import ra.run.QLCategory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

// Thể loại sách

public class Category implements IEntity {
    private int id;   // Mã thể loại (số nguyên lớn hơn 0, duy nhất)
    private String name;   //Tên thể loại (không trùng nhau, từ 6-30 kí tự)
    private boolean status;     //Trạng thái thể loại (chỉ nhận true/false khi nhập)

    public Category() {
    }

    public Category(int id, String name, boolean status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
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

    public static final List<Category> categories = new ArrayList<>();

    @Override
    public void input(Scanner scanner, int index) {
        System.out.println("Thể loại sách thứ: " + index);
        inputID();
        inputName();
        inputStatus();
    }

    private boolean checkCurrentId(int id) {
        for (Category category : QLCategory.getInstance().getListCategory()) {
            if (category.getId() == id) {
                return true;
            }
        }
        return false;
    }

    private boolean checkCurrentName(String name){
        for (Category category : QLCategory.getInstance().getListCategory()) {
            if (category.getName().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }

    public void inputID() {
        Scanner scanner = new Scanner(System.in);
        boolean check = false;
        do {
            try {
                System.out.println("Nhập vào mã thể loại (số nguyên lớn hơn 0, duy nhất)");
                id = Integer.parseInt(scanner.nextLine());
                if (id > 0 && !checkCurrentId(id)) {
                    check = true;
                } else {
                    System.out.println("Mã thể loại bị trùng, yêu cầu nhập lại");
                }
            } catch (NumberFormatException exception) {
                System.out.println("Định dạng không hợp lệ, vui lòng nhập lại");
            }
        } while (!check);
    }


    public void inputName() {
        Scanner scanner = new Scanner(System.in);
        boolean check = false;
        do {
                System.out.println("Nhập vào tên thể loại (không trùng nhau, từ 6-30 kí tự)");
                String newName = scanner.nextLine();
                if ((newName.length() >= 6) && !checkCurrentName(newName) && (newName.length() <= 30)) {
                    check = true;
                    name = newName;
                } else {
                    System.out.println("Định dạng không hợp lệ, vui lòng nhập lại");
                }

        } while (!check);
    }

    public void inputStatus() {
        Scanner scanner = new Scanner(System.in);
        boolean check = false;
        do {
            try {
                System.out.println("Nhập vào trạng thái thể loại, nhập vào true/false");
                status = Boolean.parseBoolean(scanner.nextLine());
                check = true;
            } catch (IllegalArgumentException exception) {
                System.out.println("Định dạng không hợp lệ, vui lòng nhập lại");
            }
        } while (!check);
    }

    public void editCategory() {
        inputName();
        inputStatus();
    }

    @Override
    public void output() {
        System.out.printf("Mã thể loại: %d, Tên thể loại: %s ", id, name);
        System.out.printf(" Trạng thái: %s \n", (status ? "Hoạt động" : "Không hoạt động "));

    }


}
