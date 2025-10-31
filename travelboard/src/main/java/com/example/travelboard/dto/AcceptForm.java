package com.example.travelboard.dto;

import com.example.travelboard.entity.Accept;
import com.example.travelboard.entity.Travel;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class AcceptForm {

    private Long id;
    private String name;
    private String content;

    public Accept toEntity() {
        return new Accept(id, name, content);
    }
}
