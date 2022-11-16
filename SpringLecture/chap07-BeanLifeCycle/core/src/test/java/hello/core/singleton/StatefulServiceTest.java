package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {


    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac= new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //Thread A: A사용자가 10000원 주문문
//       statefulService1.order("userA", 10000);
        int userAPrice = statefulService1.order("userA", 10000);

       //Thread B : B 사용자가 20000원을 주문
//        statefulService2.order("userB", 20000); //같은 인스턴스 사용하기 때문에 10000->20000
        int userBPrice = statefulService2.order("userB", 20000);

        //Thread A: 사용자A가 주문 금액 조회
//        int price = statefulService1.getPrice();
//        System.out.println("price = " + price); //기대한 건 10000원 나온건 20000

        System.out.println("userAPrice = " + userAPrice);

//        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig{
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}