package com.leonel.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leonel.app.model.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
	

}
