package edu.tienda.core.controllers;

import edu.tienda.core.domain.Client;
import edu.tienda.core.exceptions.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/clients") // Centralizo los endpoints con el nombre "clients"
public class ClientRestController {

    /* Creamos una lista de prueba que emplea el rol de persistencia
        o almacén de clientes en nuestra API
    */
    private List<Client> clients = new ArrayList<>(Arrays.asList(
            new Client("arm", "1234", "Armstrong"),
            new Client("ald", "1234", "Aldrin"),
            new Client("col", "1234", "Collins")
        )
    );

    @GetMapping // Creamos clase de tipo "Wrapper" que devuelve un objeto de tipo ResponseEntity
    public ResponseEntity<?> getClients() { // "wildcard" <?> permite mayor flexibilidad ante un cambio en el cuerpo del método
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/{userName}")
    public ResponseEntity<?> getClient(@PathVariable String userName) {
        return clients.stream().
                filter(client -> client.getUsername().equalsIgnoreCase(userName)) // Filtramos por userName
                .findFirst() // Retornará un opcional de Client
                .map(ResponseEntity::ok) // transformara el “Optional<Cliente>” en un “Optional<ResponseEntity<Cliente>>”
                                        // y lo retornará.
                .orElseThrow(()-> new ResourceNotFoundException("Cliente " + userName + " no encontrado"));
                /*  En el caso de que el opcional de cliente no contenga un objeto, se ejecutará la última línea que provocará la
                excepción. Luego será Spring el encargado de transformar la respuesta del servicio como NOT FOUND.*/
    }

    @PostMapping
    public ResponseEntity<?> addClient(@RequestBody Client client) {
        clients.add(client);

        //Obteniendo URL de servicio: retorna la URL para acceder a este nuevo recurso
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/userName")
                .buildAndExpand(client.getUsername())
                .toUri();


        return ResponseEntity.created(location).body(client);
    }

    @PutMapping
    public ResponseEntity<?> updateClient(@RequestBody Client client) {

        Client foundClient = clients.stream().
                filter(cli -> cli.getUsername().equalsIgnoreCase(client.getUsername())).
                findFirst().orElseThrow();

        foundClient.setPassword(client.getPassword());
        foundClient.setName(client.getName());

        return ResponseEntity.ok(foundClient);
    }

    @DeleteMapping("/{userName}")
    public ResponseEntity deleteClient(@PathVariable String userName) {
        Client foundClient = clients.stream().
                filter(cli-> cli.getUsername().equalsIgnoreCase(userName)).
                findFirst().orElseThrow();
        clients.remove(foundClient);

        return ResponseEntity.noContent().build();
    }

}
