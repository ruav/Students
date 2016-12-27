package ru.innopolis.uni.course3.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.uni.course3.model.Journal;
import ru.innopolis.uni.course3.repository.JournalRepository;

import java.util.List;

/**
 * Created by Артем on 24.12.2016.
 */
@Service
public class JournalServiceImpl implements JournalService{

    private static final Logger logger = LoggerFactory.getLogger(JournalServiceImpl.class);

    @Autowired
    private JournalRepository repository;

    @Override
    public Journal add(Journal journal) {
        return repository.add(journal);
    }

    @Override
    public Journal update(Journal journal) {
        return repository.update(journal);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }

    @Override
    public Journal get(int id) {
        return repository.get(id);
    }

    @Override
    public List<Journal> getAll() {
        return repository.getAll();
    }
}
