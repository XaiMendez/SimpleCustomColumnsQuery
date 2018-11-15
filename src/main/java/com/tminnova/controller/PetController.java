package com.tminnova.controller;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tminnova.model.Pets;
import com.tminnova.service.PetsRepository;

@RestController
@RequestMapping("pets")
public class PetController {

	@Autowired
	private PetsRepository repository;

	@GetMapping("/")
	public List<Pets> getAllPets() {
		return repository.findAll();
	}

	@GetMapping("/{id}")
	public Pets getPetById(@PathVariable("id") ObjectId id) {
		return repository.findBy_id(id);
	}

}
