package com.example.contactos_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.contactos_api.entity.Contacto;
import com.example.contactos_api.service.ContactoService;

@RestController
@RequestMapping("/api/contactos")
public class ContactoController {

    //inject service
    @Autowired
    private ContactoService contactoService;


    //getAllContactos
    @GetMapping
    public List<Contacto> getAllContactos() {
        return contactoService.getAllContactos();
    }

    //getContactoById
    @GetMapping("/{id}")
    public Contacto getContactoById(@PathVariable Long id) {
        return contactoService.getContactoById(id);
    }

    //createContacto
    @PostMapping
    public Contacto createContacto(@RequestBody Contacto contacto) {
        return contactoService.createContacto(contacto);
    }

    //updateContacto
    @PutMapping("/{id}")
    public Contacto updateContacto(@PathVariable Long id, @RequestBody Contacto contacto) {
        return contactoService.updateContacto(id, contacto);
    }

    //deleteContacto
    @DeleteMapping("/{id}")
    public void deleteContacto(@PathVariable Long id) {
        contactoService.deleteContacto(id);
    }

}
