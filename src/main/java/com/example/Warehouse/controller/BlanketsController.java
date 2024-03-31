package com.example.Warehouse.controller;

import com.example.Warehouse.model.Blanket;
import com.example.Warehouse.service.BlanketService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/blankets")
@PreAuthorize("hasAnyAuthority('ADMIN', 'OPERATOR')")
public class BlanketsController {
    private final BlanketService blanketService;

    @Autowired
    public BlanketsController(BlanketService blanketService) {
        this.blanketService = blanketService;
    }

    @GetMapping()
    public String getBlankets(Model model, @RequestParam(name = "title", required = false, defaultValue = "") String title){
        model.addAttribute("blankets", blanketService.getBlankets(title));
        model.addAttribute("count",blanketService.getCountBlankets());
        return "/Blankets/Index";
    }

    @GetMapping("/new")
    public String getPageAddBlanket(Model model){
        model.addAttribute("blanket", new Blanket());
        return "/Blankets/New";
    }

    @PostMapping()
    public String addBlanket(@Valid Blanket blanket, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors())
        {
            model.addAttribute("payload", blanket);
            return "/Blankets/New";
        }

        blanketService.addOrUpdateBlanket(blanket);
        return "redirect:/blankets";
    }

    @GetMapping("/{id}/edit")
    public String getPageEditBlanket(Model model, @PathVariable("id") int id){
        model.addAttribute("blanket", blanketService.getBlanket(id));
        return "/Blankets/Edit";
    }

    @PostMapping("/{id}")
    public String editBlanket(@Valid Blanket blanket, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors())
        {
            model.addAttribute("payload", blanket);
            return "/Blankets/Edit";
        }

        blanketService.addOrUpdateBlanket(blanket);
        return "redirect:/blankets";
    }

    @PostMapping("/{id}/delete")
    public String deleteBlanket(@PathVariable("id") int id){
        blanketService.deleteBlanket(id);
        return "redirect:/blankets";
    }
}