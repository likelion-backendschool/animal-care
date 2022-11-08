package com.codelion.animalcare.webrtc.service;


import com.codelion.animalcare.chat.service.ChatRoomService;
import com.codelion.animalcare.webrtc.domain.Room;
import com.codelion.animalcare.webrtc.domain.RoomService;
import com.codelion.animalcare.webrtc.util.Parser;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class WebrtcServiceImpl implements WebrtcService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final String REDIRECT = "redirect:/";

    private final RoomService roomService;
    private final Parser parser;
    private final ChatRoomService chatRoomService;


    @Override
    public ModelAndView displayMainPage(final Long id, final String uuid) {
        final ModelAndView modelAndView = new ModelAndView("webrtc/webrtc_main");
        modelAndView.addObject("id", id);
        modelAndView.addObject("rooms", roomService.getRooms());
        modelAndView.addObject("uuid", uuid);

        return modelAndView;
    }

    @Override
    public ModelAndView processRoomSelection(final String sid, final String uuid, final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // simplified version, no errors processing
            return new ModelAndView(REDIRECT);
        }
        Optional<Long> optionalId = parser.parseId(sid);
        optionalId.ifPresent(id -> Optional.ofNullable(uuid).ifPresent(name -> {
            roomService.addRoom(new Room(id));
            chatRoomService.createRoom(sid);
        }));


        return this.displayMainPage(optionalId.orElse(null), uuid);
    }


    @Override
    public ModelAndView displaySelectedRoom(final String sid, final String uuid) {
        // redirect to main page if provided data is invalid
        ModelAndView modelAndView = new ModelAndView(REDIRECT);

        if (parser.parseId(sid).isPresent()) {
            Room room = roomService.findRoomByStringId(sid).orElse(null);
            if(room != null && uuid != null && !uuid.isEmpty()) {
                logger.debug("User {} is going to join Room #{}", uuid, sid);
                // open the chat room
                modelAndView = new ModelAndView("webrtc/chat_room", "id", sid);
                modelAndView.addObject("uuid", uuid);
                modelAndView.addObject("chatRoom", chatRoomService.findChatRoomById(sid));
            }
        }

        return modelAndView;
    }

    @Override
    public ModelAndView processRoomExit(final String sid, final String uuid) {
        if(sid != null && uuid != null) {
            logger.debug("User {} has left Room #{}", uuid, sid);
            // implement any logic you need
        }
        return new ModelAndView(REDIRECT);
    }

    @Override
    public ModelAndView requestRandomRoomNumber(final String uuid) {
        return this.displayMainPage(randomValue(), uuid);
    }

    private Long randomValue() {
        return ThreadLocalRandom.current().nextLong(0, 100);
    }

}