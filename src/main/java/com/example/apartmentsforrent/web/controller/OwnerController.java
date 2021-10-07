package com.example.apartmentsforrent.web.controller;

import com.example.apartmentsforrent.persistence.model.Owner;
import com.example.apartmentsforrent.service.OwnerService;
import com.example.apartmentsforrent.web.converter.OwnerDtoConverter;
import com.example.apartmentsforrent.web.dto.OwnerDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OwnerController {
    private final OwnerDtoConverter ownerDtoConverter;
    private final OwnerService ownerService;

    public OwnerController(OwnerDtoConverter ownerDtoConverter, OwnerService ownerService) {
        this.ownerDtoConverter = ownerDtoConverter;
        this.ownerService = ownerService;
    }

    @GetMapping("/signup")
    public String getRegisterPage(Model model) {
        model.addAttribute("owner", new OwnerDto());
        return "signup";
    }

    @PostMapping("/signup")
    public String registerOwner(@ModelAttribute("owner") OwnerDto ownerDto) {
        Owner owner = ownerDtoConverter.convertToOwner(ownerDto);
        ownerService.saveOwner(owner);
        return "redirect:/";
    }
}
