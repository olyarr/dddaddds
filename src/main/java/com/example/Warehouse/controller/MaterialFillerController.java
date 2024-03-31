package com.example.Warehouse.controller;

import com.example.Warehouse.exception.MaterialFillerAlreadyExistException;
import com.example.Warehouse.model.MaterialFiller;
import com.example.Warehouse.service.MaterialFillerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/materials_filler")
@PreAuthorize("hasAnyAuthority('ADMIN', 'OPERATOR')")
public class MaterialFillerController {
    private final MaterialFillerService materialFillerService;

    @Autowired
    public MaterialFillerController(MaterialFillerService materialFillerService) {
        this.materialFillerService = materialFillerService;
    }

    @GetMapping()
    public String getMaterialsFiller(Model model){
        model.addAttribute("materialsFiller", materialFillerService.getMaterialsFiller());
        model.addAttribute("count", materialFillerService.getCountMaterialsFiller());
        return "/MaterialsFiller/Index";
    }

    @GetMapping("/new")
    public String getPageAddMaterialFiller(Model model){
        model.addAttribute("materialFiller", new MaterialFiller());
        return "/MaterialsFiller/New";
    }

    @PostMapping()
    public String addMaterialFiller(@Valid MaterialFiller materialFiller, BindingResult bindingResult, Model model) {
        try{
            if (bindingResult.hasErrors())
            {
                model.addAttribute("payload", materialFiller);
                return "/MaterialsFiller/New";
            }

            materialFillerService.addOrUpdateMaterialFiller(materialFiller);
            return "redirect:/materials_filler";
        }
        catch (MaterialFillerAlreadyExistException e) {
            model.addAttribute("payload", materialFiller);
            model.addAttribute("error", e.getMessage());
            return "/MaterialsFiller/New";
        }
    }

    @GetMapping("/{id}/edit")
    public String getPageEditMaterialFiller(Model model, @PathVariable("id") int id){
        model.addAttribute("materialFiller", materialFillerService.getMaterialFiller(id));
        return "/MaterialsFiller/Edit";
    }

    @PostMapping("/edit")
    public String editMaterialFiller(@Valid MaterialFiller materialFiller, BindingResult bindingResult, Model model) {
        try{
            if (bindingResult.hasErrors())
            {
                model.addAttribute("payload", materialFiller);
                return "/MaterialsFiller/Edit";
            }

            materialFillerService.addOrUpdateMaterialFiller(materialFiller);
            return "redirect:/materials_filler";
        }
        catch (MaterialFillerAlreadyExistException e) {
            model.addAttribute("payload", materialFiller);
            model.addAttribute("error", e.getMessage());
            return "/MaterialsFiller/Edit";
        }
    }
}
