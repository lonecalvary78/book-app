package demo.app.book.util.mapper;

import demo.app.book.domain.rent.entity.Rent;
import demo.app.book.domain.rent.model.RentRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface RentMapper {
  @Mappings({
    @Mapping(source="bookId", target="bookId"),
    @Mapping(source="borrowerId", target="borrowerId")
  })
  Rent fromDTO(RentRequestDTO rentRequestDTO);
}
