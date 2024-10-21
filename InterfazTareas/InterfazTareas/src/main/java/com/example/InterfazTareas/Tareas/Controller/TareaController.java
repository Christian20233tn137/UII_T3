package com.example.InterfazTareas.Tareas.Controller;

import com.example.InterfazTareas.Tareas.Model.Tarea;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {

    private List<Tarea> tareas = new ArrayList<>();
    private long idCounter = 1;

    @GetMapping("/all")
    public List<Tarea> findAll() {
        return tareas;
    }

    @GetMapping("/{id}")
    public Tarea findById(@PathVariable Long id) {
        return tareas.stream()
                .filter(tarea -> tarea.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    public Tarea register(@RequestBody Tarea tarea) {
        tarea.setId(idCounter++);
        tareas.add(tarea);
        return tarea;
    }

    @PutMapping
    public Tarea update(@RequestBody Tarea tarea) {
        for (Tarea t : tareas) {
            if (t.getId() == tarea.getId()) {
                t.setNombre(tarea.getNombre());
                t.setDescripcion(tarea.getDescripcion());
                t.setFecha(tarea.getFecha());
                t.setPendiente(tarea.isPendiente());
                return t;
            }
        }
        return null; // Si no encuentra la tarea para actualizar
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        tareas.removeIf(tarea -> tarea.getId() == id);
        return "Tarea eliminada";
    }
}
