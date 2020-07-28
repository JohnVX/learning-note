package spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

@EnableAspectJAutoProxy
@Service
public class TestAop {
    void test(){
        System.out.println("test");
    }

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        TestAop testAop = ctx.getBean(TestAop.class);
        testAop.test();
    }
}
