package com.example.contactos_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.contactos_api.entity.Contacto;
import com.example.contactos_api.repository.ContactoRepository;

@Service
public class ContactoService {

    @Autowired
    private ContactoRepository contactoRepository;

    //getAllContactos
    public List<Contacto> getAllContactos() {
        return contactoRepository.findAll();
    }
    //getContactoById
    public Contacto getContactoById(Long id) {  
        return contactoRepository.findById(id).orElse(null);
    }
    //createContacto
    public Contacto createContacto(Contacto contacto) {
        return contactoRepository.save(contacto);
    }
    //updateContacto
    public Contacto updateContacto(Long id, Contacto contactoDetails) {

        Contacto contacto = contactoRepository.findById(id).orElse(null);
        if (contacto != null) {
            contacto.setNombre(contactoDetails.getNombre());
            contacto.setTelefono(contactoDetails.getTelefono());
            contacto.setEmail(contactoDetails.getEmail());
            return contactoRepository.save(contacto);
        }

        return null;
    }
    //deleteContacto
    public void deleteContacto(Long id) {
        contactoRepository.deleteById(id);
    }  

}   