package com.example.SpringBoot_OptionalCRUD.servise;

import com.example.SpringBoot_OptionalCRUD.DTO.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<BookDto> getAllBooks();

    BookDto getBookById(Long id);

    BookDto addBook(BookDto book);

    BookDto updateBook(Long id, BookDto book);

    void deleteBook(Long id);
}
