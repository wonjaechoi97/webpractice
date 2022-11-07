package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

public class RateDiscountPracticePolicy implements DiscountPolicy {
    int discountRate = 10;
    //vip면 10%
    @Override
    public int discount(Member member, int price) {
        if(member.getGrade()== Grade.VIP){
            return price*discountRate/100;
        }else{
            return 0;
        }
    }
}
