package org.udg.pds.springtodo.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.udg.pds.springtodo.controller.exceptions.ControllerException;
import org.udg.pds.springtodo.entity.IdObject;
import org.udg.pds.springtodo.entity.Tag;
import org.udg.pds.springtodo.entity.Task;
import org.udg.pds.springtodo.entity.Views;
import org.udg.pds.springtodo.entity.Group;
import org.udg.pds.springtodo.serializer.JsonDateDeserializer;
import org.udg.pds.springtodo.service.GroupService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;

@RequestMapping(path="/groups")
@Controller
public class GroupController extends BaseController {

    @Autowired
    GroupService groupService;


    @PostMapping(consumes = "application/json")
    @ResponseBody
    public IdObject addGroup(HttpSession session, @Valid @RequestBody R_Group group) {

        Long userId = getLoggedUser(session);

        if (group.name == null) {
            throw new ControllerException("No text supplied");
        }
        if (group.description == null) {
            throw new ControllerException("No limit date supplied");
        }

        return groupService.addGroup(group.name, userId, group.description);
    }

    static class R_Group {

        @NotNull
        public String name;

        @NotNull
        public String description;


    }

}
