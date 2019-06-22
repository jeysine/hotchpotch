package com.jeysine.services.websocket.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

/**
 * @author yaojx
 * @date 2018-10-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WebsocketMessage {
    private String from;

    private String to;

    private String type;

}
