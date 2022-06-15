package com.example.projectmanager.service;

import com.example.projectmanager.entities.Employee;
import com.example.projectmanager.entities.Language;

import java.util.List;

public interface LanguageService {
    List<Language> listAll();

    void saveOrUpdateLanguage(Language language);

    void deleteLanguage(Long id);
}
