package com.easybites.springsecuritybasic.controller;

import com.easybites.springsecuritybasic.model.Customer;
import com.easybites.springsecuritybasic.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    /**
     * 회원가입을 할때 로직을 추가하는 것이다.
     */

    @Autowired
    CustomerRepository customerRepository;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Customer customer) {

        Customer savedCustromer = null;
        ResponseEntity<String> response = null;
        try {
            savedCustromer = customerRepository.save(customer);
            if (savedCustromer.getId() > 0) {
                response = ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body("회원가입 완료");
            }
        } catch (Exception e) {
            response = ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("회원가입이 되지 않았습니다. "+e.getMessage());
        }
        return response;
    }

}
