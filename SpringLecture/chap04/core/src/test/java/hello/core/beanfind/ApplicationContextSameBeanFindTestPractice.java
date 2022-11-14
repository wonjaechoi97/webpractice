package hello.core.beanfind;

import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextSameBeanFindTestPractice
{
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

   @Test
   @DisplayName("타입으로 조회시, 같은 타입 상속받은 빈 객체가 여러 개이면 중복 예외 발생")
   void findBeanByCTypeDuplicate(){
       assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(MemberRepository.class));
   }

    @Test
    @DisplayName("같은 타입 빈 객체가 여러개이면 이름으로 호출하면 된다")
    void findBeanByName() {
        MemberRepository memberRepository = ac.getBean("memberRepository1", MemberRepository.class);

        assertThat(memberRepository).isInstanceOf(MemberRepository.class);
    }


    @Test
    @DisplayName("특정 타입 모두 조회")
    void findAllBeanByType(){
       Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
       for(String key: beansOfType.keySet()){
           System.out.println("key = "+key+" value = "+ beansOfType.get(key));
       }
        System.out.println("beansOfType = " + beansOfType);
       assertThat(beansOfType.size()).isEqualTo(2);
    }

    @Configuration
    static class SameBeanConfig {
        @Bean
        MemberRepository memberRepository1(){
            return new MemoryMemberRepository();
        }

        @Bean
        MemberRepository memberRepository2(){
            return new MemoryMemberRepository();
        }
    }
}
