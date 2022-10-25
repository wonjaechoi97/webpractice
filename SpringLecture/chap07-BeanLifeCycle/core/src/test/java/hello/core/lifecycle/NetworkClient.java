package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient  {
    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
        /*connect();
        call("초기화 연결 메시지");*/
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작 시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message){
        System.out.println("call: " + url + "message = " + message);
    }

    //서비스 종료 시 호출(안전하게 연결이 끊어지도록)
    public void disconnect() {
        System.out.println("close: " + url);
    }


    @PostConstruct
    public void init() throws Exception { //의존관계 주입이 끝나면 호출할 것
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
        
    }

    @PreDestroy
    public void close() throws Exception { //빈 종료시 호출
        System.out.println("NetworkClient.close");
        disconnect();
    }
}
