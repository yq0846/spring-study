package singleton;

import hello.core.beanfind.ApplicationContextExtendsFindTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatusfulServiceTest {

    @Test
    void statusfulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatusfulService statusfulService1 = ac.getBean(StatusfulService.class);
        StatusfulService statusfulService2 = ac.getBean(StatusfulService.class);

        //ThreadA: A사용자 10000원 주문
        statusfulService1.order("userA", 10000);
        //ThreadB: B사용자 20000원 주문
        statusfulService2.order("userB", 20000);

        //ThreadA: A사용자 주문 금액 조회
        int price = statusfulService1.getPrice();
        System.out.println("price = " + price);

        Assertions.assertThat(statusfulService1.getPrice()).isEqualTo(20000);
    }

    //해결법
    @Test
    void statusfulServiceSingleton2() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatusfulService statusfulService1 = ac.getBean(StatusfulService.class);
        StatusfulService statusfulService2 = ac.getBean(StatusfulService.class);

        //ThreadA: A사용자 10000원 주문
        int userAPrice = statusfulService1.order2("userA", 10000);
        //ThreadB: B사용자 20000원 주문
        int userBPrice = statusfulService2.order2("userB", 20000);

        //ThreadA: A사용자 주문 금액 조회
        System.out.println("price = " + userAPrice);
    }

    static class TestConfig {

        @Bean
        public StatusfulService statusfulService() {
            return new StatusfulService();
        }
    }

}