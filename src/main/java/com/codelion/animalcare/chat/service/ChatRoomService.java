package com.codelion.animalcare.chat.service;

import com.codelion.animalcare.chat.model.ChatRoom;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChatRoomService {
    private Map<String, ChatRoom> chatRoomMap;

    @PostConstruct
    private void init() {
        chatRoomMap = new LinkedHashMap<>();
    }

    // 채팅방 불러오기
    public ChatRoom findChatRoomById(String roomId) {
        return chatRoomMap.get(roomId);
    }

    // 채팅방 생성
    public String createRoom(String sid) {
        ChatRoom chatRoom = ChatRoom.create(sid);
        chatRoomMap.put(chatRoom.getRoomId(), chatRoom);

        return chatRoom.getRoomId();
    }
}
