package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.api.v1.model.CustomerListDTO;
import guru.springfamework.services.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/api/v1/customers/")
public class CustomerController {
  private final CustomerService customerService;

  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @GetMapping
  public ResponseEntity<CustomerListDTO> getListOfCustomers() {

    return new ResponseEntity<>(
        new CustomerListDTO(customerService.getAllCustomers()), HttpStatus.OK);
  }

  @GetMapping({"{id}"})
  public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
    return new ResponseEntity<>(customerService.getCustomerById(id), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<CustomerDTO> createNewCustomer(@RequestBody CustomerDTO customerDTO) {
    final CustomerDTO customerDto = customerService.createNewCustomer(customerDTO);
    log.debug("customerDto: {}", customerDto);
    return new ResponseEntity<>(customerDto, HttpStatus.CREATED);
  }

  @PutMapping({"{id}"})
  public ResponseEntity<CustomerDTO> updateCustomer(
      @PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
    final CustomerDTO savedCustomerDto = customerService.saveCustomerByDTO(id, customerDTO);
    return new ResponseEntity<>(savedCustomerDto, HttpStatus.OK);
  }
}
