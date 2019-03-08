package org.udg.pds.springtodo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.udg.pds.springtodo.serializer.JsonDateDeserializer;
import org.udg.pds.springtodo.serializer.JsonDateSerializer;

import javax.persistence.*;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;



@Entity(name = "usergroup")
// This tells JAXB that it has to ignore getters and setters and only use fields for JSON marshaling/unmarshaling
public class Group implements Serializable {
    /**
     * Default value included to remove warning. Remove or modify at will.
     **/
    private static final long serialVersionUID = 1L;

    public Group() {
    }

    public Group(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // This tells JAXB that this field can be used as ID
    // Since XmlID can only be used on Strings, we need to use LongAdapter to transform Long <-> String
    @Id
    // Don't forget to use the extra argument "strategy = GenerationType.IDENTITY" to get AUTO_INCREMENT
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_user")
    private User owner;

    @Column(name = "fk_user", insertable = false, updatable = false)
    private long ownerId;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription (String description) {
        this.description = description;
    }

    public void setOwner(User user) {
        this.owner = user;
    }

    @JsonView(Views.Complete.class)
    public long getOwnerId() {
        return ownerId;
    }


}
