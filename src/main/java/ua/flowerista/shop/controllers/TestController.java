package ua.flowerista.shop.controllers;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/test")
@CrossOrigin(origins = "*")
public class TestController {
	
	@GetMapping
    @ResponseBody
    public String helloWorld() {
        return "{\"name\":\"Hello, World!\"}";
    }

}
