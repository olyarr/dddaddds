package com.example.Warehouse.controller;

import com.example.Warehouse.exception.MaterialCoverAlreadyExistException;
import com.example.Warehouse.model.MaterialCover;
import com.example.Warehouse.model.MaterialFiller;
import com.example.Warehouse.service.MaterialCoverService;
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
@RequestMapping("/materials_cover")
@PreAuthorize("hasAnyAuthority('ADMIN', 'OPERATOR')")
public class MaterialCoverController {
    private final MaterialCoverService materialCoverService;

    @Autowired
    public MaterialCoverController(MaterialCoverService materialCoverService) {
        this.materialCoverService = materialCoverService;
    }

    @GetMapping()
    public String getMaterialsCover(Model model){
        model.addAttribute("materialsCover", materialCoverService.getMaterialsCover());
        model.addAttribute("count", materialCoverService.getCountMaterialsCover());
        return "/MaterialsCover/Index";
    }

    @GetMapping("/new")
    public String getPageAddMaterialCover(Model model){
        model.addAttribute("materialCover", new MaterialFiller());
        return "/MaterialsCover/New";
    }

    @PostMapping()
    public String addMaterialCover(@Valid MaterialCover materialCover, BindingResult bindingResult, Model model) {
        try{
            if (bindingResult.hasErrors())
            {
                model.addAttribute("payload", materialCover);
                return "/MaterialsCover/New";
            }

            materialCoverService.addOrUpdateMaterialCover(materialCover);
            return "redirect:/materials_cover";
        }
        catch (MaterialCoverAlreadyExistException e) {
            model.addAttribute("payload", materialCover);
            model.addAttribute("error", e.getMessage());
            return "/MaterialsCover/New";
        }
    }

    @GetMapping("/{id}/edit")
    public String getPageEditMaterialCover(Model model, @PathVariable("id") int id){
        model.addAttribute("materialCover", materialCoverService.getMaterialCover(id));
        return "/MaterialsCover/Edit";
    }

    @PostMapping("/edit")
    public String editMaterialCover(@Valid MaterialCover materialCover, BindingResult bindingResult, Model model) {
        try{
            if (bindingResult.hasErrors())
            {
                model.addAttribute("payload", materialCover);
                return "/MaterialsCover/Edit";
            }

            materialCoverService.addOrUpdateMaterialCover(materialCover);
            return "redirect:/materials_cover";
        }
        catch (MaterialCoverAlreadyExistException e) {
            model.addAttribute("payload", materialCover);
            model.addAttribute("error", e.getMessage());
            return "/MaterialsCover/Edit";
        }
    }
}
