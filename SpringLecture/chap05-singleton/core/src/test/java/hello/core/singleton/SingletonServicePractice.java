package hello.core.singleton;

public class SingletonServicePractice {

    private static final SingletonServicePractice instance = new SingletonServicePractice();

    public static SingletonServicePractice getInstance(){
        return  instance;
    }

    //외부에서 호출 못하게 프라이빗 생성자 딱 하나만 !!
    private SingletonServicePractice(){

    }
    
    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }
}
