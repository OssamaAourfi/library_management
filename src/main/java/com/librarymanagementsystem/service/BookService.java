package com.librarymanagementsystem.service;

import com.librarymanagementsystem.entity.Book;
import com.librarymanagementsystem.entity.User;
import com.librarymanagementsystem.repository.BookRepository;
import com.librarymanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository  bookRepository;
    @Autowired
   private  UserRepository userRepository;

   public  List<Book> getAllBooks(){
       return bookRepository.findAll();
   }
   public Book getBookById(Long id){
       return bookRepository.findById(id).orElse(null);
   }
   public Book saveBook(Book book){
       return bookRepository.save(book);
   }
   public void deleteBook(Long id){
       bookRepository.deleteById(id);
   }
   public Book borrowBook(Long bookId, Long userId){
       Book book = bookRepository.findById(bookId).orElse(null);
       User user = userRepository.findById(userId).orElse(null);
       if(book != null && !book.isBorrowed() && user != null){
           book.setBorrowedBy(user);
           book.setBorrowed(true);
           return saveBook(book);
       }
       // Handle errors (e.g., book not found, book already borrowed, user not found)
       return null;
   }
   public Book returnBook(Long bookId){
       Book book = bookRepository.findById(bookId).orElse(null);
       if (book != null && book.isBorrowed()){
           book.setBorrowedBy(null);
           book.setBorrowed(false);
           return saveBook(book);
       }
       // Handle errors (e.g., book not found, book not borrowed)
       return null;
   }
}
