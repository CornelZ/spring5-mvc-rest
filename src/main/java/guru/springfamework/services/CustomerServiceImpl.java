package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;
  private final CustomerMapper customerMapper;

  public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
    this.customerRepository = customerRepository;
    this.customerMapper = customerMapper;
  }

  @Override
  public List<CustomerDTO> getAllCustomers() {
    return customerRepository.findAll().stream()
        .map(
            customer -> {
              CustomerDTO customerDto = customerMapper.customerToCustomerDTO(customer);
              customerDto.setCustomerUrl("/api/v1/customer/" + customer.getId());
              return customerDto;
            })
        .collect(Collectors.toList());
  }

  @Override
  public CustomerDTO getCustomerById(Long id) {
    return customerRepository
        .findById(id)
        .map(customerMapper::customerToCustomerDTO)
        .map(custDto -> custDto.setCustomerUrl("/api/v1/customers/" + id))
        .orElseThrow(RuntimeException::new);
  }

  @Override
  public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {
    return saveAndReturnDTO(customerMapper.customerDtoToCustomer(customerDTO));
  }

  @Override
  public CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO) {
    Customer customer = customerMapper.customerDtoToCustomer(customerDTO);
    customer.setId(id);
    return saveAndReturnDTO(customer);
  }

  private CustomerDTO saveAndReturnDTO(Customer customer) {
    Customer savedCustomer = customerRepository.save(customer);
    CustomerDTO returnDto = customerMapper.customerToCustomerDTO(savedCustomer);
    returnDto.setCustomerUrl("/api/v1/customer/" + savedCustomer.getId());
    return returnDto;
  }

  @Override
  public CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO) {
    return customerRepository
        .findById(id)
        .map(
            customer -> {
              if (customerDTO.getFirstName() != null) {
                customer.setFirstName(customerDTO.getFirstName());
              }

              if (customerDTO.getLastName() != null) {
                customer.setLastName(customerDTO.getLastName());
              }
              return customerMapper.customerToCustomerDTO(customerRepository.save(customer));
            })
        .map(custDto -> custDto.setCustomerUrl("/api/v1/customers/" + id))
        .orElseThrow(RuntimeException::new);
    // todo implement better exception handling;
  }

  @Override
  public void deleteCustomerById(Long id) {
    customerRepository.deleteById(id);
  }
}
