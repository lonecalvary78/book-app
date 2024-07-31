package demo.app.book.util.mapper;

import demo.app.book.domain.renting.entity.Renting;
import demo.app.book.domain.renting.model.RentingRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface RentingMapper {
  @Mappings({
    @Mapping(source="bookId", target="bookId"),
    @Mapping(source="borrowerId", target="borrowerId")
  })
  Renting fromDTO(RentingRequestDTO rentingRequestDTO);
}
