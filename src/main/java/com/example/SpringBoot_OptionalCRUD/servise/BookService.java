package com.example.SpringBoot_OptionalCRUD.servise;

import com.example.SpringBoot_OptionalCRUD.DTO.BookDto;
import com.example.SpringBoot_OptionalCRUD.entity.Book;
import com.example.SpringBoot_OptionalCRUD.mapper.BookMapper;
import com.example.SpringBoot_OptionalCRUD.repository.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookDto> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(BookMapper.INSTANCE::toBookDto)
                .collect(Collectors.toList());
    }
    public ResponseEntity<BookDto> getBookById(Long id) {
        return bookRepository.findById(id)
                .map(book-> ResponseEntity.ok(BookMapper.INSTANCE.toBookDto(book)))
                .orElseGet(()->ResponseEntity.notFound().build());
    }
    public ResponseEntity<BookDto> createBook(BookDto bookDto) {
        Book book = BookMapper.INSTANCE.toBook(bookDto);
        book = bookRepository.save(book);
        return  ResponseEntity.status(HttpStatus.CREATED)
                .body(BookMapper.INSTANCE.toBookDto(book));
    }
    public ResponseEntity<BookDto> updateBook(Long id, BookDto bookDto) {
        return bookRepository.findById(id)
                .map(existingBook -> {
                    existingBook.setTitle(bookDto.title());
                    existingBook.setAuthor(bookDto.author());
                    existingBook.setDescription(bookDto.description());
                    existingBook.setPrice(bookDto.price());
                    bookRepository.save(existingBook);
                    return ResponseEntity.ok(BookMapper.INSTANCE.toBookDto(existingBook));
                }).orElseGet(()->ResponseEntity.notFound().build());
    }
    public ResponseEntity<Void> deleteBook(Long id) {
        return bookRepository.findById(id)
                .map(book -> {
                    bookRepository.delete(book);
                    return ResponseEntity.noContent().<Void>build(); // <Void> qo'shildi
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).<Void>build()); // <Void> qo'shildi
    }
}
