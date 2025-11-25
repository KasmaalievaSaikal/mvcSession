package peaksoft.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Lesson;
import peaksoft.service.LessonService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/lessons")
public class LessonController {

    private final LessonService lessonService;

    @GetMapping
    public String getAllLessons(Model model) {
        model.addAttribute("allLessons", lessonService.getAllLessons());
        return "getAllLessons";
    }

    @GetMapping("/new")
    public String createLesson(Model model) {
        model.addAttribute("newLesson", new Lesson());
        return "newLesson";
    }

    @PostMapping("/save")
    public String saveLesson(@ModelAttribute("newLesson") Lesson lesson) {
        lessonService.saveLesson(lesson);
        return "redirect:/lessons";
    }

    @GetMapping("/{lessonId}")
    public String getLessonById(@PathVariable("lessonId") Long id, Model model) {
        Lesson lesson = lessonService.getLessonById(id);
        model.addAttribute("lesson", lesson);
        return "lessonDetails";
    }

    @GetMapping("/edit/{id}")
    public String updateLesson(@PathVariable("id") Long id, Model model) {
        Lesson lesson = lessonService.getLessonById(id);
        model.addAttribute("lesson", lesson);
        return "editLesson";
    }

    @PostMapping("/{id}/update")
    public String updateLesson(@PathVariable("id") Long id, @ModelAttribute("lesson") Lesson lesson) {
        lessonService.updateLesson(id, lesson);
        return "redirect:/lessons";
    }

    @GetMapping("/delete/{id}")
    public String deleteLesson(@PathVariable("id") Long id) {
        lessonService.deleteLesson(id);
        return "redirect:/lessons";
    }
}
