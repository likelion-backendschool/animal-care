package com.codelion.animalcare.chat.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessage {
    private String roomId;
    private String sender;
    private String message;
}
