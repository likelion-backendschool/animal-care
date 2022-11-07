package com.codelion.animalcare.chat.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ChatRoom {
    private String roomId;

    public static ChatRoom create(String sid) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.roomId = sid;
        return chatRoom;
    }
}
