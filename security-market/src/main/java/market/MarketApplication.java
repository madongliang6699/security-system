package market;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MarketApplication {
    public static void main(String[] args) {
        SpringApplication.run(MarketApplication.class, args);
        System.out.println("=========================================================");
        System.out.println("============ 启动 成功 ===================");
        System.out.println("=========================================================");
    }
    
}
