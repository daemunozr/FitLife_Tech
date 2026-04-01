package com.example.fitlifetech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.fitlifetech.model.Rutina;
import com.example.fitlifetech.service.RutinaService;


@RestController
@RequestMapping("/api/v1/rutinas")
public class RutinaController {

    @Autowired
    private RutinaService service;

    @GetMapping
    public List<Rutina> listarRutinas(){
        return service.obtenerRutinas();
    }

    @GetMapping("/orden")
    public List<Rutina> listarRutinasOrdenadasPorCriterio(@RequestParam(value = "criterio", required=true) String criterio){
        switch (criterio){
            case("id"):
                return service.obtenerRutinasOrdenadasPorId();
            case("nombre"): 
                return service.obtenerRutinasOrdenadasPorNombre();
            case("nivel"):
                return service.obtenerRutinasOrdenadasPorNivel();
            case("duracion"):
                return service.obtenerRutinasOrdenadasPorDuracion();
            case("entrenador"):
                return service.obtenerRutinasOrdenadasPorEntrenador();
        }
        return null;
    }
    
    @GetMapping("/buscarId")
    public Rutina buscarRutinaPorId(@RequestParam(value= "llave", required=true) int id) {
        try {
            return service.buscarPorId(id);    
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @GetMapping("/buscarNombre")
    public Rutina buscarRutinaPorNombre(@RequestParam(value= "nombre", required=true) String nombre) {
        try {
            return service.buscarPorNombre(nombre);    
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    @GetMapping("/filtrarNivel")
    public List<Rutina> filtrarRutinaPorNivel(@RequestParam(value= "valor", required=true) int nivel) {
        try {
            return service.filtrarPorNivel(nivel);    
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @GetMapping("/filtrarDuracion")
    public List<Rutina> filtrarRutinaPorDuracion(@RequestParam(value= "duracion", required=true) int duracion) {
        try {
            return service.filtrarPorDuracion(duracion);    
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @GetMapping("/filtrarTipo")
    public List<Rutina> filtrarRutinaPorTipo(@RequestParam(value= "tipo", required=true) String tipo) {
        try {
            return service.filtrarPorTipo(tipo);    
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @GetMapping("/filtrarEntrenador")
    public List<Rutina> filtrarRutinaPorEntrenador(@RequestParam(value= "nombre", required=true) String entrenador) {
        try {
            return service.filtrarPorTipo(entrenador);    
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    @PostMapping
    public Rutina guardarRutina(@RequestBody Rutina rutina) {
        service.guardarRutina(rutina);
        return rutina;
    }

    @PutMapping
    public Rutina actualizarRutina(@RequestBody Rutina rutina) {
        
        try {
            service.actualizarRutina(rutina);
            return rutina;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @DeleteMapping("/borrarId/{id}")
    public String borrarRutinaPorId(@PathVariable int id){
        try {
            return service.borrarRutina(id);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @DeleteMapping("/borrarNombre/{nombre}")
    public String borrarRutinaPorNombre(@PathVariable String nombre){
        try {
            return service.borrarRutina(nombre);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    
}
