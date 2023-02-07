package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor // final 붙은 멤버 주입하는 생성자 만들어줌
public class OrderServiceImpl implements OrderService{

    //private final MemberRepository memberRepository= new MemoryMemberRepository();
    //    private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); // FixDiscountPolicy -> RateDiscountPolicy
    /*
    OrderServiceImpl이 구체 클래스에도 의존하고 있다.DIP 위반
    할인 정책을 바꾸는 순간 클라이언트 코드가 **변경**되므로 OCP위반

     */
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy; // 인터페이스에만 의존한다.

    //setter 의존 관계 주입은 빈 생성 후 의존관계 주입 단계에서 주입된다.
    /*@Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        System.out.println("discountPolicy = " + discountPolicy);
        this.discountPolicy = discountPolicy;
    }

    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        System.out.println("memberRepository = " + memberRepository);
        this.memberRepository = memberRepository;
    }*/


    //스프링이 빈 생성 시 의존관계 주입이 필요하면 그때 같이 주입한다.
    //@Autowired //생성자가 딱 1개 있을 때는 !!생략 가능
    /*public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        System.out.println("1. OrderServiceImpl.OrderServiceImpl");
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }*/ // <--------롬복으로 대체
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        //단일 책임 원칙이 잘 지켜져서 할인 정책이 변경되어도 Order 서비스는 건들 필요가 없다.

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
