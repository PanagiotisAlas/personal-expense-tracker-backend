package org.example.services;

import org.example.model.AppUser;
import org.example.model.Expense;
import org.example.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
//@Profile("json")
public class ExpenseServiceImpl implements ExpenseService{

    private final ExpenseRepository expenseRepository;
    private final UserService userService;

    //Thread safe
    //private static final AtomicLong idCounter = new AtomicLong();

    public ExpenseServiceImpl(ExpenseRepository expenseRepository, UserService userService) {
        this.expenseRepository = expenseRepository;
        this.userService = userService;
    }


    @Override
    public List<Expense> getAllUserExpenses(Long userId) {
        return new ArrayList<>(expenseRepository
                .findByUserIdOrderByDateDesc(userId));
    }

    @Override
    public List<Expense> getExpenseByDay(String date, Long userId) {
        return expenseRepository.findByUserIdOrderByDateDesc(userId)
                .stream().filter(expense -> expense.getDate()
                        .equals(date)).toList();


        //To load from json
//        return ExpenseDataLoader.getExpenses().stream().filter(
//                expense -> expense.getDate().equalsIgnoreCase(date)).toList();
                // A predicate is a functional interface in Java
        // that takes one argument and returns a boolean value
        // (true or false). It is often used to filter or evaluate
        // conditions, especially when working with streams in java
    }

    @Override
    public List<Expense> getExpenseByCategoryAndMonth(String category
            , String month, Long userId) {
        return expenseRepository.findByUserIdOrderByDateDesc(userId)
        //ExpenseDataLoader.getExpenses()
                .stream().filter(
                expense -> expense.getCategory()
                        .equalsIgnoreCase(category) && expense
                        .getDate().startsWith(month)
                ).toList();
    }

    @Override
    public List<String> getAllExpenseCategories(Long userId) {
       return expenseRepository.findByUserIdOrderByDateDesc(userId)
        // ExpenseDataLoader.getExpenses() // Gets all the expenses
                .stream() // This converts the list of expenses
                // (List<Expenses>) into a stream.
                // A stream is an abstraction introduced in Java 8
                // that allows you to perform a sequence
                //of operations (like filtering, mapping,
                // reducing, etc.) on elements of a collection.
                .map( // The map operation is used to transform
                        //elements in the stream. here,
                        //it takes each Expense object and
                        // transforms it by applying the
                        // getCategory() method to each one.
                        Expense::getCategory)
                // Expense::getCategory is a method reference,
                // which is shorthand for (expense) -> expense.getCategory
                .distinct()
                // The distinct() operation removes duplicate elements
                // from the stream. It ensures that each category
                // appears only once in the resulting stream.
                .toList();
    }

    @Override
    public Optional<Expense> getExpenseById(Long id, Long userId) {
        return expenseRepository.findByIdAndUserId(id,userId)
        //ExpenseDataLoader.getExpenses()
                .stream().filter(
                expense -> expense.getId().equals(id)
        ).findFirst();
    }

    @Override
    public Expense addExpense(Expense expense, Long userId) {
        Optional<AppUser> userOptional = userService.findUserById(userId);
        if(userOptional.isPresent()){
            AppUser user = userOptional.get();
            expense.setUser(user);
            return expenseRepository.save(expense);
        }else {
            throw new  RuntimeException("User not found");
        }

//        expense.setId(idCounter.incrementAndGet());
//        ExpenseDataLoader.getExpenses().add(expense);

    }

    @Override
    public boolean updateExpense(Expense updatedExpense, Long userId) {
        Optional<Expense> existingExpense = expenseRepository.findByIdAndUserId(
                updatedExpense.getId(),userId);
                //getExpenseById(updatedExpense.getId());
        if(existingExpense.isPresent()){
            updatedExpense.setUser(existingExpense.get().getUser());
            expenseRepository.save(updatedExpense);
//            ExpenseDataLoader.getExpenses().remove(existingExpense.get());
//            ExpenseDataLoader.getExpenses().add(updatedExpense);
            return  true;
        }
        return false;
    }

    @Override
    public boolean deleteExpense(Long id,Long userId) {
        Optional<Expense> existingExpense =
                expenseRepository.findByIdAndUserId(id,userId);
        // getExpenseById(id);
        if(existingExpense.isPresent()){
            expenseRepository.deleteById(id);
            //ExpenseDataLoader.getExpenses().remove(existingExpense.get());
            return true;
        }
        return false;
    }


}
