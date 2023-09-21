package com.latam.alura.tienda.modelo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate fecha = LocalDate.now();
    private BigDecimal valorTotal = new BigDecimal(0);

    // Muchos Pedidos pertenecen a un Cliente
    @ManyToOne
    private Cliente cliente;

    // Un pedido puede tener multiples productos, y esos productos pueden estar en muchos pedidos
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL) // ManyToOne   OneToMany  ->  ManyToMany
    private List<ItemsPedido> items = new ArrayList<>();

    // Constructor default
    public Pedido() {

    }

    // Metodo constructor
    public Pedido(Cliente cliente) {
        this.cliente = cliente;
    }

    public void agregarItems(ItemsPedido item) {
        item.setPedido(this);
        this.items.add(item);
        this.valorTotal = this.valorTotal.add(item.getValor());
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
