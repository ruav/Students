package ru.innopolis.uni.course3.model;

/**
 * Created by Артем on 22.12.2016.
 */
public class Student {

    private Integer id;
    private String name;
    private String surname;
    private String sex;
    private Integer groupNumber;

    public Student(Integer id) {
        this.id = id;
    }

    public Student(Integer id, String name, String surname, String sex, int groupNumber) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.groupNumber = groupNumber;
    }

    public Student(String name, String surname, String sex, int groupNumber) {
        this(null, name, surname, sex, groupNumber);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + name + '\'' +
                ", lastName='" + surname + '\'' +
                ", sex=" + sex +
                ", groupNumber=" + groupNumber +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public boolean isNew() {
        return id == null;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getSex() {
        return sex;
    }

    public String getRepresentation() {
        return name + " " + surname;
    }

    public Integer getGroupNumber() {
        return groupNumber;
    }

    public void setName(String firstName) {
        this.name = firstName;
    }

    public void setSurname(String lastName) {
        this.surname = lastName;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }

}
