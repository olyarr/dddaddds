package com.example.Warehouse.controller;

import com.example.Warehouse.model.Pillow;
import com.example.Warehouse.service.PillowService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pillows")
@PreAuthorize("hasAnyAuthority('ADMIN', 'OPERATOR')")
public class PillowsController {
    private final PillowService pillowService;

    @Autowired
    public PillowsController(PillowService pillowService) {
        this.pillowService = pillowService;
    }

    @GetMapping()
    public String getPillows(Model model, @RequestParam(name = "title", required = false, defaultValue = "") String title){
        model.addAttribute("pillows", pillowService.getPillows(title));
        model.addAttribute("count", pillowService.getCountPillows());
        return "/Pillows/Index";
    }

    @GetMapping("/new")
    public String getPadeAddPillow(Model model){
        model.addAttribute("pillow", new Pillow());
        return "/Pillows/New";
    }

    @PostMapping()
    public String addPillow(@Valid Pillow pillow, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors())
        {
            model.addAttribute("payload", pillow);
            return "/Pillows/New";
        }

        pillowService.addOrUpdatePillow(pillow);
        return "redirect:/pillows";
    }

    @GetMapping("/{id}/edit")
    public String getPageEditPillow(Model model, @PathVariable("id") int id){
        model.addAttribute("pillow", pillowService.getPillow(id));
        return "/Pillows/Edit";
    }

    @PostMapping("/{id}")
    public String editPillow(@Valid Pillow pillow, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors())
        {
            model.addAttribute("payload", pillow);
            return "/Pillows/Edit";
        }

        pillowService.addOrUpdatePillow(pillow);
        return "redirect:/pillows";
    }

    @PostMapping("/{id}/delete")
    public String deletePillow(@PathVariable("id") int id){
        pillowService.deletePillow(id);
        return "redirect:/pillows";
    }
}