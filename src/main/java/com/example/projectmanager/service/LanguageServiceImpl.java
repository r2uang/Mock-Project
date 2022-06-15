package com.example.projectmanager.service;

import com.example.projectmanager.entities.Language;
import com.example.projectmanager.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LanguageServiceImpl implements LanguageService{
    @Autowired
    private LanguageRepository languageRepository;

    @Override
    public List<Language> listAll() {
        return languageRepository.findAll();
    }

    @Override
    public void saveOrUpdateLanguage(Language language) {

    }

    @Override
    public void deleteLanguage(Long id) {

    }
}
