package com.example.Warehouse.controller;

import com.example.Warehouse.exception.MaterialAlreadyExistException;
import com.example.Warehouse.exception.MaterialUsedException;
import com.example.Warehouse.model.Material;
import com.example.Warehouse.service.MaterialService;
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
@RequestMapping("/materials")
@PreAuthorize("hasAnyAuthority('ADMIN', 'OPERATOR')")
public class MaterialController {
    private final MaterialService materialService;

    @Autowired
    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    @GetMapping()
    public String getMaterials(Model model){
        model.addAttribute("materials", materialService.getMaterials());
        model.addAttribute("count", materialService.getCountMaterials());
        return "/Materials/Index";
    }

    @GetMapping("/new")
    public String getPageAddMaterial(Model model){
        model.addAttribute("material", new Material());
        return "/Materials/New";
    }

    @PostMapping()
    public String addMaterial(@Valid Material material, BindingResult bindingResult, Model model) {
        try{
            if (bindingResult.hasErrors())
            {
                model.addAttribute("payload", material);
                return "/Materials/New";
            }

            materialService.addOrUpdateMaterial(material);
            return "redirect:/materials";
        }
        catch (MaterialAlreadyExistException e) {
            model.addAttribute("payload", material);
            model.addAttribute("error", e.getMessage());
            return "/Materials/New";
        }
    }

    @GetMapping("/{id}/edit")
    public String getPageEditMaterial(Model model, @PathVariable("id") int id){
        model.addAttribute("material", materialService.getMaterial(id));
        return "/Materials/Edit";
    }

    @PostMapping("/edit")
    public String editMaterial(@Valid Material material, BindingResult bindingResult, Model model) {
        try{
            if (bindingResult.hasErrors())
            {
                model.addAttribute("payload", material);
                return "/Materials/Edit";
            }

            materialService.addOrUpdateMaterial(material);
            return "redirect:/materials";
        }
        catch (MaterialAlreadyExistException e) {
            model.addAttribute("payload", material);
            model.addAttribute("error", e.getMessage());
            return "/Materials/Edit";
        }
    }

    @PostMapping("/{id}/delete")
    public String deleteMaterial(@PathVariable("id") int id, Model model){
        try{
            materialService.deleteMaterial(id);
            return "redirect:/materials";
        }
        catch (MaterialUsedException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("materials", materialService.getMaterials());
            return "/Materials/Index";
        }
    }
}
