package br.com.tardelli.buyproduct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BuyProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(BuyProductApplication.class, args);
    }

}
