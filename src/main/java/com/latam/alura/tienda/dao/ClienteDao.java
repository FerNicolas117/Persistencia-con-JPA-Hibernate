package com.latam.alura.tienda.dao;

import com.latam.alura.tienda.modelo.Cliente;
import com.latam.alura.tienda.modelo.Pedido;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class ClienteDao {

    private EntityManager em;

    public ClienteDao(EntityManager em) {
        this.em = em;
    }

    // Metodo guardar
    public void guardar(Cliente cliente) {
        this.em.persist(cliente);
    }

    // Consulta por ID
    public Cliente consultaPorId(Long id) {
        return em.find(Cliente.class, id);
    }

    // Consulta de la lista o tabla completa
    public List<Cliente> consultarTodos() {
        // Consulta a traves de JPQL -> Java Persistence Query Language
        String jpql = "SELECT P FROM Cliente AS P";
        return em.createQuery(jpql, Cliente.class).getResultList();
    }

    public List<Cliente> consultaPorNombre(String nombre) {
        String jpql = "select P from Cliente as P where P.nombre =: nombre";
        return em.createQuery(jpql, Cliente.class).setParameter("nombre", nombre).getResultList();
    }

    public List<Cliente> consultaPorNombreDeCategoria(String nombre) {
        String jpql = "select P from Cliente as P where P.categoria.nombre =: nombre";
        return em.createQuery(jpql, Cliente.class).setParameter("nombre", nombre).getResultList();
    }

    public BigDecimal consultarPrecioPorNombreDeProducto(String nombre) {
        String jpql = "select P.precio from Cliente as P where P.nombre =: nombre";
        return em.createQuery(jpql, BigDecimal.class).setParameter("nombre", nombre).getSingleResult();
    }
}
