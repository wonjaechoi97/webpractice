package hello.core.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SingletonService {
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance() {
        return instance;
    }

    private SingletonService(){ //외부에서 호출 불가, 애초에 생성하지 못하도록 해서 컴파일 오류 발생하도록
    }

    public void logic(){
        System.out.println("싱글톤 로직을 호출했다.");
    }



}
