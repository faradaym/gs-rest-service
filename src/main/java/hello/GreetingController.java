package hello;

import java.util.concurrent.atomic.AtomicLong;

import aus.hack.duo.DuoVerify;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    DuoVerify dv = new DuoVerify();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }

    @RequestMapping("/enroll")
    public Boolean enroll(@RequestParam(value="name", defaultValue="World") String name) {
        dv.enroll(name);

        return new Boolean(true);
    }

    @RequestMapping("/login")
    public Boolean login(@RequestParam(value="name", defaultValue="World") String name) {
        dv.login(name);

        return new Boolean(true);
    }

}