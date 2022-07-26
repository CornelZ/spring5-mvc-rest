package guru.springframework.bootstrap;

import guru.springframework.domain.Category;
import guru.springframework.domain.Customer;
import guru.springframework.domain.Vendor;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.CustomerRepository;
import guru.springframework.repositories.VendorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/** Created by jt on 9/24/17. */
@Slf4j
@Component
public class Bootstrap implements CommandLineRunner {

  private final CategoryRepository categoryRepository;
  private final CustomerRepository customerRepository;
  private final VendorRepository vendorRepository;

  public Bootstrap(
      CategoryRepository categoryRepository,
      CustomerRepository customerRepository,
      VendorRepository vendorRepository) {
    this.categoryRepository = categoryRepository;
    this.customerRepository = customerRepository;
    this.vendorRepository = vendorRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    loadCategories();
    loadCustomers();
    loadVendors();
  }

  private void loadCategories() {
    Category fruits = new Category();
    fruits.setName("Fruits");

    Category dried = new Category();
    dried.setName("Dried");

    Category fresh = new Category();
    fresh.setName("Fresh");

    Category exotic = new Category();
    exotic.setName("Exotic");

    Category nuts = new Category();
    nuts.setName("Nuts");

    categoryRepository.save(fruits);
    categoryRepository.save(dried);
    categoryRepository.save(fresh);
    categoryRepository.save(exotic);
    categoryRepository.save(nuts);

    log.debug("Data loaded: {}", categoryRepository.count());
  }

  private void loadCustomers() {
    // given
    Customer customer1 = new Customer();
    customer1.setId(1l);
    customer1.setFirstName("Michale");
    customer1.setLastName("Weston");
    customerRepository.save(customer1);

    Customer customer2 = new Customer();
    customer2.setId(2l);
    customer2.setFirstName("Sam");
    customer2.setLastName("Axe");

    customerRepository.save(customer2);

    log.debug("Customers loaded: {}", customerRepository.count());
  }

  private void loadVendors() {
    Vendor vendor1 = new Vendor();
    vendor1.setName("Vendor 1");
    vendorRepository.save(vendor1);

    Vendor vendor2 = new Vendor();
    vendor2.setName("Vendor 2");
    vendorRepository.save(vendor2);
  }
}
