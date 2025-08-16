package com.Vinoteca.AppVinos.controller;


import com.Vinoteca.AppVinos.dto.VinoDTO;
import com.Vinoteca.AppVinos.models.Vinos;
import com.Vinoteca.AppVinos.service.VinoService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.Vinoteca.AppVinos.dto.VinoDTO;
import java.util.List;
import java.util.stream.Collectors;


@CrossOrigin(origins = "http://localhost:9002")
@RestController
@RequestMapping("/api/vinos")
public class VinoController {

        @Autowired
        private VinoService vinoService;

        @GetMapping("/listadoVinos")
        public List<VinoDTO> listarTodos() {
            List<Vinos> vinos = vinoService.listarTodos();
            return vinos.stream()
                    .map(VinoDTO::new)
                    .collect(Collectors.toList());
        }

        @GetMapping("/{id}")
        public ResponseEntity<?> obtenerPorId(@PathVariable Long id){
            return vinoService.buscarPorId(id)
                    .map(vino -> ResponseEntity.ok(new VinoDTO(vino)))
                    .orElse(ResponseEntity.notFound().build());
        }

        @GetMapping("/buscarPorNombre")
        public ResponseEntity<?> buscarPorNombre(@RequestParam String nombre){
            return vinoService.buscarPorNombre(nombre)
                    .map(vino -> ResponseEntity.ok(new VinoDTO(vino)))
                    .orElse(ResponseEntity.notFound().build());
        }

        @PostMapping("/agregarProductos")
        public ResponseEntity<?> agregarProductos(@RequestBody Vinos vino) {
            if (vino.getId() != null) {
                return ResponseEntity.badRequest().body("No se debe especificar un ID para crear un nuevo producto.");
            }
            try {
                Vinos vinoGuardado = vinoService.guardar(vino);
                return ResponseEntity.ok(new VinoDTO(vinoGuardado));
            } catch (IllegalArgumentException v) {
                return ResponseEntity.badRequest().body(v.getMessage());
            }
        }

        @PutMapping("/editaProducto/{id}")
        public ResponseEntity<?> editarProducto(@RequestBody Vinos vino, @PathVariable Long id) {
            try {
                if (vino.getId() == null || !vino.getId().equals(id)) {
                    return ResponseEntity.badRequest().body("El ID del producto en la URL no coincide con el ID del cuerpo de la petición.");
                }
                Vinos vinoActualizado = vinoService.actualizar(id, vino);
                return ResponseEntity.ok(new VinoDTO(vinoActualizado));
            } catch (RuntimeException v) {
                if (v instanceof IllegalArgumentException) {
                    return ResponseEntity.badRequest().body(v.getMessage());
                } else {
                    return ResponseEntity.notFound().build();
                }
            }
        }

        @DeleteMapping("/eliminarProducto/{id}")
        public ResponseEntity<?> eliminarProducto(@PathVariable Long id){
            vinoService.eliminar(id);
            return ResponseEntity.ok().build();
        }
}

