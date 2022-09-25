package com.codelion.animalcare.webrtc.service;

import com.codelion.animalcare.chat.service.ChatRoomService;
import com.codelion.animalcare.domain.mypage.dto.LoadDoctorMyPageInfo;
import com.codelion.animalcare.domain.user.dto.MemberDto;
import com.codelion.animalcare.domain.user.service.DoctorService;
import com.codelion.animalcare.domain.user.service.MemberService;
import com.codelion.animalcare.webrtc.domain.Room;
import com.codelion.animalcare.webrtc.domain.RoomService;
import com.codelion.animalcare.webrtc.util.Parser;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class WebrtcServiceImpl implements WebrtcService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final String REDIRECT = "redirect:/";

    private final RoomService roomService;
    private final Parser parser;
    private final MemberService memberService;
    private final DoctorService doctorService;
    private final ChatRoomService chatRoomService;

//    @Autowired
//    public WebrtcServiceImpl(final RoomService roomService, final Parser parser) {
//        this.roomService = roomService;
//        this.parser = parser;
//    }

    @Override
    public ModelAndView displayMainPage(final Long id, final String uuid, final Principal principal) {
        final ModelAndView modelAndView = new ModelAndView("webrtc/webrtc_main");
        modelAndView.addObject("id", id);
        modelAndView.addObject("rooms", roomService.getRooms());
        modelAndView.addObject("uuid", uuid);

        Optional<MemberDto> memberDto = findMemberDto(principal);

        if(memberDto.isPresent()) {
            modelAndView.addObject("dto", memberDto.get());
        }
        else{
            LoadDoctorMyPageInfo.ResponseDto doctorDto = doctorService.findByEmail(principal.getName());
            modelAndView.addObject("dto", doctorDto);
        }

        return modelAndView;
    }

    @Override
    public ModelAndView processRoomSelection(final String sid, final String uuid, final BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            // simplified version, no errors processing
            return new ModelAndView(REDIRECT);
        }
        Optional<Long> optionalId = parser.parseId(sid);
        optionalId.ifPresent(id -> Optional.ofNullable(uuid).ifPresent(name -> {
            roomService.addRoom(new Room(id));
            chatRoomService.createRoom(sid);
        }));


        return this.displayMainPage(optionalId.orElse(null), uuid, principal);
    }

    @Override
    public ModelAndView displaySelectedRoom(final String sid, final String uuid, Principal principal) {
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
    public ModelAndView processRoomExit(final String sid, final String uuid, Principal principal) {
        if(sid != null && uuid != null) {
            logger.debug("User {} has left Room #{}", uuid, sid);
            // implement any logic you need
        }
        return new ModelAndView(REDIRECT);
    }

    @Override
    public ModelAndView requestRandomRoomNumber(final String uuid, Principal principal) {
        return this.displayMainPage(randomValue(), uuid, principal);
    }

    private Long randomValue() {
        return ThreadLocalRandom.current().nextLong(0, 100);
    }


    private Optional<MemberDto> findMemberDto(Principal principal) {

        return memberService.findByEmail(principal.getName());
    }
}