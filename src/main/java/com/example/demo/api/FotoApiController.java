package com.example.demo.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Foto;
import com.example.demo.repository.FotoRepository;

@RestController
@CrossOrigin
@RequestMapping("/api/photos")
public class FotoApiController {

	@Autowired
	private FotoRepository fotoRepo;

	@GetMapping
	public List<Foto> index(@RequestParam(name="search", required = false) String keyword){
		List<Foto> fotoList;
		if (keyword != null && !keyword.isEmpty()) {
			fotoList = fotoRepo.findByTitleLikeOrTagLike("%" + keyword + "%", "%" + keyword + "%");
		} else {
			fotoList = fotoRepo.findAll();
		}
		return fotoList;
	}
}
