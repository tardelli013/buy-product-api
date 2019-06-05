package br.com.tardelli.buyproduct.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class TestServiceRest {

    @GetMapping("/people")
    public List<String> listPeople() {
        return Arrays.asList("tardelli","aguiar","moura");
    }

}
