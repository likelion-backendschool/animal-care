package com.codelion.animalcare.domain.chat.service;

import com.codelion.animalcare.domain.chat.model.ChatRoom;
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

    // 모든 채팅방 불러오기
    public List<ChatRoom> findAllChatRoom() {
        List<ChatRoom> roomList = new ArrayList<>(chatRoomMap.values());

        return roomList;
    }

    // 채팅방 하나 불러오기
    public ChatRoom findChatRoomById(String roomId) {
        return chatRoomMap.get(roomId);
    }

    // 채팅방 생성
    public String createRoom(String name) {
        ChatRoom chatRoom = ChatRoom.create(name);
        chatRoomMap.put(chatRoom.getRoomId(), chatRoom);

        return chatRoom.getRoomName();
    }
}
