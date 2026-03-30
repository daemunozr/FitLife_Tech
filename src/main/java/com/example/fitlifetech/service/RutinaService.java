package com.example.fitlifetech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fitlifetech.model.Rutina;
import com.example.fitlifetech.repository.RutinaRepository;

@Service
public class RutinaService {

        @Autowired
        private RutinaRepository repo;
        
        public List<Rutina> obtenerRutinas(){
                return repo.obtenerRutinas();
        }

        public List<Rutina> obtenerRutinasOrdenadas(String criterio) throws Exception{
            try {
                switch (criterio) {
                        case ("id")             -> repo.obtenerRutinasOrdenadasPorId();
                        case ("nombre")         -> repo.obtenerRutinasOrdenadasPorNombre();
                        case ("nivel")          -> repo.obtenerRutinasOrdenadasPorNivel();
                        case ("duracion")       -> repo.obtenerRutinasOrdenadasPorDuracion();
                        case ("entrenador")     -> repo.obtenerRutinasOrdenadasPorEntrenador();
                        default -> throw new Exception("criterio " + criterio + " no valido");
                }   
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            return null;
        }

        public List<Rutina> obtenerRutinasCortas(){
                return repo.obtenerRutinasCortas();
        }

        public List<Rutina> obtenerRutinasLargas(){
                return repo.obtenerRutinasLargas();
        }

        public List<Rutina> obtenerRutinasFaciles(){
                return repo.obtenerRutinasFaciles();
        }

        public List<Rutina> obtenerRutinasDificiles(){
                return repo.obtenerRutinasDificiles();
        }

        public <E> Rutina buscarPorIdentificador(String identificador,E element) throws Exception{
                
                try{
                        switch (identificador){
                        case ("id")     -> repo.buscarPorId((int)element);
                        case("nombre")  -> repo.buscarPorNombre((String)element);
                        default -> throw new Exception("identificador " + identificador + " invalido");
                        }
                }catch (ClassCastException e){
                        System.out.println("Error: elemento " + e.getMessage());
                }
                return null;
        }

        public <E> List<Rutina> buscarPorCriterio(String criterio, E element) throws Exception{

                try {
                        switch (criterio) {
                            case ("nivel")      -> repo.buscarPorNivel((int)element);
                            case ("duracion")   -> repo.buscarPorDuracion((int)element);
                            case ("tipo")       -> repo.buscarPorTipo((String)element);
                            case ("entrenador") -> repo.buscarPorEntrenador((String)element);
                            default             -> throw new Exception("criterio " + criterio + " invalido");
                        }
                } catch (ClassCastException e) {
                        System.out.println("Error: elemento " + e.getMessage());
                }
                return null;
        }

        public Rutina actualizarRutina(Rutina rutina) throws Exception{
                if(repo.actualizar(rutina) == null)
                        throw new Exception("Error: rutina no encontrada para actualizar");

                return rutina;
        }

        public String borrarRutina(int id) throws Exception{
                if(repo.buscarPorId(id) == null)
                        throw new Exception("Error: rutina no encontrada para actualizar");

                repo.eliminar(id);
                return "Rutina eliminada";
        }

        public String borrarRutina(String nombre) throws Exception{
                if(repo.buscarPorNombre(nombre) == null)
                        throw new Exception("Error: rutina no encontrada para actualizar");

                repo.eliminar(nombre);
                return "Rutina eliminada";
        }
}
