package com.example.learningcaffeine.service;

import com.example.learningcaffeine.model.Address;
import com.example.learningcaffeine.model.AddressDTO;
import com.example.learningcaffeine.repo.AddressRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AddressService {
    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    /**
     * The example above tells Spring to use a cache named address_cache
     * and the id argument for the cache key.
     */
    @Cacheable(value = "addresses", key = "#id")
    public AddressDTO findById(Long id) {
        log.info("Fetching address from database for id: {}", id);
        Address address = addressRepository.findById(id).orElseThrow(() -> new RuntimeException("Address not found"));
        return convertToDTO(address);
    }

    @CachePut(value = "addresses", key = "#result.id")
    public AddressDTO save(AddressDTO addressDTO) {
        Address address = convertToEntity(addressDTO);
        address = addressRepository.save(address);
        return convertToDTO(address);
    }

    @CacheEvict(value = "addresses", key = "#id")
    public void delete(Long id) {
        addressRepository.deleteById(id);
    }

    private AddressDTO convertToDTO(Address address) {
        return new AddressDTO(
                address.getId(),
                address.getStreet(),
                address.getCity(),
                address.getState(),
                address.getZipCode(),
                address.getCountry());
    }

    private Address convertToEntity(AddressDTO dto) {
        return new Address(
                dto.getId(),
                dto.getStreet(),
                dto.getCity(),
                dto.getState(),
                dto.getZipCode(),
                dto.getCountry(),
                null,
                null);
    }
}
