package com.codelion.animalcare.chat.controller;

import com.codelion.animalcare.chat.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/usr/chat")
public class ChatRoomController {
    private final ChatRoomService chatRoomService;

    // 채팅방 입장
    @GetMapping("/room/{roomId}")
    public String chatRoom(Model model, @PathVariable String roomId) {
        model.addAttribute("room", chatRoomService.findChatRoomById(roomId));

        return "chat/room";
    }
}
