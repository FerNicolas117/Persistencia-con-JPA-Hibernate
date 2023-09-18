package com.latam.alura.tienda.dao;

import com.latam.alura.tienda.modelo.Producto;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class ProductoDao {

    private EntityManager em;

    public ProductoDao(EntityManager em) {
        this.em = em;
    }

    // Metodo guardar
    public void guardar(Producto producto) {
        this.em.persist(producto);
    }

    // Consulta por ID
    public Producto consultaPorId(Long id) {
        return em.find(Producto.class, id);
    }

    // Consulta de la lista o tabla completa
    public List<Producto> consultarTodos() {
        // Consulta a traves de JPQL -> Java Persistence Query Language
        String jpql = "SELECT P FROM Producto AS P";
        return em.createQuery(jpql, Producto.class).getResultList();
    }

    public List<Producto> consultaPorNombre(String nombre) {
        String jpql = "select P from Producto as P where P.nombre =: nombre";
        return em.createQuery(jpql, Producto.class).setParameter("nombre", nombre).getResultList();
    }

    public List<Producto> consultaPorNombreDeCategoria(String nombre) {
        String jpql = "select P from Producto as P where P.categoria.nombre =: nombre";
        return em.createQuery(jpql, Producto.class).setParameter("nombre", nombre).getResultList();
    }

    public BigDecimal consultarPrecioPorNombreDeProducto(String nombre) {
        String jpql = "select P.precio from Producto as P where P.nombre =: nombre";
        return em.createQuery(jpql, BigDecimal.class).setParameter("nombre", nombre).getSingleResult();
    }
}
