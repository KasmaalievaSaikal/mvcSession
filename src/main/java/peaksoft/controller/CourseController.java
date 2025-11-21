package peaksoft.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Course;
import peaksoft.service.CourseService;
import peaksoft.service.InstructorService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;
    private final InstructorService instructorService;

    @GetMapping
    public String getAllCourses(Model model) {
        model.addAttribute("allCourses", courseService.getAllCourses());
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


    @PostMapping("/assign/{courseId}")
    public String assignInstructor(@PathVariable ("courseId") Long courseId,
                                   @RequestParam("instructorId") Long instructorId){
        courseService.assignCourseToInstructor(courseId, instructorId);
        return "redirect:/courses";


    }


}
