package demo.app.book.mapper;

import demo.app.book.domain.entity.Borrower;
import demo.app.book.model.BorrowerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface BorrowerMapper {
  @Mappings({
    @Mapping(source="id", target="id"),
    @Mapping(source="firstName", target="firstName"),
    @Mapping(source="lastName", target="lastName")
  })
  Borrower fromDTO(BorrowerDTO borrowerDTO);

  @Mappings({
          @Mapping(source="id", target="id"),
          @Mapping(source="firstName", target="firstName"),
          @Mapping(source="lastName", target="lastName")
  })
  BorrowerDTO toEntity(Borrower borrower);
}
