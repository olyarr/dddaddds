package com.example.Warehouse.controller;

import com.example.Warehouse.model.BedLinen;
import com.example.Warehouse.service.BedLinenService;
import com.example.Warehouse.service.MaterialService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/bed_linens")
@PreAuthorize("hasAnyAuthority('ADMIN', 'OPERATOR')")
public class BedLinensController {
    private final BedLinenService bedLinenService;

    private final MaterialService materialService;

    @Autowired
    public BedLinensController(BedLinenService bedLinenService, MaterialService materialService){
        this.bedLinenService = bedLinenService;
        this.materialService = materialService;
    }

    @GetMapping()
    public String getBedLinens(Model model, @RequestParam(name = "title", required = false, defaultValue = "") String title){
        model.addAttribute("bed_linens", bedLinenService.getBedLinens(title));
        model.addAttribute("materials", materialService.getMaterials());
        model.addAttribute("count", bedLinenService.getCountBedLinens());
        return "/BedLinens/Index";
    }

    @GetMapping("/new")
    public String getPageAddBedLinen(Model model){
        model.addAttribute("bedLinen", new BedLinen());
        model.addAttribute("materials", materialService.getMaterials());
        return "/BedLinens/New";
    }

    @PostMapping()
    public String addBedLinen(@Valid BedLinen bedLinen, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors())
        {
            model.addAttribute("payload", bedLinen);
            model.addAttribute("materials", materialService.getMaterials());
            return "/BedLinens/New";
        }

        bedLinenService.addOrUpdateBedLinen(bedLinen);
        return "redirect:/bed_linens";
    }

    @GetMapping("/{id}/edit")
    public String getPageEditBedLinen(Model model, @PathVariable("id") int id){
        model.addAttribute("bedLinen", bedLinenService.getBedLinen(id));
        model.addAttribute("materials", materialService.getMaterials());
        return "/BedLinens/Edit";
    }

    @PostMapping("/edit")
    public String editBedLinen(@Valid BedLinen bedLinen, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors())
        {
            model.addAttribute("payload", bedLinen);
            model.addAttribute("materials", materialService.getMaterials());
            return "/BedLinens/Edit";
        }


        bedLinenService.addOrUpdateBedLinen(bedLinen);
        return "redirect:/bed_linens";
    }

    @PostMapping("/{id}/delete")
    public String deleteBedLinen(@PathVariable("id") int id){
        bedLinenService.deleteBedLinen(id);
        return "redirect:/bed_linens";
    }
}