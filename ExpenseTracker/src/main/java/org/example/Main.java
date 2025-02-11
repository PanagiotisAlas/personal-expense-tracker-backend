package org.example;

import io.github.cdimascio.dotenv.Dotenv;
import org.example.model.Expense;
import org.example.utils.ExpenseDataLoader;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Main implements CommandLineRunner {
    public static void main(String[] args) {

        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
        System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));


        SpringApplication.run(Main.class,args);

    }

    @Override
    public void run(String... args) throws Exception {
        List<Expense> expenseList = ExpenseDataLoader.getExpenses();
        expenseList.forEach(System.out::println);
    }
}