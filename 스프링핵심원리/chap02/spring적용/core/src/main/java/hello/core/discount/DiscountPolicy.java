package hello.core.discount;

import hello.core.member.Member;

public interface DiscountPolicy {
    //return이 할인 대상 금액
    int discount(Member member, int price); // f2누르면 오류난 곳으로

}
