package com.latam.alura.tienda.prueba;

import com.latam.alura.tienda.dao.CategoriaDao;
import com.latam.alura.tienda.dao.ProductoDao;
import com.latam.alura.tienda.modelo.Categoria;
import com.latam.alura.tienda.modelo.Producto;
import com.latam.alura.tienda.utils.JPAUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

public class RegistroDeProducto {
    public static void main(String[] args) {

        registrarProducto();
        EntityManager em = JPAUtils.getEntityManager();
        ProductoDao productoDao = new ProductoDao(em);
        Producto producto = productoDao.consultaPorId(1L);
        System.out.println(producto.getNombre());

        BigDecimal precio = productoDao.consultarPrecioPorNombreDeProducto("Xiaomi Redmi");
        System.out.println();
        System.out.println("Precio: " + precio);
    }

    private static void registrarProducto() {
        Categoria celulares = new Categoria("CELULARES");

        Producto celular = new Producto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"),
                celulares);

        EntityManager em = JPAUtils.getEntityManager();
        ProductoDao productoDao = new ProductoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);

        em.getTransaction().begin();

        categoriaDao.guardar(celulares);
        productoDao.guardar(celular);

        em.getTransaction().commit();
        em.close();
    }
}
