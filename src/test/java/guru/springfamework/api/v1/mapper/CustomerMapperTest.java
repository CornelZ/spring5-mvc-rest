package guru.springfamework.api.v1.mapper;

import static org.junit.Assert.assertEquals;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import org.junit.Test;

public class CustomerMapperTest {

  public static final String FIRST_NAME = "Jimmy";
  public static final String LAST_NAME = "Fallon";
  CustomerMapper customerMapper = CustomerMapper.INSTANCE;

  @Test
  public void customerToCustomerDTO() throws Exception {
    // given
    Customer customer = new Customer();
    customer.setFirstName(FIRST_NAME);
    customer.setLastName(LAST_NAME);

    // when
    CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

    // then
    assertEquals(FIRST_NAME, customerDTO.getFirstName());
    assertEquals(LAST_NAME, customerDTO.getLastName());
  }
}
