package ru.innopolis.uni.course3.service;

import ru.innopolis.uni.course3.model.Lecture;

import java.util.List;

/**
 * Created by Артем on 24.12.2016.
 */
public interface LectureService {

    Lecture add(Lecture lecture);

    Lecture update(Lecture lecture);

    void delete(int id);

    Lecture get(int id);

    List<Lecture> getAll();
}
