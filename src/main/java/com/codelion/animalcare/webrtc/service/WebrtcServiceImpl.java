package com.codelion.animalcare.webrtc.service;

import com.codelion.animalcare.domain.appointment.controller.AppointmentMyPageDoctorController;
import com.codelion.animalcare.domain.appointment.dto.AppointmentDto;
import com.codelion.animalcare.domain.appointment.dto.LoadMyPageDoctorAppointment;
import com.codelion.animalcare.domain.appointment.entity.Appointment;
import com.codelion.animalcare.domain.appointment.service.AppointmentService;
import com.codelion.animalcare.domain.diagnosis.dto.FindOneDiagnosis;
import com.codelion.animalcare.domain.diagnosis.service.DiagnosisService;
import com.codelion.animalcare.domain.doctormypage.dto.LoadDoctorMyPageInfo;
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
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class WebrtcServiceImpl implements WebrtcService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final String REDIRECT = "redirect:/";

    private final RoomService roomService;
    private final Parser parser;

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
        optionalId.ifPresent(id -> Optional.ofNullable(uuid).ifPresent(name -> roomService.addRoom(new Room(id))));


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