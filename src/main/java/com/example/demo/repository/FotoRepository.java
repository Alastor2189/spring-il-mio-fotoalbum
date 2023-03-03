package com.example.demo.repository;

//import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Foto;

public interface FotoRepository extends JpaRepository<Foto, Integer> {
	
}
