package com.example.fitlifetech.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.fitlifetech.model.Rutina;
import com.example.fitlifetech.service.RutinaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/rutinas")
public class RutinaController {

    @Autowired
    private RutinaService service;

    @GetMapping
    public ResponseEntity<List<Rutina>> listarRutinas(){
        return ResponseEntity.ok(service.obtenerRutinas());
    }

    @GetMapping("/orden")
    public ResponseEntity<?> listarRutinasOrdenadasPorCriterio(@RequestParam(value = "criterio", required=true) String criterio){
        switch (criterio){
            case("id"):
                return ResponseEntity.ok(service.obtenerRutinasOrdenadasPorId());
            case("nombre"): 
                return ResponseEntity.ok(service.obtenerRutinasOrdenadasPorNombre());
            case("nivel"):
                return ResponseEntity.ok(service.obtenerRutinasOrdenadasPorNivel());
            case("duracion"):
                return ResponseEntity.ok(service.obtenerRutinasOrdenadasPorDuracion());
            case("entrenador"):
                return ResponseEntity.ok(service.obtenerRutinasOrdenadasPorEntrenador());
            default:
                return ResponseEntity.badRequest().body("Error: el criterio \"" + criterio + "\" no es valido");
        }
        //return ResponseEntity.badRequest().body("Error: query no soportado");
    }
    
    @GetMapping("/buscarId")
    public ResponseEntity<?> buscarRutinaPorId(@RequestParam(value= "llave", required=true) int id) {
        try {
            return ResponseEntity.ok(service.buscarPorId(id));    
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/buscarNombre")
    public ResponseEntity<?> buscarRutinaPorNombre(@RequestParam(value= "nombre", required=true) String nombre) {
        try {
            return ResponseEntity.ok(service.buscarPorNombre(nombre));    
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    
    @GetMapping("/filtrarNivel")
    public ResponseEntity<?> filtrarRutinaPorNivel(@RequestParam(value= "valor", required=true) int nivel) {
        try {
            return ResponseEntity.ok(service.filtrarPorNivel(nivel));    
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/filtrarDuracion")
    public ResponseEntity<?> filtrarRutinaPorDuracion(@RequestParam(value= "duracion", required=true) int duracion) {
        try {
            return ResponseEntity.ok(service.filtrarPorDuracion(duracion));    
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/filtrarTipo")
    public ResponseEntity<?> filtrarRutinaPorTipo(@RequestParam(value= "tipo", required=true) String tipo) {
        try {
            return ResponseEntity.ok(service.filtrarPorTipo(tipo));    
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/filtrarEntrenador")
    public ResponseEntity<?> filtrarRutinaPorEntrenador(@RequestParam(value= "nombre", required=true) String entrenador) {
        try {
            return ResponseEntity.ok(service.filtrarPorTipo(entrenador));    
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    
    @PostMapping
    public ResponseEntity<?> guardarRutina(@Valid @RequestBody Rutina rutina) {
        try {
            service.guardarRutina(rutina);
            return ResponseEntity.status(HttpStatus.CREATED).body(rutina);    
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        
    }

    @PutMapping
    public ResponseEntity<?> actualizarRutina(@Valid @RequestBody Rutina rutina) {
        
        try {
            service.actualizarRutina(rutina);
            return ResponseEntity.ok(rutina);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/borrarId/{id}")
    public ResponseEntity<?> borrarRutinaPorId(@PathVariable int id){
        try {
            String ok_msg = service.borrarRutina(id);
            return ResponseEntity.ok(ok_msg);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/borrarNombre/{nombre}")
    public ResponseEntity<?> borrarRutinaPorNombre(@PathVariable String nombre){
        try {
            String ok_msg = service.borrarRutina(nombre);
            return ResponseEntity.ok(ok_msg);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> manejarErroresValidacion(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errores.put(error.getField(), error.getDefaultMessage());
        }
        return errores;
    }
}
