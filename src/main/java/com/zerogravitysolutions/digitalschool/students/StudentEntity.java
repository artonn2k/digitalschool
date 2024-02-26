package com.zerogravitysolutions.digitalschool.students;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zerogravitysolutions.digitalschool.commons.BaseEntity;
import com.zerogravitysolutions.digitalschool.groups.GroupEntity;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "students")
public class StudentEntity extends BaseEntity {
    private String firstName;
    private String lastName;
    private String profilePicture;
    private String email;
    private String phoneNumber;
    private String address;

    @ManyToMany(mappedBy = "students", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<GroupEntity> groups = new HashSet<>();


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firsName) {
        this.firstName = firsName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<GroupEntity> getGroups() {
        return groups;
    }

    public void setGroups(Set<GroupEntity> groups) {
        this.groups = groups;
    }
}
