package com.example.fitlifetech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    
    @GetMapping("/filtrar/id")
    public Rutina buscarRutinaUnicaPorId(@RequestParam(value= "llave") int id) {
        try {
            return service.buscarPorId(id);    
        } catch (Exception e) {
            System.out.println("Error: "+ e.getMessage());
        }
        return null;
    }
    
    
    

    @PostMapping
    public Rutina guardarRutina(@RequestBody Rutina rutina) {
        service.guardarRutina(rutina);
        return rutina;
    }
    
}
