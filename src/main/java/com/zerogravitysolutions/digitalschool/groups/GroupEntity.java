package com.zerogravitysolutions.digitalschool.groups;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zerogravitysolutions.digitalschool.commons.BaseEntity;
import com.zerogravitysolutions.digitalschool.instructorgroup.InstructorGroupEntity;
import com.zerogravitysolutions.digitalschool.students.StudentEntity;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "groups")
public class GroupEntity extends BaseEntity {

    private String title;

    private String description;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "students_groups",
            joinColumns = {@JoinColumn(name = "group_id")},
            inverseJoinColumns = {@JoinColumn(name = "student_id")}
    )
            @JsonIgnore
    Set<StudentEntity> students = new HashSet<>();

    @OneToMany(mappedBy = "group")
    @JsonIgnore
    private Set<InstructorGroupEntity> instructorGroupSet = new HashSet<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<StudentEntity> getStudents() {
        return students;
    }

    public void setStudents(Set<StudentEntity> students) {
        this.students = students;
    }
}
