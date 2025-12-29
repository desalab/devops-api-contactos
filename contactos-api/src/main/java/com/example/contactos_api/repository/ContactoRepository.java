package com.example.contactos_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.contactos_api.entity.Contacto;

@Repository
public interface ContactoRepository extends JpaRepository<Contacto, Long> {

}
