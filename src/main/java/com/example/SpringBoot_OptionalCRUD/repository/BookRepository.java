package com.example.SpringBoot_OptionalCRUD.repository;

import com.example.SpringBoot_OptionalCRUD.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
