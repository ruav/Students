package ru.innopolis.uni.course3.service;

import ru.innopolis.uni.course3.model.Journal;

import java.util.List;

/**
 * Created by Артем on 24.12.2016.
 */
public interface JournalService {

    Journal add(Journal journal);

    Journal update(Journal journal);

    void delete(int id);

    Journal get(int id);

    List<Journal> getAll();
}
