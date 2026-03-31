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

        public List<Rutina> obtenerRutinasOrdenadasPorId(){
                return repo.obtenerRutinasOrdenadasPorId();
        }

        public List<Rutina> obtenerRutinasOrdenadasPorNombre(){
                return repo.obtenerRutinasOrdenadasPorNombre();
        }

        public List<Rutina> obtenerRutinasOrdenadasPorNivel(){
                return repo.obtenerRutinasOrdenadasPorNivel();
        }

        public List<Rutina> obtenerRutinasOrdenadasPorDuracion(){
                return repo.obtenerRutinasOrdenadasPorDuracion();
        }

        public List<Rutina> obtenerRutinasOrdenadasPorEntrenador(){
                return repo.obtenerRutinasOrdenadasPorEntrenador();
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

        public Rutina buscarPorId(int id) throws Exception{
                Rutina tmp_rutina = repo.buscarPorId(id); 
                if(tmp_rutina == null)
                        throw new Exception("Error: rutina no encontrada por id " + id);
                return tmp_rutina;
        }

        public Rutina buscarPorNombre(String nombre) throws Exception{
                Rutina tmp_rutina = repo.buscarPorNombre(nombre);
                if(tmp_rutina == null)
                        throw new Exception("Error: rutina no encontrada por nombre " + nombre);
                return tmp_rutina;
        }

        public List<Rutina> buscarPorNivel(int nivel) throws Exception{
                List<Rutina> tmp_rutinas = repo.buscarPorNivel(nivel);
                if(tmp_rutinas == null)
                        throw new Exception("Error: rutina no encontrada por nivel " + nivel);
                return tmp_rutinas;
        }

        public List<Rutina> buscarPorDuracion(int duracion) throws Exception{
                List<Rutina> tmp_rutinas = repo.buscarPorDuracion(duracion);
                if(tmp_rutinas == null)
                        throw new Exception("Error: rutina no encontrada por duracion " + duracion);
                return tmp_rutinas;
        }

        public List<Rutina> buscarPorTipo(String tipo) throws Exception{
                List<Rutina> tmp_rutinas = repo.buscarPorTipo(tipo);
                if(tmp_rutinas == null)
                        throw new Exception("Error: rutina no encontrada por tipo " + tipo);
                return tmp_rutinas;
        }

        public List<Rutina> buscarPorEtrenador(String entrenador) throws Exception{
                List<Rutina> tmp_rutinas = repo.buscarPorEntrenador(entrenador);
                if(tmp_rutinas == null)
                        throw new Exception("Error: rutina no encontrada por entrenador " + entrenador);
                return tmp_rutinas;
        }

        public Rutina actualizarRutina(Rutina rutina) throws Exception{
                if(repo.actualizar(rutina) == null)
                        throw new Exception("Error: rutina " + rutina.getNombre() + " con id " + rutina.getId() + " no encontrada para actualizar");

                return rutina;
        }

        public Rutina guardarRutina(Rutina rutina){
                return repo.guardar(rutina);
        }

        public String borrarRutina(int id) throws Exception{
                if(repo.buscarPorId(id) == null)
                        throw new Exception("Error: rutina con id " + id + " no encontrada para borrar");

                repo.eliminar(id);
                return "Rutina eliminada";
        }

        public String borrarRutina(String nombre) throws Exception{
                if(repo.buscarPorNombre(nombre) == null)
                        throw new Exception("Error: rutina con nombre " + nombre + " no encontrada para borrar");

                repo.eliminar(nombre);
                return "Rutina eliminada";
        }
}
