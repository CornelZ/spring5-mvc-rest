package guru.springframework.api.v1.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CatorgoryListDTO {

  List<CategoryDTO> categories;
}
