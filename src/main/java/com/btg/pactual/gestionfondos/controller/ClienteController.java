package com.btg.pactual.gestionfondos.controller;

import com.btg.pactual.gestionfondos.dto.ClienteRequestDTO;
import com.btg.pactual.gestionfondos.model.Cliente;
import com.btg.pactual.gestionfondos.service.ClienteService;
import com.btg.pactual.gestionfondos.utils.Constantes;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

@RestController
@RequestMapping(Constantes.PATH_CLIENTES)
@AllArgsConstructor
@CrossOrigin(
        origins = "*",
        methods = {RequestMethod.OPTIONS})
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class ClienteController {
    private final Validator validator;
    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Cliente> crearCliente(@Valid @RequestBody ClienteRequestDTO clienteRequestDTO) {
        Cliente nuevoCliente = clienteService.crearCliente(clienteRequestDTO);
        return new ResponseEntity<>(nuevoCliente, HttpStatus.CREATED);
    }

    @PostMapping(Constantes.PATH_CREAR_LISTA_CLIENTES)
    public ResponseEntity<List<ClienteRequestDTO>> crearListaCliente(@Valid @RequestBody List<@Valid ClienteRequestDTO> clienteRequestDTO) {
        List<ClienteRequestDTO> nuevosClientes = clienteService.crearListaClientes(clienteRequestDTO);
        clienteRequestDTO.forEach(validator::validate);
        return new ResponseEntity<>(nuevosClientes, HttpStatus.CREATED);
    }


}
