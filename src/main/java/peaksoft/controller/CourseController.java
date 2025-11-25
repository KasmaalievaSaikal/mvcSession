package peaksoft.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Course;
import peaksoft.service.CourseService;
import peaksoft.service.InstructorService;
import peaksoft.service.StudentService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;
    private final InstructorService instructorService;
    private final StudentService studentService;

    @GetMapping
    public String getAllCourses(Model model) {
        model.addAttribute("allCourses", courseService.getAllCourses());
        model.addAttribute("allInstructors", instructorService.getAllInstructorsWithoutCourse());
        model.addAttribute("allStudents", studentService.getAllStudentsWithoutCourse());
        return "getAllCourses";
    }

    @GetMapping("/new")
    public String createCourse(Model model) {
        model.addAttribute("newCourse", new Course());
        return "newCourse";
    }

    @PostMapping("/save")
    public String saveCourse(@ModelAttribute("newCourse") Course course) {
        courseService.saveCourse(course);
        return "redirect:/courses";
    }

    @GetMapping("/{courseId}")
    public String getCourseById(@PathVariable("courseId") Long id, Model model) {
        Course course = courseService.getCourseById(id);
        model.addAttribute("course", course);
        return "courseDetails";
    }

    @GetMapping("/edit/{id}")
    public String editCourse(@PathVariable("id") Long id, Model model){
        Course course = courseService.getCourseById(id);
        model.addAttribute("course", course);
        return "editCourse";
    }

    @PostMapping("/{id}/update")
    public String updateCourse(@PathVariable("id") Long id, @ModelAttribute("course") Course course) {
        courseService.updateCourse(id, course);
        return "redirect:/courses";
    }

    @GetMapping("/delete/{id}")
    public String deleteCompany(@PathVariable("id")Long id){
        courseService.deleteCourse(id);
        return "redirect:/courses";
    }

    @PostMapping("/{courseId}/assign-instructor")
    public String assignInstructorToCourse(
            @PathVariable("courseId") Long courseId,
            @RequestParam("instructorId") Long instructorId) {

        instructorService.assignInstructorToCourse(instructorId, courseId);
        return "redirect:/courses";
    }

    @PostMapping("/{courseId}/assign-student")
    public String assignStudentToCourse(
            @PathVariable("courseId") Long courseId,
            @RequestParam("studentId") Long studentId) {

        studentService.assignStudentToCourse(courseId,studentId);
        return "redirect:/courses";
    }



}