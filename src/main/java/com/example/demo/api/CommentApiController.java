package com.example.demo.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Commenti;
import com.example.demo.models.Foto;
import com.example.demo.repository.CommentiRepository;
import com.example.demo.repository.FotoRepository;

@RestController
@CrossOrigin
@RequestMapping("/api/commenti")
public class CommentApiController {
	@Autowired
	private CommentiRepository commentiRepo;

	@Autowired 
	private FotoRepository fotoRepo;

	@GetMapping
	public List<Commenti> index(){
		List<Commenti> commentList = commentiRepo.findAll();
		return commentList;
	}

	@GetMapping("{id}")
	public List<Commenti> commentsById(@PathVariable("id")Integer id){
		Foto photo = fotoRepo.getReferenceById(id);
		return photo.getCommenti();
	}

	//CREATE
	@PostMapping("/create/{id}")
	public Commenti create(@PathVariable("id") Integer id, @RequestBody Commenti commenti) {
		Foto foto = fotoRepo.getReferenceById(id);
		commenti.setFoto(foto);
		commenti.getText();
		return commentiRepo.save(commenti);

	}

}
