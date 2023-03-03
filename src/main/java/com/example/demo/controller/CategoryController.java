package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.Category;
import com.example.demo.repository.CategoryRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/categorie")
public class CategoryController {

	@Autowired
	private CategoryRepository categoryRepo;

	@GetMapping
	public String index(Model model) {
		List<Category> categoryList = categoryRepo.findAll();
		model.addAttribute("categorie", categoryList);
		return "/categories/indexCategory";
	}

	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("category", new Category());
		return "categorie/createCategory";
	}

	@PostMapping("/create")
	public String store(@Valid @ModelAttribute("category") Category formCategory, BindingResult bindingR, Model model) {

		if (bindingR.hasErrors()) {
			return "/categorie/createCategory";
		}

		categoryRepo.save(formCategory);
		return "redirect:/categorie";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("category", categoryRepo.getReferenceById(id));

		return "/categorie/editCategory";
	}

	@PostMapping("/edit/{id}")
	public String update(@Valid @ModelAttribute Category formCategory, BindingResult bindingR, Model model) {
		if (bindingR.hasErrors()) {
			return "/categorie/editCategory";
		}
		categoryRepo.save(formCategory);
		return "redirect:/categorie";
	}

	@PostMapping("delete/{id}")
	public String delete(@PathVariable("id") Integer id, Model model) {
		categoryRepo.deleteById(id);
		return "redirect:/categorie";
	}
}
