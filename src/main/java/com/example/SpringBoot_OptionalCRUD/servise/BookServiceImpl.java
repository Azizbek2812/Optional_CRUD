package com.example.SpringBoot_OptionalCRUD.servise;

import com.example.SpringBoot_OptionalCRUD.DTO.BookDto;
import com.example.SpringBoot_OptionalCRUD.entity.Book;
import com.example.SpringBoot_OptionalCRUD.exception.BookNotFoundException;
import com.example.SpringBoot_OptionalCRUD.mapper.BookMapper;
import com.example.SpringBoot_OptionalCRUD.repository.BookRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.IllegalFormatCodePointException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }


    @Override
    public List<BookDto> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::toBookDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookDto getBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(()-> new  BookNotFoundException("Book not foun exception ${}"));
        return bookMapper.toBookDto(book);
    }

    @Override
    public BookDto addBook(BookDto book) {
        bookRepository.save(bookMapper.toBook(book));
        return book;
    }

    @Override
    public BookDto updateBook(Long id, BookDto book) {
        Book excistingBook = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found exception with"+id+" this is"));
        excistingBook.setPrice(book.price());
        excistingBook.setTitle(book.title());
        Book updatedBook = bookRepository.save(excistingBook);
        return bookMapper.toBookDto(updatedBook);
    }

    @Override
    public void  deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException("Book not found exception with"+id+" this is");
        }
        bookRepository.deleteById(id);
    }
}
