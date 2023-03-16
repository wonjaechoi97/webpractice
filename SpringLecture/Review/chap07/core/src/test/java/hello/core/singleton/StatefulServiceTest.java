package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.*;


class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //threadA: A 사용자가 10000원 주문
        int priceA = statefulService1.order("userA", 10000);

        //threadA: B 사용자가 20000원 주문
        int priceB = statefulService2.order("userB", 20000);

        //threadA : 사용자 A가 주문 금액 조회
//        int price = statefulService1.getPrice(); //10000원이 나오길 기대한다.
        System.out.println("price = " + priceA);;
        assertThat(priceA).isEqualTo(10000);

//        assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig{
        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }
}