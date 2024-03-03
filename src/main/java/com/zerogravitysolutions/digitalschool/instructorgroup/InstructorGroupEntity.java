package com.zerogravitysolutions.digitalschool.instructorgroup;

import com.zerogravitysolutions.digitalschool.commons.BaseEntity;
import com.zerogravitysolutions.digitalschool.groups.GroupEntity;
import com.zerogravitysolutions.digitalschool.instructors.InstructorEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "instructors_groups")
public class InstructorGroupEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private InstructorEntity instructor;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private GroupEntity group;

    public InstructorEntity getInstructor() {
        return instructor;
    }

    public void setInstructor(InstructorEntity instructor) {
        this.instructor = instructor;
    }

    public GroupEntity getGroup() {
        return group;
    }

    public void setGroup(GroupEntity group) {
        this.group = group;
    }
}
