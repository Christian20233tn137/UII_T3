package com.example.InterfazTareas.Tareas.Controller;

import com.example.InterfazTareas.Tareas.Model.Tarea;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/tareas")
public class TareaViewController {

    private List<Tarea> tareas = new ArrayList<>();
    private long idCounter = 1;

    @GetMapping
    public String mostrarTareas(Model model) {
        model.addAttribute("tareas", tareas);
        model.addAttribute("tarea", new Tarea()); // Para el formulario
        return "tareas";
    }

    @PostMapping("/agregar")
    public String agregarTarea(@ModelAttribute Tarea tarea) {
        tarea.setId(idCounter++);
        tareas.add(tarea);
        return "redirect:/tareas";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarTarea(@PathVariable long id) {
        tareas.removeIf(tarea -> tarea.getId() == id);
        return "redirect:/tareas";
    }

    @GetMapping("/pendientes")
    public String contarPendientes(Model model) {
        long tareasPendientes = tareas.stream().filter(Tarea::isPendiente).count();
        model.addAttribute("tareasPendientes", tareasPendientes);
        return "tareas";
    }

    @GetMapping("/imprimir")
    public String imprimirTareasPendientes(Model model) {
        model.addAttribute("tareasPendientes", tareas.stream().filter(Tarea::isPendiente).toList());
        return "tareas";
    }

    @PostMapping("/limpiar")
    public String limpiarLista() {
        tareas.clear();
        return "redirect:/tareas";
    }

    @PostMapping("/exportar")
    public String exportarTareas() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("tareas.txt"))) {
            for (Tarea tarea : tareas) {
                bw.write(tarea.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/tareas";
    }
}
