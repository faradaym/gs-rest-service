package aus.hack.duo.auth;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import aus.hack.duo.DuoVerify;



    @RestController
    public class AuthenticationController {

        DuoVerify dv = new DuoVerify();

        @RequestMapping("/enroll")
        public Boolean enroll(@RequestParam(value="name", defaultValue="World") String name) {
            return dv.enroll(name);
        }

        @RequestMapping("/login")
        public Boolean login(@RequestParam(value="name", defaultValue="World") String name, @RequestParam(value="capability", defaultValue="push") String capability) {
            return dv.login(name, capability);
        }

    }
