package com.example.empresa.web;

import com.example.empresa.entity.Empleado;
import com.example.empresa.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {

	@Autowired
	private EmpresaService empresaService;

	 @GetMapping("/listar")
	    public ResponseEntity<List<Empleado>> listarEmpleados() {
	        List<Empleado> empleados = empresaService.listarEmpleados();
	        return ResponseEntity.ok(empleados);
	    }

	    @PostMapping("/listarSueldo")
	    public ResponseEntity<String> buscarSueldo(@RequestParam("dni") String dni) {
	        String sueldo = empresaService.obtenerSueldo(dni);
	        if (sueldo != null) {
	            return ResponseEntity.ok(sueldo);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }

	    @PostMapping("/modificarEmpleados")
	    public ResponseEntity<List<Empleado>> buscarEmpleados(@RequestParam("valor") String valor) {
	        List<Empleado> empleados = empresaService.buscarPorCriterios(valor);
	        if (empleados.isEmpty()) {
	            return ResponseEntity.notFound().build();
	        } else {
	            return ResponseEntity.ok(empleados);
	        }
	    }

	
	    @GetMapping("/editarEmpleado&dni={dni}")
	    public ResponseEntity<Empleado> obtenerEmpleado(@PathVariable String dni) {
	        Empleado empleado = empresaService.obtenerEmpleadoPorDni(dni);
	        if (empleado != null) {
	            return ResponseEntity.ok(empleado);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }

	    @PostMapping("/editado")
	    public ResponseEntity<Void> editarEmpleado(
	            @RequestParam("dniOriginal") String dniOriginal,
	            @RequestBody Empleado empleado) {
	        boolean actualizado = empresaService.editar(empleado, dniOriginal);
	        if (actualizado) {
	            return ResponseEntity.noContent().build();
	        } else {
	            return ResponseEntity.badRequest().build();
	        }
	    }

	    @GetMapping("/volver")
	    public ResponseEntity<String> volver() {
	        return ResponseEntity.ok("API funcionando correctamente");
	    }
}