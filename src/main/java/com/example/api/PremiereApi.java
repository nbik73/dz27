package com.example.api;

import com.example.model.Premiere;
import com.example.service.PremiereService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/premiers")
@RequiredArgsConstructor
public class PremiereApi {

    private final PremiereService premiereService;

    @GetMapping
    public List<Premiere> getAll() {
        return premiereService.findAll();
    }

    @GetMapping("{id}")
    public Premiere getById(@PathVariable Integer id) {
        return premiereService.getPremiereById(id);
    }

    @GetMapping("name/{name}")
    public Premiere getByName(@PathVariable String name) {
        return premiereService.getPremiereByName(name);
    }

    @PostMapping
    public Premiere save(@RequestBody Premiere premiere) {
        return premiereService.addPremiere(premiere.getNameOfThePremiere(), premiere.getDescription(), premiere.getAgeCategory(), premiere.getQuantityOfSeats());
    }

    @PutMapping("{id}")
    public Premiere update(@PathVariable Integer id, @RequestBody Premiere premiere) {
        return premiereService.updatePremiere(id, premiere.getNameOfThePremiere(), premiere.getDescription(), premiere.getAgeCategory(), premiere.getQuantityOfSeats());
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Integer id) {
        premiereService.deletePremiere(id);
    }

    @DeleteMapping("name/{name}")
    public void deleteByName(@PathVariable String name) {
        premiereService.deletePremiere(name);
    }

    @PatchMapping("{id}/bookTickets")
    public Premiere bookTickets(@PathVariable Integer id, @RequestParam(defaultValue = "1") Integer count) {
        return premiereService.bookTickets(id, count);
    }
}
