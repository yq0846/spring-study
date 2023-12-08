package singleton;

import java.sql.SQLOutput;

public class StatusfulService {

    private int price;  //상태를 유지하는 필드

    public void order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        this.price = price; //여기가 문제
    }

    public int getPrice() {
        return price;
    }

    //해결법
    public int order2(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        return price;
    }
}
