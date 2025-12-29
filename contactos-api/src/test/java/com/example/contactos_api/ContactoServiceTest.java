package com.example.contactos_api;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.contactos_api.entity.Contacto;
import com.example.contactos_api.repository.ContactoRepository;
import com.example.contactos_api.service.ContactoService;

@ExtendWith(MockitoExtension.class)
class ContactoServiceTest {

    @Mock
    private ContactoRepository contactoRepository;

    @InjectMocks
    private ContactoService contactoService;

    private Contacto contacto1;
    private Contacto contacto2;

    @BeforeEach
    void setUp() {
        contacto1 = new Contacto(1L, "Juan Pérez", "555-1234", "juan@example.com");
        contacto2 = new Contacto(2L, "María García", "555-5678", "maria@example.com");
    }

    @Test
    void testGetAllContactos() {
        // Arrange
        List<Contacto> contactos = Arrays.asList(contacto1, contacto2);
        when(contactoRepository.findAll()).thenReturn(contactos);

        // Act
        List<Contacto> resultado = contactoService.getAllContactos();

        // Assert
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Juan Pérez", resultado.get(0).getNombre());
        assertEquals("María García", resultado.get(1).getNombre());
        verify(contactoRepository, times(1)).findAll();
    }

    @Test
    void testGetContactoById_Encontrado() {
        // Arrange
        when(contactoRepository.findById(1L)).thenReturn(Optional.of(contacto1));

        // Act
        Contacto resultado = contactoService.getContactoById(1L);

        // Assert
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Juan Pérez", resultado.getNombre());
        assertEquals("555-1234", resultado.getTelefono());
        assertEquals("juan@example.com", resultado.getEmail());
        verify(contactoRepository, times(1)).findById(1L);
    }

    @Test
    void testGetContactoById_NoEncontrado() {
        // Arrange
        when(contactoRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Act
        Contacto resultado = contactoService.getContactoById(999L);

        // Assert
        assertNull(resultado);
        verify(contactoRepository, times(1)).findById(999L);
    }

    @Test
    void testCreateContacto() {
        // Arrange
        Contacto nuevoContacto = new Contacto(null, "Pedro López", "555-9999", "pedro@example.com");
        Contacto contactoGuardado = new Contacto(3L, "Pedro López", "555-9999", "pedro@example.com");
        when(contactoRepository.save(any(Contacto.class))).thenReturn(contactoGuardado);

        // Act
        Contacto resultado = contactoService.createContacto(nuevoContacto);

        // Assert
        assertNotNull(resultado);
        assertEquals(3L, resultado.getId());
        assertEquals("Pedro López", resultado.getNombre());
        assertEquals("555-9999", resultado.getTelefono());
        assertEquals("pedro@example.com", resultado.getEmail());
        verify(contactoRepository, times(1)).save(nuevoContacto);
    }

    @Test
    void testUpdateContacto_Exitoso() {
        // Arrange
        Contacto contactoActualizado = new Contacto(null, "Juan Pérez Actualizado", "555-0000", "juannuevo@example.com");
        Contacto contactoGuardado = new Contacto(1L, "Juan Pérez Actualizado", "555-0000", "juannuevo@example.com");
        
        when(contactoRepository.findById(1L)).thenReturn(Optional.of(contacto1));
        when(contactoRepository.save(any(Contacto.class))).thenReturn(contactoGuardado);

        // Act
        Contacto resultado = contactoService.updateContacto(1L, contactoActualizado);

        // Assert
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Juan Pérez Actualizado", resultado.getNombre());
        assertEquals("555-0000", resultado.getTelefono());
        assertEquals("juannuevo@example.com", resultado.getEmail());
        verify(contactoRepository, times(1)).findById(1L);
        verify(contactoRepository, times(1)).save(any(Contacto.class));
    }

    @Test
    void testUpdateContacto_NoEncontrado() {
        // Arrange
        Contacto contactoActualizado = new Contacto(null, "Nombre", "Telefono", "Email");
        when(contactoRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Act
        Contacto resultado = contactoService.updateContacto(999L, contactoActualizado);

        // Assert
        assertNull(resultado);
        verify(contactoRepository, times(1)).findById(999L);
        verify(contactoRepository, never()).save(any(Contacto.class));
    }

    @Test
    void testDeleteContacto() {
        // Arrange
        doNothing().when(contactoRepository).deleteById(1L);

        // Act
        contactoService.deleteContacto(1L);

        // Assert
        verify(contactoRepository, times(1)).deleteById(1L);
    }
}