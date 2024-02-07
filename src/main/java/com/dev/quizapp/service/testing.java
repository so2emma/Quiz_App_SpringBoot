package com.example.books.service.impl;

import com.example.books.model.Book;
import com.example.books.model.Cart;
import com.example.books.repositories.BookRepositories;
import com.example.books.repositories.CartRepositories;
import com.example.books.service.CartService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    public CartRepositories cartRepositories;

    @Autowired
    public BookRepositories bookRepositories;

    @Override
    @Transactional
    public ResponseEntity<Cart> addBookToCart(Long cartId, Long bookId) throws ChangeSetPersister.NotFoundException {
//        try{
            Optional<Cart> optionalCart  =  cartRepositories.findById(cartId);
            Optional<Book> optionalBook   = bookRepositories.findById(bookId);

//            if(optionalCart.isPresent()){
        Cart cart = optionalCart.orElseThrow(ChangeSetPersister.NotFoundException::new);
        Book book = optionalBook.orElseThrow(ChangeSetPersister.NotFoundException::new);
                cart.getBooks().add(book);
                cartRepositories.save(cart);
                return new ResponseEntity<>(cart, HttpStatus.CREATED);
//            }
//        } catch(Exception e) {
//            e.printStackTrace(System.out);
//        }
//        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

//    @Override
//    @Transactional
//    public ResponseEntity<Cart> addBookToCart(Long cartId, Long bookId) {
//        try{
//            Optional<Cart> optionalCart = cartRepositories.findById(cartId);
//            Optional<Book> optionalBook   = bookRepositories.findById(bookId);
//
//            if(optionalCart.isEmpty()) {
//                return new ResponseEntity<>( HttpStatus.NOT_FOUND);
//            } else if (optionalBook.isEmpty()) {
//                return new ResponseEntity<>( HttpStatus.NOT_FOUND);
//            }
//
//            Cart cart = optionalCart.get();
//            Book book = optionalBook.get();
//            cart.getBooks().add(book);
//            cartRepositories.save(cart);
//            return new ResponseEntity<>(cart, HttpStatus.CREATED);
//        } catch(Exception e) {
//            e.printStackTrace(System.out);
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @Override
    @Transactional
    public ResponseEntity<Cart> removeBookFromCart(Long cartId, Long bookId) {
        try{
            Optional<Cart> optionalCart  =  cartRepositories.findById(cartId);
            Optional<Book> optionalBook   = bookRepositories.findById(bookId);

            if(optionalCart.isPresent() && optionalBook.isPresent()){
                Cart cart = optionalCart.get();
                Set<Book> books  = cart.getBooks();

                Book book = optionalBook.get();

                if(books.contains(book))
                {
                    cart.getBooks().remove(book);
                    cartRepositories.save(cart);
                    return new ResponseEntity<>(cart, HttpStatus.OK);
                }
                else {
                    return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            }
        } catch(Exception e) {
            e.printStackTrace(System.out);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Optional<Cart>> getCart(Long cartId) {
        try {
            return new ResponseEntity<>(cartRepositories.findById(cartId), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
