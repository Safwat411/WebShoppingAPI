package com.springboot.restapi.webshoppingapi.service;

import com.springboot.restapi.webshoppingapi.dto.request.OfficesRequest;
import com.springboot.restapi.webshoppingapi.model.Offices;
import com.springboot.restapi.webshoppingapi.repository.OfficesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OfficesService {

    private OfficesRepository officesRepo;

    public List<Offices> getOffices(){
        return officesRepo.findAll();
    }

    public Offices getOfficesById(Long id) throws Exception {
        Optional<Offices> office =  officesRepo.findById(id);
        if(office.isPresent()){
            return office.get();
        }
        throw new Exception("Office is not found for the ID: " + id);
    }

    public Offices saveOffices(OfficesRequest request){

        Offices office = new Offices();

        office.setCity(request.city());
        office.setPhone(request.phone());
        office.setAddressLine1(request.addressLine1());
        office.setAddressLine2(request.addressLine2());
        office.setState(request.state());
        office.setCountry(request.country());
        office.setPostalCode(request.postalCode());
        office.setTerritory(request.territory());

        return officesRepo.save(office);
    }

    public Offices updateOffices(Offices office){
        return officesRepo.save(office);
    }

    public void deleteOffices(Long id){
        officesRepo.deleteById(id);
    }
}
