package com.example.travelboard.dto;

import com.example.travelboard.entity.Travel;
import jakarta.websocket.server.ServerEndpoint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class TravelForm {

    private Long id;
    private String title;
    private String guide;
    private String content;
    private String schedule;

    public Travel toEntity() {
        return new Travel(id, title, guide, content, schedule);
    }

}
