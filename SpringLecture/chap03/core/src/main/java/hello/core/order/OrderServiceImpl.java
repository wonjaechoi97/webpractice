package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final DiscountPolicy discountPolicy;
    private final MemberRepository memberRepository;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy ) {
        this.discountPolicy = discountPolicy;
        this.memberRepository = memberRepository;
    }

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
}
