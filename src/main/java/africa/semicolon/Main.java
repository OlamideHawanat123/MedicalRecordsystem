package africa.semicolon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Scanner;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "africa.semicolon.data.repositories")

public class Main {
    public static void main(String[] args) {

        SpringApplication.run(Main.class, args);

    }
}