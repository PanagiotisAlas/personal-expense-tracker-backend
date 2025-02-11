//package org.example.services;
//
//import org.example.model.Expense;
//import org.example.repository.ExpenseRepository;
//import org.springframework.context.annotation.Profile;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//@Service
//@Profile("db")
//public class ExpenseServiceImplDb implements ExpenseService {
//
//    private final ExpenseRepository expenseRepository;
//
//    public ExpenseServiceImplDb(ExpenseRepository expenseRepository) {
//        this.expenseRepository = expenseRepository;
//    }
//
//    @Override
//    public List<Expense> getExpenseByDay(String date) {
//        return expenseRepository.findAll().stream().filter(
//                expense -> expense.getDate().equalsIgnoreCase(date))
//                .toList();
//    }
//
//    @Override
//    public List<Expense> getExpenseByCategoryAndMonth(String category, String month) {
//        return expenseRepository.findAll().stream().filter(
//                expense -> expense.getCategory()
//                        .equalsIgnoreCase(category) && expense
//                        .getDate().startsWith(month)
//        ).toList();
//    }
//
//    @Override
//    public List<String> getAllExpenseCategories() {
//        return expenseRepository.findAll().stream() // This converts the list of expenses
//                // (List<Expenses>) into a stream.
//                // A stream is an abstraction introduced in Java 8
//                // that allows you to perform a sequence
//                //of operations (like filtering, mapping,
//                // reducing, etc.) on elements of a collection.
//                .map( // The map operation is used to transform
//                        //elements in the stream. here,
//                        //it takes each Expense object and
//                        // transforms it by applying the
//                        // getCategory() method to each one.
//                        Expense::getCategory)
//                // Expense::getCategory is a method reference,
//                // which is shorthand for (expense) -> expense.getCategory
//                .distinct()
//                // The distinct() operation removes duplicate elements
//                // from the stream. It ensures that each category
//                // appears only once in the resulting stream.
//                .toList();
//    }
//
//    @Override
//    public Optional<Expense> getExpenseById(Long id) {
//        return expenseRepository.findById(id);
//    }
//
//    @Override
//    public Expense addExpense(Expense expense) {
//        return expenseRepository.save(expense);
//    }
//
//    @Override
//    public boolean updateExpense(Expense updatedExpense) {
//        if(expenseRepository.existsById(updatedExpense.getId())){
//            expenseRepository.save(updatedExpense);
//            return true;
//        }
//        return false;
//    }
//
//    @Override
//    public boolean deleteExpense(Long id) {
//        if(expenseRepository.existsById(id)){
//            expenseRepository.deleteById(id);
//            return true;
//        }
//        return false;
//    }
//}
