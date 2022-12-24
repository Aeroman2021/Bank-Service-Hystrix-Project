package ir.tamin.bankservice.controller;

import ir.tamin.bankservice.service.AccDelegateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bank")
public class BankService {

    @Autowired
    AccDelegateService accDelegateService;


    @GetMapping("/{bankName}")
    public String getAccData(@PathVariable String bankName){
        System.out.println("Loading Account Data");
        return accDelegateService.callAccService(bankName);
    }
}
