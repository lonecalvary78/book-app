package demo.app.book.mapper;

import demo.app.book.domain.entity.Book;
import demo.app.book.model.BookDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface BookMapper {
  @Mappings({
     @Mapping(source="id", target="id"),
     @Mapping(source="isbn", target="isbn"),
     @Mapping(source="title", target="title"),
     @Mapping(source="author", target="author"),
     @Mapping(source="yearOfPublished", target="yearOfPublished")
  })
  Book fromDTO(BookDTO bookDTO);

  @Mappings({
          @Mapping(source="id", target="id"),
          @Mapping(source="isbn", target="isbn"),
          @Mapping(source="title", target="title"),
          @Mapping(source="author", target="author"),
          @Mapping(source="yearOfPublished", target="yearOfPublished")
  })
  BookDTO fromEntity(Book book);
}
