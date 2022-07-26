package guru.springframework.services;

import guru.springframework.api.v1.mapper.VendorMapper;
import guru.springframework.api.v1.model.VendorDTO;
import guru.springframework.api.v1.model.VendorListDTO;
import guru.springframework.controllers.v1.VendorController;
import guru.springframework.domain.Vendor;
import guru.springframework.repositories.VendorRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class VendorServiceImpl implements VendorService {

  private final VendorMapper vendorMapper;
  private final VendorRepository vendorRepository;

  public VendorServiceImpl(VendorMapper vendorMapper, VendorRepository vendorRepository) {
    this.vendorMapper = vendorMapper;
    this.vendorRepository = vendorRepository;
  }

  @Override
  public VendorDTO getVendorById(Long id) {
    return vendorRepository
        .findById(id)
        .map(vendorMapper::vendorToVendorDTO)
        .map(vendorDTO -> vendorDTO.setVendorUrl(getVendorUrl(id)))
        .orElseThrow(ResourceNotFoundException::new);
  }

  @Override
  public VendorListDTO getAllVendors() {
    List<VendorDTO> vendorDTOS =
        vendorRepository.findAll().stream()
            .map(
                vendor ->
                    vendorMapper
                        .vendorToVendorDTO(vendor)
                        .setVendorUrl(getVendorUrl(vendor.getId())))
            .collect(Collectors.toList());

    return new VendorListDTO(vendorDTOS);
  }

  @Override
  public VendorDTO createNewVendor(VendorDTO vendorDTO) {
    return saveAndReturnDTO(vendorMapper.vendorDTOtoVendor(vendorDTO));
  }

  @Override
  public VendorDTO saveVendorByDTO(Long id, VendorDTO vendorDTO) {
    Vendor vendorToSave = vendorMapper.vendorDTOtoVendor(vendorDTO).setId(id);
    return saveAndReturnDTO(vendorToSave);
  }

  @Override
  public VendorDTO patchVendor(Long id, VendorDTO vendorDTO) {
    return vendorRepository
        .findById(id)
        .map(
            vendor -> {
              if (vendorDTO.getName() != null) {
                vendor.setName(vendorDTO.getName());
              }
              return saveAndReturnDTO(vendor);
            })
        .orElseThrow(ResourceNotFoundException::new);
  }

  @Override
  public void deleteVendorById(Long id) {
    vendorRepository.deleteById(id);
  }

  private String getVendorUrl(Long id) {
    return VendorController.BASE_URL + "/" + id;
  }

  private VendorDTO saveAndReturnDTO(Vendor vendor) {
    Vendor savedVendor = vendorRepository.save(vendor);
    return vendorMapper
        .vendorToVendorDTO(savedVendor)
        .setVendorUrl(getVendorUrl(savedVendor.getId()));
  }
}
