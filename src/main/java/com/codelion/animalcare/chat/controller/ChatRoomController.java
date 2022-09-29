package com.codelion.animalcare.chat.controller;

import com.codelion.animalcare.chat.service.ChatRoomService;
import com.codelion.animalcare.domain.user.entity.UserInfo;
import com.codelion.animalcare.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/usr/chat")
public class ChatRoomController {
    private final ChatRoomService chatRoomService;
    private final UserService userService;

    // 채팅방 입장
    @GetMapping("/room/{roomId}")
    public String chatRoom(Model model, @PathVariable String roomId, Principal principal) {

        UserInfo user = userService.getUserInfo(principal.getName()).orElse(null);

        model.addAttribute("user", user);
        model.addAttribute("room", chatRoomService.findChatRoomById(roomId));

        return "chat/room";
    }
}
