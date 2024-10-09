package org.example.case_study_module_4.restful;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FollowRestfulController {
    @GetMapping("/follow")
    public String follow() {
        return "Hello World";
    }
}
