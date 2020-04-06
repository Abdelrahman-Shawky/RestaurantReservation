package GUI;

public class CustomerTable {

    private String name;
    private int tableNumber;
    private int numOfSeats;
    private int num1;
    private int num2;
    private int num3;
    private int num4;
    private int num5;
    private int num6;
    private int num7;
    private int[] num = new int[10];
    private double amountToBePaid;


    public CustomerTable() {
        this.name = "";
        this.tableNumber = 0;
        this.numOfSeats = 0;
        this.num1 = 0;
        this.num2 = 0;
        this.num3 = 0;
        this.num4 = 0;
        this.num5 = 0;
        this.num6 = 0;
        this.num7 = 0;
        this.amountToBePaid = 0;
    }

    public CustomerTable(String name, int tableNumber,int numOfSeats, double amountToBePaid) {
        this.name = name;
        this.tableNumber = tableNumber;
        this.numOfSeats = numOfSeats;
        this.amountToBePaid = amountToBePaid;
        this.num1 = 0;
        this.num2 = 0;
        this.num3 = 0;
        this.num4 = 0;
        this.num5 = 0;
        this.num6 = 0;
        this.num7 = 0;

    }

    public String getName() {
        return name;
    }

    public void setNum(int[] num) {
        this.num = num;
        num1=num[0];
        num2=num[1];
        num3=num[2];
        num4=num[3];
        num5=num[4];
        num6=num[5];
        num7=num[6];
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public int getNum1() {
        return num1;
    }

    public int getNum2() {
        return num2;
    }

    public int getNum3() {
        return num3;
    }

    public int getNum4() {
        return num4;
    }

    public int getNum5() {
        return num5;
    }

    public int getNum6() {
        return num6;
    }

    public int getNum7() {
        return num7;
    }

    public double getAmountToBePaid() {
        return amountToBePaid;
    }

    public int getNumOfSeats() {
        return numOfSeats;
    }

    public void setNumOfSeats(int numOfSeats) {
        this.numOfSeats = numOfSeats;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public void setNum1(int num1) {
        this.num1 = num1;
    }

    public void setNum2(int num2) {
        this.num2 = num2;
    }

    public void setNum3(int num3) {
        this.num3 = num3;
    }

    public void setNum4(int num4) {
        this.num4 = num4;
    }

    public void setNum5(int num5) {
        this.num5 = num5;
    }

    public void setNum6(int num6) {
        this.num6 = num6;
    }

    public void setNum7(int num7) {
        this.num7 = num7;
    }

    public void setAmountToBePaid(double amountToBePaid) {
        this.amountToBePaid = amountToBePaid;
    }
}
