package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{

//    @Autowired 필드 주입 좋은 방식이 아니니 사용 지양하자
    //순수 자바코드로 이 클래스 객체를 생성하면 discountPolicy 에 null값들어 있어서 사용 불가하므로 불가피하게 setter를 만들어줘야 한다.
    private final DiscountPolicy discountPolicy;
    private final MemberRepository memberRepository;

    /*
    final을 넣어주면 생성자할 때 아규먼트 넣어주지 않은 것을 컴파일 단계에서 확인 가능
     */


    //위의 final없애면 setMember...등으로 수정자 의존 주입 가능
    //선택적으로 의존 관계 주입할 때는 set으로 하는 게 좋다 예)  memberRepository가 스프링 컨테이너에 없을 경우
    //의존 주입 없어도 동작하게 하려면 @Autowired(required = false)필요

//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;
//    }
//
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
    /*
    이렇게 수정자 di를하면 test코드 작성 시 그냥 실행은 되는데 NullPointerException 발생
    생성자로 하면 컴파일 오류가 발생하므로 부족한 것이 무엇인지 한눈에 파악가능!
     */


        @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy ) {
        this.discountPolicy = discountPolicy;
        this.memberRepository = memberRepository;
    }

    //일반 메서드 의존 주입 잘 사용하지 않음
//    @Autowired
//    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;
//        this.memberRepository = memberRepository;
//    }

    /*
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); //할인 정책을 변경하려면 클라이언트인 orderserviceimpl코드를 변경해야 한다!
    //인터페이스 뿐만 아니라 구체화한 클래스도 의존하고 있다.
    //OderServiceImpl이 discount 정책까지 결정하므로 책임이 커진다 단일 책임 원칙 위배
    구현 객체를 생성하고 연결을 담당하는 별도의 설정 클래스 필요!!
    */
    //인터페이스만 의존 그냥 실행하면 NullPointException 발생
    //누군가 대신 생성해서 주입해주어야 함 : 스프링!!

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        //할인과 관련된 정책을 바꾸어도 OrderService는 수정할 필요 없이 discountpolicy만 변경하면 되므로 단일 책임 원칙 잘 준수

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트용용
   public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
