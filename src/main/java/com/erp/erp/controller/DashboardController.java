package com.erp.erp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/")
    public String index() {
        return "/login";
    }
    
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }
        @GetMapping("/caja")
    public String caja() {
        return "caja";
    }
        @GetMapping("/ventas")
    public String venta() {
        return "ventas";
    }
        @GetMapping("/inventario")
    public String inventario() {
        return "inventario";
    }
        @GetMapping("/almacen")
    public String almacen() {
        return "almacen";
    }
        @GetMapping("/movimientos")
    public String movimientos() {
        return "movimientos";
    }
        @GetMapping("/proveedores")
    public String proveedores() {
        return "proveedores";
    }
        @GetMapping("/reportes")
    public String reportes() {
        return "reportes";
    }
        @GetMapping("/Clientes")
    public String clientes() {
        return "clientes";
    }
        @GetMapping("/productos")
    public String productos() {
        return "productos";
    }
        @GetMapping("/apertura-caja")
    public String aperturacaja() {
        return "apertura-caja";
    }
    
}