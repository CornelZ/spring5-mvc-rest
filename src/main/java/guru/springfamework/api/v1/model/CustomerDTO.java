package guru.springfamework.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class CustomerDTO {

  @ApiModelProperty(value = "This is the first name", required = true)
  private String firstName;

  @ApiModelProperty(required = true)
  private String lastName;

  private String customerUrl;
}
