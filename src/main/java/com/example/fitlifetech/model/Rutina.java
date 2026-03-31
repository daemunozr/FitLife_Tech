package com.example.fitlifetech.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rutina {
        @NotNull(message="Id no ingresado")
        private int id;

        @NotBlank(message="Rutina sin nombre")
        private String nombre;

        @NotNull(message="Rutina sin nivel")
        private int nivel;

        @NotNull(message="Rutina sin duracion")
        private int duracion_minutos;

        @NotNull(message="Rutina sin tipo")
        private String tipo;

        @NotBlank(message="Rutina sin entrenador")
        private String entrenador;
}
