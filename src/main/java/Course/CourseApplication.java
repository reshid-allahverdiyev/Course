package Course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CourseApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourseApplication.class, args);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception ex) {

		}
	}

}
