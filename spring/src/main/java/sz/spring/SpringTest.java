package sz.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class SpringTest {
    String home()
    {
        return "Hello world!";
    }

    public static void main( String[] args) throws Exception
    {
        SpringApplication.run(SpringTest.class, args);
    }
}
