package com.codelion.animalcare.chat.controller;

import com.codelion.animalcare.chat.model.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatController {
    private final SimpMessagingTemplate template;

    @MessageMapping("/chat/enter")
    public void enterRoom(ChatMessage message) {
        message.setMessage(message.getSender() + "님이 채팅방에 참여했습니다");
        template.convertAndSend("/sub/chat/room/enter/" + message.getRoomId(), message);
    }

    @MessageMapping("/chat/message")
    public void message(ChatMessage message) {
        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }
}
