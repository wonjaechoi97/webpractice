package hello.core.singleton;

public class SingletonService {
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance(){
        return instance;
    }


    private SingletonService(){ //생성자를 private으로 만들어서 밖에서 임의로 생성하지 못 하도록!

    }

    public void logic() {
        System.out.println("싱글톤 객체 로직을 홀출");
    }


}
