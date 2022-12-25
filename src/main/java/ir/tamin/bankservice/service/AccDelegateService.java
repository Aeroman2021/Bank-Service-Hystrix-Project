package ir.tamin.bankservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@Service
//@EnableHystrix
public class AccDelegateService {

    @Autowired
    RestTemplate restTemplate;


    @HystrixCommand(fallbackMethod = "callAccount_FallBack", commandKey = "doSomethingKey")
    public String callAccService(String bankName){
        String response = restTemplate.exchange("http://localhost:8080/api/v1/accounts/fetchAccOfTheGivenBank/{bankName}",
                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
                }, bankName).getBody();
        System.out.println("response received as " + response + ": " + new Date());
        return "NORMAL FLOW !!! - Bank -  " + bankName + " :::  included accounts " + response + " :  " + new Date();
    }


    @SuppressWarnings("unused")
    private String callAccount_FallBack(String bankName){
        System.out.println("Account Service is down!!! fallback method activated...");
        return "Circuit Breaker Enabled: " + new Date();

    }


    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
