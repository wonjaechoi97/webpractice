package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class RateDiscountPracticePolicyTest {
    RateDiscountPolicy rateDiscountPolicy = new RateDiscountPolicy();

    @Test
    void VIP할인(){
        Member member = new Member(1L,"memberVIP", Grade.VIP);

        int discount = rateDiscountPolicy.discount(member, 10000);

        assertThat(discount).isEqualTo(1000);
    }

    @Test
    void Basic할인() {
        Member member = new Member(2L, "memberBASIC", Grade.BASIC);

        int discount = rateDiscountPolicy.discount(member, 20000);

        assertThat(discount).isNotEqualTo(2000);
    }
}
