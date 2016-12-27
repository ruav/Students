package ru.innopolis.uni.course3.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.innopolis.uni.course3.model.Student;
import ru.innopolis.uni.course3.service.StudentService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 */
@Controller
public class StudentController {

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService service;

    @RequestMapping("/students")
    public String getStudents(Model model){
        model.addAttribute("students", service.getAll());
        return "students";
    }

    @RequestMapping("/students/delete/{studentId}")
    public String deleteStudentById(Model model, @PathVariable Integer studentId){
        service.delete(studentId);
        model.addAttribute("students", service.getAll());
        return "students";
    }

    @RequestMapping("/students/update/{studentId}")
    public String updateStudentById(Model model, @PathVariable Integer studentId){
        Student student = service.get(studentId);
        model.addAttribute("student", student);
        return "editStudent";
    }

    @RequestMapping("/students/create/new")
    public String addStudent(Model model){
        Student student = new Student("", "", "MALE", 1);
        model.addAttribute("student", student);
        return "editStudent";
    }

    @RequestMapping(value = "students/*/students", method = RequestMethod.POST)
    public ModelAndView processEditStudent(@RequestParam Integer id, @RequestParam String name,
        @RequestParam String surname, @RequestParam String sex, @RequestParam Integer groupNumber) throws IOException {

        Student student = new Student(id, name, surname, sex, groupNumber);
        logger.info("StudentServlet:  " + (student.isNew() ? "create of" : "update of") +  student);
        if(student.isNew() ){
            service.add(student);
        } else {
            service.update(student);
        }
        return new ModelAndView("redirect:/students");
    }

}
