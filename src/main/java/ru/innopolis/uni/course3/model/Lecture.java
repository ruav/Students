package ru.innopolis.uni.course3.model;

/**
 * Created by Артем on 22.12.2016.
 */
public class Lecture {

    private Integer id;
    private String topic;
    private Integer duration;

    public Lecture(Integer id) {
        this.id = id;
    }

    public Lecture(Integer id, String topic, Integer duration) {
        this.id = id;
        this.topic = topic;
        this.duration = duration;
    }

    public Lecture(String topic, int duration) {
        this(null, topic, duration);
    }

    public Integer getId() {
        return id;
    }

    public boolean isNew() {
        return id == null;
    }

    public String getTopic() {
        return topic;
    }

    public Integer getDuration() {
        return duration;
    }

    public String getRepresentation() {
        return topic;
    }
}
