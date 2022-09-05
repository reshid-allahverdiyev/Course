package Course.schedule;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class CustomSchedule {

    @Scheduled(fixedDelay = 1000)
    void task(){
        System.out.println("test");
    }

}
