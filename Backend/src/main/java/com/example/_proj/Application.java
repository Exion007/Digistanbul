package com.example._proj;

//import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class Application {

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner runner(UserRepository userRepo, MongoTemplate mongoTemplate){
		List<Comment> comments2 = new ArrayList<Comment>();
		List<Comment> comments3 = new ArrayList<Comment>();
		List<Comment> comments4 = new ArrayList<Comment>();
		List<Comment> comments5 = new ArrayList<Comment>();
		return args -> {

			String email = "baturkarakaya@sabanciuniv.edu";

			User user1 = new User(
					"batur",
					email,
					"batur!123",
					"+905063634407"
			);

			User user2 = new User(
					"demirhan",
					"demirhanizer@sabanciuniv.edu",
					"demo.123",
					"+905379585147"
			);

			User user3 = new User(
					"mert",
					"mertcoskuner@sabanciuniv.edu",
					"mert,123",
					"+905353795866"
			);
		};
	}
}