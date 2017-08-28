package com.leonel.app.service;

import com.leonel.app.model.Note;

public interface NoteService {

	public Note save(Note note);
	
	public Note findById(Long id);

	public void delete(Note note);
}
