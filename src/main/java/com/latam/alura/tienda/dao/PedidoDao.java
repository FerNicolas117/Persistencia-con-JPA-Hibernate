package com.latam.alura.tienda.dao;

import com.latam.alura.tienda.modelo.Pedido;
import com.latam.alura.tienda.modelo.Producto;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class PedidoDao {

    private EntityManager em;

    public PedidoDao(EntityManager em) {
        this.em = em;
    }

    // Metodo guardar
    public void guardar(Pedido pedido) {
        this.em.persist(pedido);
    }

    // Consulta por ID
    public Pedido consultaPorId(Long id) {
        return em.find(Pedido.class, id);
    }

    // Consulta de la lista o tabla completa
    public List<Pedido> consultarTodos() {
        // Consulta a traves de JPQL -> Java Persistence Query Language
        String jpql = "SELECT P FROM Pedido AS P";
        return em.createQuery(jpql, Pedido.class).getResultList();
    }

    public List<Pedido> consultaPorNombre(String nombre) {
        String jpql = "select P from Pedido as P where P.nombre =: nombre";
        return em.createQuery(jpql, Pedido.class).setParameter("nombre", nombre).getResultList();
    }

    public List<Pedido> consultaPorNombreDeCategoria(String nombre) {
        String jpql = "select P from Pedido as P where P.categoria.nombre =: nombre";
        return em.createQuery(jpql, Pedido.class).setParameter("nombre", nombre).getResultList();
    }

    public BigDecimal consultarPrecioPorNombreDeProducto(String nombre) {
        String jpql = "select P.precio from Pedido as P where P.nombre =: nombre";
        return em.createQuery(jpql, BigDecimal.class).setParameter("nombre", nombre).getSingleResult();
    }
}
