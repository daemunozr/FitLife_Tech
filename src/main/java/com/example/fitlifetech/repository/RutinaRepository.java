package com.example.fitlifetech.repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.example.fitlifetech.model.Rutina;
import com.example.fitlifetech.model.TipoRutina;

import lombok.NoArgsConstructor;;

@NoArgsConstructor
@Repository
public class RutinaRepository {

        private final List<Rutina> rutinas = new ArrayList<>();

        public List<Rutina> obtenerRutinas(){
                return rutinas;
        }

        public List<Rutina> obtenerRutinasCortas(){
                int duracion = Integer.MAX_VALUE;
                for(Rutina rutina: rutinas){
                        if(rutina.getDuracion_minutos() < duracion){
                                duracion = rutina.getDuracion_minutos();
                        }
                }
                return buscarPorDuracion(duracion);
        }

        public List<Rutina> obtenerRutinasLargas(){
                int duracion = Integer.MIN_VALUE;
                for(Rutina rutina: rutinas){
                        if(rutina.getDuracion_minutos() > duracion){
                                duracion = rutina.getDuracion_minutos();
                        }
                }
                return buscarPorDuracion(duracion);
        }

        public List<Rutina> obtenerRutinasFaciles(){
                int nivel = 5;
                for(Rutina rutina: rutinas){
                        if(rutina.getNivel() < nivel)
                                nivel = rutina.getNivel();
                }
                return buscarPorNivel(nivel);
        }

        public List<Rutina> obtenerRutinasDificiles(){
                int nivel = 1;
                for(Rutina rutina: rutinas){
                        if(rutina.getNivel() > nivel)
                                nivel = rutina.getNivel();
                }
                return buscarPorNivel(nivel);
        }

        public List<Rutina> obtenerRutinasOrdenadasPorId(){
                List<Rutina> tmp_rutinas = new ArrayList<>(rutinas);
                tmp_rutinas.sort(Comparator.comparing(Rutina::getId));
                return tmp_rutinas;
        }

        public List<Rutina> obtenerRutinasOrdenadasPorNombre(){
                List<Rutina> tmp_rutinas = new ArrayList<>(rutinas);
                tmp_rutinas.sort(Comparator.comparing(Rutina::getNombre));
                return tmp_rutinas;
        }

        public List<Rutina> obtenerRutinasOrdenadasPorNivel(){
                List<Rutina> tmp_rutinas = new ArrayList<>(rutinas);
                tmp_rutinas.sort(Comparator.comparing(Rutina::getNivel));
                return tmp_rutinas;
        }

        public List<Rutina> obtenerRutinasOrdenadasPorDuracion(){
                List<Rutina> tmp_rutinas = new ArrayList<>(rutinas);
                tmp_rutinas.sort(Comparator.comparing(Rutina::getDuracion_minutos));
                return tmp_rutinas;
        }

        public List<Rutina> obtenerRutinasOrdenadasPorEntrenador(){
                List<Rutina> tmp_rutinas = new ArrayList<>(rutinas);
                tmp_rutinas.sort(Comparator.comparing(Rutina::getPersonal_trainer));
                return tmp_rutinas;
        }

        public Rutina buscarPorId(int id){
                for(Rutina rutina: rutinas){
                        if(rutina.getId() == id)
                                return rutina;
                }
                return null;
        }

        public Rutina buscarPorNombre(String nombre){
                for(Rutina rutina: rutinas){
                        if(rutina.getNombre().equals(nombre))
                                return rutina;
                }
                return null;
        }

        public List<Rutina> buscarPorNivel(int nivel){
                return rutinas.
                        stream().filter(rutina -> rutina.getNivel() == nivel)
                        .collect(Collectors.toList());
        }

        public List<Rutina> buscarPorDuracion(int duracion_minutos){
                return rutinas.
                        stream().filter(rutina -> rutina.getDuracion_minutos() == duracion_minutos)
                        .collect(Collectors.toList());
        }

        public List<Rutina> buscarPorTipo(String tipo){
                return rutinas.
                        stream().filter(rutina -> rutina.getTipo().equals(tipo))
                        .collect(Collectors.toList());
        }

        public List<Rutina> buscarPorEntrenador(String entrenaador){
                return rutinas.
                        stream().filter(rutina -> rutina.getPersonal_trainer().equals(entrenaador))
                        .collect(Collectors.toList());
        }

        public Rutina actualizar(Rutina rutina){

                int posicion = 0;
                for(Rutina tmp_rutina: rutinas){
                        if(tmp_rutina.getId() == rutina.getId()){
                                rutinas.set(posicion, rutina);
                                return rutina;
                        }
                        posicion++;
                }
                return null;
        }

        public void eliminar(int id){
                Rutina tmp_rutina = buscarPorId(id);
                if(tmp_rutina != null)
                        rutinas.remove(tmp_rutina);
        }
        
}
