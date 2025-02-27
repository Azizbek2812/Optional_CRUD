package com.example.SpringBoot_OptionalCRUD.mapper;

import com.example.SpringBoot_OptionalCRUD.DTO.BookDto;
import com.example.SpringBoot_OptionalCRUD.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);
    BookDto toBookDto(Book book);
    Book toBook(BookDto bookDto);
}
