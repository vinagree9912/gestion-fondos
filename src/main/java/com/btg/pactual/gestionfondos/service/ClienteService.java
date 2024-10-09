package com.btg.pactual.gestionfondos.service;

import com.btg.pactual.gestionfondos.dto.ClienteRequestDTO;
import com.btg.pactual.gestionfondos.model.Cliente;

import java.util.List;

public interface ClienteService {
    Cliente crearCliente(ClienteRequestDTO clienteRequestDTO);
    List<ClienteRequestDTO> crearListaClientes(List<ClienteRequestDTO> clienteRequestDTO);
}
