package ru.innopolis.uni.course3.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.uni.course3.model.Lecture;
import ru.innopolis.uni.course3.repository.LectureRepository;

import java.util.List;

/**
 * Created by Артем on 24.12.2016.
 */
@Service
public class LectureServiceImpl implements LectureService {

    private static final Logger logger = LoggerFactory.getLogger(LectureServiceImpl.class);

    @Autowired
    private LectureRepository repository;

    @Override
    public Lecture add(Lecture lecture) {
        return repository.add(lecture);
    }

    @Override
    public Lecture update(Lecture lecture) {
        return repository.update(lecture);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }

    @Override
    public Lecture get(int id) {
        return repository.get(id);
    }

    @Override
    public List<Lecture> getAll() {
        return repository.getAll();
    }
}
