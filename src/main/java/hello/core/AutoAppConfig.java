package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackageClasses = AutoAppConfig.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)  //자동으로 스프링 빈으로 등록해준다. (@Configuration 붙은것은 제외 <- 기존 예제코드는 살리기 위해 필터)
public class AutoAppConfig {

    //@Bean(name = "memoryMemberRepository")
    //MemberRepository memberRepository() {
    //    return new MemoryMemberRepository();
    //} <- 스프링 빈 이름이 같으면 부트 실행 시 오류나서 튕김
}
