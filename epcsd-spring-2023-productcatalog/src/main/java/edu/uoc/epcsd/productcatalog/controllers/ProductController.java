package edu.uoc.epcsd.productcatalog.controllers;

import edu.uoc.epcsd.productcatalog.entities.Product;
import edu.uoc.epcsd.productcatalog.kafka.ProductMessage;
import edu.uoc.epcsd.productcatalog.repositories.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private KafkaTemplate<String, ProductMessage> productKafkaTemplate;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAllProducts() {
        log.trace("getAllProducts");

        return productRepository.findAll();
    }

    @GetMapping("/prueba")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> prueba() {
        log.trace("prueba");

        return productRepository.findAll();
    }

    // add the code for the missing operations here

    /*
        ● crear sección/subsección
        ● crear producto
        ● crear unidad de producto
        ● eliminar producto
        ● cambiar el estado de una unidad de producto a operativo/no-operativo
        ● consulta de secciones/subsecciones por nombre
        ● consulta de secciones/subsecciones por descripción
        ● consulta de secciones/subsecciones por sección “padre”
        ● consulta de productos por nombre
        ● consulta de productos por sección
        ● consulta del detalle de un producto
        ● consulta del detalle de una unidad
    */
}
