package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    //private final MemberRepository memberRepository= new MemoryMemberRepository();
    //    private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); // FixDiscountPolicy -> RateDiscountPolicy
    /*
    OrderServiceImpl이 구체 클래스에도 의존하고 있다.DIP 위반
    할인 정책을 바꾸는 순간 클라이언트 코드가 **변경**되므로 OCP위반

     */
    private final MemberRepository memberRepository;
    private DiscountPolicy discountPolicy; // 인터페이스에만 의존한다.

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        //단일 책임 원칙이 잘 지켜져서 할인 정책이 변경되어도 Order 서비스는 건들 필요가 없다.

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
