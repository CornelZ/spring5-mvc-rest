package guru.springframework.api.v1.mapper;

import static org.junit.Assert.assertEquals;

import guru.springframework.api.v1.model.VendorDTO;
import guru.springframework.domain.Vendor;
import org.junit.Test;

public class VendorMapperTest {

  public static final String NAME = "someName";

  VendorMapper vendorMapper = VendorMapper.INSTANCE;

  @Test
  public void vendorToVendorDTO() throws Exception {
    // given
    Vendor vendor = new Vendor();
    vendor.setName(NAME);

    // when
    VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);

    // then
    assertEquals(vendor.getName(), vendorDTO.getName());
  }

  @Test
  public void vendorDTOtoVendor() throws Exception {
    // given
    VendorDTO vendorDTO = new VendorDTO();
    vendorDTO.setName(NAME);

    // when
    Vendor vendor = vendorMapper.vendorDTOtoVendor(vendorDTO);

    // then
    assertEquals(vendorDTO.getName(), vendor.getName());
  }
}
