package tobyspring.hellospring;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Client {
	public static void main(String[] args) throws IOException, InterruptedException {
		BeanFactory beanFactory = new AnnotationConfigApplicationContext(ObjectFactory.class);
		PaymentService paymentService = beanFactory.getBean(PaymentService.class);

		ObjectFactory o = beanFactory.getBean(ObjectFactory.class);
		PaymentService p1 = o.paymentService();
		PaymentService p2 = o.paymentService();
		System.out.println(p1 == p2);

		Payment payment1 = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
		System.out.println(payment1);

		TimeUnit.SECONDS.sleep(1);

		Payment payment2 = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
		System.out.println(payment2);

		TimeUnit.SECONDS.sleep(3);

		Payment payment3 = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
		System.out.println(payment3);
	}
}
