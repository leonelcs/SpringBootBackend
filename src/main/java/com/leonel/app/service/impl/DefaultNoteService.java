package com.leonel.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonel.app.model.Note;
import com.leonel.app.repository.NoteRepository;
import com.leonel.app.service.NoteService;

@Service
public class DefaultNoteService implements NoteService {
	
	NoteRepository repository;
	
	@Autowired
	public DefaultNoteService(NoteRepository repository) {
		// TODO Auto-generated constructor stub
		this.repository = repository;
	}

	@Override
	public Note save(Note note) {
		// TODO Auto-generated method stub
		return repository.save(note);
	}

	@Override
	public Note findById(Long id) {
		return repository.findOne(id);
	}

	@Override
	public void delete(Note note) {
		// TODO Auto-generated method stub
		repository.delete(note);;	}

}
