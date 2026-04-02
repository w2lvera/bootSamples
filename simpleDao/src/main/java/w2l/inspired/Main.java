package w2l.inspired;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import w2l.inspired.dao.CustomerDao;
import w2l.inspired.dao.CustomerSimpleDao;
import w2l.inspired.logical.CustomersChecker;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
                    SpringApplication.run(Main.class, args);

    }
}
