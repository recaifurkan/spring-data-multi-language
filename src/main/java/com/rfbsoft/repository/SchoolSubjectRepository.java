package com.rfbsoft.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rfbsoft.model.SchoolSubject;

public interface SchoolSubjectRepository extends JpaRepository<SchoolSubject, Long> {

	@Query("SELECT new com.rfbsoft.model.SchoolSubject(d.id, d.difficulty, coalesce(t.name, d.name), coalesce(t.description, d.description)) FROM SchoolSubject d LEFT JOIN SchoolSubjectTranslation t ON d.id = t.schoolSubjectTranslationId.schoolSubjectId AND t.schoolSubjectTranslationId.languageId = :acceptLanguage WHERE d.id = :id")
	public Optional<SchoolSubject> findTranslatedById(Long id, String acceptLanguage);

	@Query("SELECT new com.rfbsoft.model.SchoolSubject(d.id, d.difficulty, coalesce(t.name, d.name), coalesce(t.description, d.description)) FROM SchoolSubject d LEFT JOIN SchoolSubjectTranslation t ON d.id = t.schoolSubjectTranslationId.schoolSubjectId AND t.schoolSubjectTranslationId.languageId = :acceptLanguage")
	public List<SchoolSubject> findAllTranslated(String acceptLanguage);

}
