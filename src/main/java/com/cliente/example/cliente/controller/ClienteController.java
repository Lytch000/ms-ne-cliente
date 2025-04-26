    package com.cliente.example.cliente.controller;

    import com.cliente.example.cliente.dominio.Cliente;
    import com.cliente.example.cliente.repository.ClienteRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @RequestMapping("/cliente")
    public class ClienteController {

        @Autowired
        private ClienteRepository clienteRepository;

        @GetMapping("/allClientes")
        public List<Cliente> obtenerTodosLosClientes(){
            return clienteRepository.findAll();
        }

        @GetMapping("/clienteId/{id}")
        public Cliente obtenerClientePorBy(@PathVariable Long id){
            return clienteRepository.findById(id).orElse(null);
        }

        @PostMapping("/crear")
        public ResponseEntity<Cliente> registroCliente(@RequestBody Cliente cliente){
            Cliente clienteSaved = clienteRepository.save(cliente);
            return ResponseEntity.status(201).body(clienteSaved);
        }

        @DeleteMapping("/borrarCliente/{id}")
        public ResponseEntity<String> borrarCliente(@PathVariable long id){
            Cliente cliente = clienteRepository.findById(id).orElse(null);

            if(cliente != null){
                clienteRepository.delete(cliente);
                return ResponseEntity.ok("Cliente eliminado correctamente");
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado");
            }

        }

        @PutMapping("/updateCliente/{id}")
        public ResponseEntity<String> actualizarCliente(@PathVariable long id, @RequestBody Cliente clienteUpdate){
            Cliente cliente = clienteRepository.findById(id).orElse(null);

            if(cliente != null){
                cliente.setNombre(clienteUpdate.getNombre());
                cliente.setEdad(clienteUpdate.getEdad());
                cliente.setEmail(clienteUpdate.getEmail());

                clienteRepository.save(cliente);
                return ResponseEntity.ok("Cliente actualizado correctamente");
            }else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado");
            }
        }

        @PatchMapping("/updateParamsCliente/{id}")
        public ResponseEntity<String> actualizarParametroCliente(@PathVariable long id, @RequestBody Cliente clienteUpdate){
            Cliente cliente = clienteRepository.findById(id).orElse(null);

            //Coentario de prueba

            if(cliente != null){
                if(clienteUpdate.getNombre() != null ){
                    cliente.setNombre(clienteUpdate.getNombre());
                }
                if(clienteUpdate.getEdad() != null ){
                    cliente.setEdad(clienteUpdate.getEdad());
                }
                if(clienteUpdate.getEmail() != null ){
                    cliente.setEmail(clienteUpdate.getEmail());
                }

                clienteRepository.save(cliente);
                return ResponseEntity.ok("Datos actualizados correctamente");
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado");
            }
        }

    }
