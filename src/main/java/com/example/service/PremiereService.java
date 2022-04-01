package com.example.service;

import com.example.annotations.MessageSend;
import com.example.model.Premiere;
import com.example.repositories.PremiereRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PremiereService {

    private final PremiereRepository premiereRepository;

    public List<Premiere> findAll() {
        return premiereRepository.findAll();
    }

    public Premiere getPremiereById(Integer id) {
        return premiereRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Premiere with id " + id + " not found"));
    }

    public Premiere getPremiereByName(String nameOfThePremiere) {
        return premiereRepository.findByNameOfThePremiere(nameOfThePremiere).orElseThrow(() -> new EntityNotFoundException("Premiere with name " + nameOfThePremiere + " not found"));
    }

    @Transactional
    @MessageSend
    public Premiere addPremiere(String nameOfThePremiere, String description, int ageCategory, int count) {//описание премьеры
        return premiereRepository.save(new Premiere(nameOfThePremiere, description, ageCategory, count));
    }

    @Transactional
    @MessageSend
    public void deletePremiere(Integer id) {
        premiereRepository.deleteById(id);
    }

    @Transactional
    @MessageSend
    public void deletePremiere(String nameOfThePremiere) {
        premiereRepository.deleteByNameOfThePremiere(nameOfThePremiere);
    }

    @Transactional
    @MessageSend
    public Premiere updatePremiere(Integer id, String newNameOfThePremiere, String description, int ageCategory, int count) {
        Premiere premiere = getPremiereById(id);
        premiere.setNameOfThePremiere(newNameOfThePremiere);
        premiere.setDescription(description);
        premiere.setAgeCategory(ageCategory);
        premiere.setQuantityOfSeats(count);
        return premiereRepository.save(premiere);
    }

    @Transactional
    public Premiere bookTickets(Integer id, Integer count) {
        Premiere premiere = getPremiereById(id);
        premiere.setQuantityOfSeats(premiere.getQuantityOfSeats() - count);
        premiereRepository.save(premiere);
        return premiere;
    }

    public void printPremiers() {
        for (Premiere premiere : findAll()) {
            System.out.println(premiere.getNameOfThePremiere());
        }
        System.out.println("****************************");
    }

    public void printFullDescription(String nameOfThePremiere) {
        System.out.println(getPremiereByName(nameOfThePremiere));
        System.out.println("****************************");
    }

}
