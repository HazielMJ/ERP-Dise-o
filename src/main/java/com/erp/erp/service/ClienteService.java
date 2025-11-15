package com.erp.erp.service;

import com.erp.erp.entity.*;
import com.erp.erp.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Transactional
public class ClienteService {
    private final ClienteRepository clienteRepository;
    
    public Cliente crearCliente(Cliente cliente) {
        if (cliente.getRfc() != null && clienteRepository.findByRfc(cliente.getRfc()).isPresent()) {
            throw new RuntimeException("Ya existe un cliente con ese RFC");
        }
        return clienteRepository.save(cliente);
    }
    
    public Cliente actualizarCliente(Integer id, Cliente cliente) {
        Cliente existente = obtenerClientePorId(id);
        existente.setTipoCliente(cliente.getTipoCliente());
        existente.setNombreRazonSocial(cliente.getNombreRazonSocial());
        existente.setRfc(cliente.getRfc());
        existente.setTelefono(cliente.getTelefono());
        existente.setEmail(cliente.getEmail());
        existente.setDireccion(cliente.getDireccion());
        existente.setLimiteCredito(cliente.getLimiteCredito());
        existente.setObservaciones(cliente.getObservaciones());
        return clienteRepository.save(existente);
    }
    
    public void actualizarSaldo(Integer id, BigDecimal monto, boolean esAumento) {
        Cliente cliente = obtenerClientePorId(id);
        BigDecimal nuevoSaldo = esAumento ? 
            cliente.getSaldoActual().add(monto) : 
            cliente.getSaldoActual().subtract(monto);
        
        if (nuevoSaldo.compareTo(cliente.getLimiteCredito()) > 0) {
            throw new RuntimeException("El saldo excede el límite de crédito del cliente");
        }
        
        cliente.setSaldoActual(nuevoSaldo);
        clienteRepository.save(cliente);
    }
    
    public void desactivarCliente(Integer id) {
        Cliente cliente = obtenerClientePorId(id);
        cliente.setEstado(Cliente.EstadoCliente.INACTIVO);
        clienteRepository.save(cliente);
    }
    
    @Transactional(readOnly = true)
    public Cliente obtenerClientePorId(Integer id) {
        return clienteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }
    
    @Transactional(readOnly = true)
    public List<Cliente> obtenerTodosLosClientes() {
        return clienteRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public List<Cliente> obtenerClientesActivos() {
        return clienteRepository.findByEstado(Cliente.EstadoCliente.ACTIVO);
    }
    
    @Transactional(readOnly = true)
    public List<Cliente> buscarClientesPorNombre(String nombre) {
        return clienteRepository.findByNombreRazonSocialContaining(nombre);
    }
    
    @Transactional(readOnly = true)
    public List<Cliente> obtenerClientesConSaldoExcedido() {
        return clienteRepository.findClientesConSaldoExcedido();
    }
}
