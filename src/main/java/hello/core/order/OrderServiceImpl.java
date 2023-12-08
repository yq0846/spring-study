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
@RequiredArgsConstructor    //final 붙은 필드를 모아서 생성자를 자동으로 만든다.
public class OrderServiceImpl implements OrderService {

    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    //@Autowired private MemberRepository memberRepository;
    //@Autowired private DiscountPolicy discountPolicy; <- 의존관계 필드 주입 테스트 (권장하지 않음)

    //@Autowired
    //public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
    //    System.out.println("memberRepository = " + memberRepository);
    //    System.out.println("discountPolicy = " + discountPolicy);
    //    this.memberRepository = memberRepository;
    //    this.discountPolicy = discountPolicy;
    //}

    //@Autowired
    //public void setMemberRepository(MemberRepository memberRepository) {
    //    this.memberRepository = memberRepository;
    //}

    //@Autowired
    //public void setDiscountPolicy(DiscountPolicy discountPolicy) {
    //    this.discountPolicy = discountPolicy;
    //} <- 수정자 주입 테스트

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //싱글톤 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
