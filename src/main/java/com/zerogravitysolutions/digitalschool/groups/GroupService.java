package com.zerogravitysolutions.digitalschool.groups;

public interface GroupService {
    GroupEntity findById(Long id);

    GroupEntity create(GroupEntity group);
}
