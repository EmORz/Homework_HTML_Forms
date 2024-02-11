package com.example.Homework_HTML_Forms;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ResortController {
    private ResortRepository resortRepository;
    public ResortController(ResortRepository resortRepository) {
        this.resortRepository = resortRepository;
    }

    @GetMapping("/resorts")
    public String getAllResorts(Model model){
        Iterable<Resort> resorts = resortRepository.findAll();
        model.addAttribute("resorts", resorts);
        return "resorts";
    }

    @GetMapping("/resorts/add")
    public String addResortForm(Model model){
        model.addAttribute("resort", new Resort());
        return "addResort";
    }

    @PostMapping("/resorts")
    public String addResort(@ModelAttribute Resort resort){
        resortRepository.save(resort);
        return "redirect:/resorts";
    }
}
