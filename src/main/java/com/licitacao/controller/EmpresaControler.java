package com.licitacao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.licitacao.model.Empresa;
import com.licitacao.repository.EmpresaRepository;

@RestController
@RequestMapping("/empresas")
public class EmpresaControler {

	@Autowired
	private EmpresaRepository empresaRepository;
	
	@GetMapping
	public List<Empresa> listar(){
		return empresaRepository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Empresa cadastrar(@RequestBody Empresa empresa) {
		return empresaRepository.save(empresa);
	}
	
	 @DeleteMapping("/{id}")
	    public ResponseEntity<Object> deletar(@PathVariable Long id) {
	        return empresaRepository.findById(id)
	            .map(empresa -> {
	                empresaRepository.deleteById(id);
	                return ResponseEntity.ok().build();
	            }).orElse(ResponseEntity.notFound().build());
	    }
	 
	 @PutMapping("/{id}")
	    public ResponseEntity<Empresa> atualizar(@PathVariable Long id, @RequestBody Empresa empresaDetalhes) {
	        return empresaRepository.findById(id)
	            .map(empresa -> {
	                empresa.setNome(empresaDetalhes.getNome());
	                empresa.setEmail(empresaDetalhes.getEmail());
	                empresa.setCNPJ(empresaDetalhes.getCNPJ());
	                empresa.setCapDec(empresaDetalhes.getCapDec());
	                empresa.setAcervo(empresaDetalhes.getAcervo());
	                empresa.setNomePJ(empresaDetalhes.getNomePJ());
	                Empresa empresaAtualizada = empresaRepository.save(empresa);
	                return ResponseEntity.ok(empresaAtualizada);
	            }).orElse(ResponseEntity.notFound().build());
	    }
	}
