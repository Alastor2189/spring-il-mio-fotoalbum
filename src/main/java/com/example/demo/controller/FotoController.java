package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.models.Category;
import com.example.demo.models.Foto;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.FotoRepository;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/photos")

public class FotoController {
	
	private @Autowired FotoRepository fotoRepo;
	
	private @Autowired CategoryRepository categoryRepo;

	@GetMapping
	public String index(@RequestParam(name = "search", required = false) String keyword, Model model) {
		List<Foto> listaFoto;
		if(keyword != null && !keyword.isEmpty()) {
			listaFoto = fotoRepo.findByTitleLikeOrTagLike("%" + keyword + "%", "%" + keyword + "%");
		} else {
			listaFoto = fotoRepo.findAll();
		}
		model.addAttribute("photos", listaFoto);

		return "/photos/indexFoto";
	}

	@GetMapping("/{id}")
	public String show(@PathVariable("id") Integer id, Model model) {
		Optional<Foto> foto = fotoRepo.findById(id);
		model.addAttribute("foto", foto.get());

		return "/photos/showFoto";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("photo", new Foto());

		List<Category> categoryList = categoryRepo.findAll();
		model.addAttribute("categories", categoryList);

		return "/photos/createFoto";
	}

	@PostMapping("/create")
	public String store(@Valid @ModelAttribute("foto") Foto formPhoto, BindingResult bindingCreate, Model model) {

		if (bindingCreate.hasErrors()) {
			return "/photos/createFoto";
		}

		fotoRepo.save(formPhoto);			
		return "redirect:/photos";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("foto", fotoRepo.getReferenceById(id));

		List<Category> categoryList = categoryRepo.findAll();
		model.addAttribute("categories", categoryList);

		return "/photos/edit";
	}

	@PostMapping("/edit/{id}")
	public String update(@Valid @ModelAttribute("foto") Foto formPhoto, BindingResult bindingEdit, Model model) {

		if (bindingEdit.hasErrors()) {
			return "photos/edit";
		}

		fotoRepo.save(formPhoto);
		return "redirect:/photos/"+formPhoto.getId();
	}

	@PostMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id, Model model) {
		fotoRepo.deleteById(id);

		return "redirect:/photos";
	}
	
}
