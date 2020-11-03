package com.rfbsoft.controller;

import java.util.List;

import com.rfbsoft.repository.SchoolSubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rfbsoft.model.SchoolSubject;

@RestController
@RequestMapping("school-subject")
public class SchoolSubjectController {

	@Autowired
	private SchoolSubjectRepository repository;

	@GetMapping
	public List<SchoolSubject> getAllTranslated() {
		return repository.findAllTranslated(LocaleContextHolder.getLocale().toLanguageTag());
	}

	@GetMapping("/{id}")
	public SchoolSubject getOneTranslated(@PathVariable long id) {
		return repository.findTranslatedById(id, LocaleContextHolder.getLocale().toLanguageTag()).orElse(null);
	}

}
