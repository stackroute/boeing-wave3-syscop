package com.stackroute.nextevent.model;

import javax.validation.constraints.NotNull;
import java.util.Date;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Event {

    @Id
    private String id;

    @NotNull
    private String eventId;

    @NotNull
    private String name;

    @NotNull
    private String description;

    private Date created;

    //@NotNull
    private Date start;

    //@NotNull
    private  Date end;

    private Venu venu;

    private Category category;

    @NotNull
    private String imageUrl;

    @Override
    public String toString() {
        return "Event{" +
                "id='" + id + '\'' +
                ", eventId='" + eventId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", created=" + created +
                ", start=" + start +
                ", end=" + end +
                ", venu=" + venu +
                ", category=" + category +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
