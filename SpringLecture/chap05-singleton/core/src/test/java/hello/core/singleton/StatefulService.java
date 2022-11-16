package hello.core.singleton;

public class StatefulService {
//    private int price; //상태 유지 필드


    public int order(String name, int price){ //주문 시 가격 저장하는 그런 것
        System.out.println("name = " + name +"price = "+price);
//        this.price = price; //여기가 문제!

        return price; //지역 변수 사용해서 공유 문제 해결

    }

    public int getPrice(){
//        return price;
        return 0;
    }
}
