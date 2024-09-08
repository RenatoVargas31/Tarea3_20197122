package com.app.tarea3_20197122.controller;

import com.app.tarea3_20197122.entity.Employee;
import com.app.tarea3_20197122.entity.Job;
import com.app.tarea3_20197122.repository.DepartmentRepository;
import com.app.tarea3_20197122.repository.EmployeeRepository;
import com.app.tarea3_20197122.repository.JobRepository;
import com.app.tarea3_20197122.repository.LocationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    final EmployeeRepository employeeRepository;
    final DepartmentRepository departmentRepository;
    final LocationRepository locationRepository;
    final JobRepository jobRepository;
    public EmployeeController(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository, LocationRepository locationRepository, JobRepository jobRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.locationRepository = locationRepository;
        this.jobRepository = jobRepository;
    }

    @GetMapping("/listar")
    public String listarEmpleados(Model model) {
        List<Employee> empleados = employeeRepository.findAll();

        for (Employee employee : empleados) {
            employee.setDepartment(departmentRepository.findByDepartmentId(employee.getDepartmentId()));
            employee.getDepartment().setLocation(locationRepository.findByLocationId(employee.getDepartment().getLocationId()));
        }
        //Enviar la lista de empleados a la vista
        model.addAttribute("empleados", empleados);

        return "employee/lista";
    }

    @GetMapping("/info")
    public String infoEmpleado(Integer id, Model model) {
        Employee empleado = employeeRepository.findByEmployeeId(id);
        empleado.setJob(jobRepository.findByJobId(empleado.getJobId()));
        empleado.setDepartment(departmentRepository.findByDepartmentId(empleado.getDepartmentId()));
        empleado.getDepartment().setLocation(locationRepository.findByLocationId(empleado.getDepartment().getLocationId()));
        model.addAttribute("empleado", empleado);
        //Lista de trabajos
        List<Job> trabajos = jobRepository.findAll();
        model.addAttribute("trabajos", trabajos);

        return "employee/info";
    }
    //Borrar empleado por id, enviar mensaje de éxito o fracaso por errores internos de la base de datos
    @GetMapping("/delete")
    public String deleteEmployee(Integer id, RedirectAttributes redirectAttributes) {
        try {
            employeeRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("mensaje", "Se borró el empleado");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensaje", "No se puede borrar el empleado");
        }
        //Redirigir a la lista de empleados cambiando el link después de eliminar con el mensaje de confirmación
        return "redirect:/employee/listar";
    }
}
