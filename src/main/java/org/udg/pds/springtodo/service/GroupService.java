package org.udg.pds.springtodo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.udg.pds.springtodo.controller.exceptions.ServiceException;
import org.udg.pds.springtodo.entity.IdObject;
import org.udg.pds.springtodo.entity.Tag;
import org.udg.pds.springtodo.entity.Task;
import org.udg.pds.springtodo.entity.User;
import org.udg.pds.springtodo.entity.Group;
import org.udg.pds.springtodo.repository.TaskRepository;
import org.udg.pds.springtodo.repository.GroupRepository;


import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@Service
public class GroupService {

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    UserService userService;



    public GroupRepository crud() {
        return groupRepository;
    }

    @Transactional
    public IdObject addGroup(String name, Long userId,
                            String description) {
        try {
            User user = userService.getUser(userId);

            Group group = new Group(name,description);

            group.setOwner(user);

            user.addOwnergroup(group);

            groupRepository.save(group);

            return new IdObject( group.getId() );
        } catch (Exception ex) {
            // Very important: if you want that an exception reaches the EJB caller, you have to throw an ServiceException
            // We catch the normal exception and then transform it in a ServiceException
            throw new ServiceException(ex.getMessage());
        }
    }





}
