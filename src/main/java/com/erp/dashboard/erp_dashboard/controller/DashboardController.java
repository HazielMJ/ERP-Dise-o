package com.erp.dashboard.erp_dashboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/")
    public String index() {
        return "dashboard";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }
        @GetMapping("/pd")
    public String puntoDeVenta() {
        return "pd"; // busca pd.html
    }
        @GetMapping("/caja")
    public String caja() {
        return "caja"; // busca caja.html
    }
        @GetMapping("/ventas")
    public String venta() {
        return "ventas"; // busca ventas.html
    }
        @GetMapping("/inventario")
    public String inventario() {
        return "inventaro"; // busca inventario.html
    }
        @GetMapping("/almacen")
    public String almacen() {
        return "almacen"; // busca almacen.html
    }
        @GetMapping("/kardex")
    public String kardex() {
        return "kardex"; // busca kardex.html
    }
        @GetMapping("/movimientos")
    public String movimientos() {
        return "movimientos"; // busca movimientos.html
    }
        @GetMapping("/proveedores")
    public String proveedores() {
        return "proveedores"; // busca proveedores.html
    }
        @GetMapping("/reportes")
    public String reportes() {
        return "reportes"; // busca reportes.html
    }
        @GetMapping("/clientes")
    public String clientes() {
        return "clientes"; // busca clientes.html
    }
        @GetMapping("/productos")
    public String productos() {
        return "productos"; // busca productos.html
    }
        @GetMapping("/apertura-caja")
    public String aperturacaja() {
        return "apertura-caja"; // busca apertura-caja.html
    }
    
}