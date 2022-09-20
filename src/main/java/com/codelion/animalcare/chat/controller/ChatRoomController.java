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

    // 채팅방 목록 조회
    @GetMapping("/rooms")
    public String chatRoomList(Model model) {
        model.addAttribute("roomList", chatRoomService.findAllChatRoom());

        return "chat/roomList";
    }

    // 채팅방 생성
    @PostMapping("/room")
    public String create(@RequestParam String roomName, RedirectAttributes attr){
        attr.addFlashAttribute("roomName", chatRoomService.createRoom(roomName));

        return "redirect:/usr/chat/rooms";
    }

    // 채팅방 입장
    @GetMapping("/room/{roomId}")
    public String chatRoom(Model model, @PathVariable String roomId) {
        model.addAttribute("room", chatRoomService.findChatRoomById(roomId));

        return "chat/room";
    }
}
