package com.stackroute.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import javax.annotation.processing.Generated;
import java.lang.annotation.Documented;
import java.util.Objects;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Track {

    @Id
    @ApiModelProperty(notes = "Track's Id that is unique")
    private int trackId;
    @ApiModelProperty(notes = "Track's name")
    private String trackName;
    @ApiModelProperty(notes = "Track's comment that would help other listeners to judge the song")
    private String trackComment;

}
