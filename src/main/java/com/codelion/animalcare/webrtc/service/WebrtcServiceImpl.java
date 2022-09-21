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
    private final MemberService memberService;
    private final DoctorService doctorService;
    private final AppointmentService appointmentService;
    private final DiagnosisService diagnosisService;
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

//        Optional<MemberDto> memberDto = memberService.findByEmail(principal.getName());
//
//        if(memberDto.isPresent()) {
//            modelAndView.addObject("dto", memberDto.get());
//        }
//        else{
//            LoadDoctorMyPageInfo.ResponseDto doctorDto = doctorService.findByEmail(principal.getName());
//            modelAndView.addObject("dto", doctorDto);
//        }

        return modelAndView;
    }

    @Override
    public ModelAndView processRoomSelection(final String sid, final String uuid, final BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            // simplified version, no errors processing
            return new ModelAndView(REDIRECT);
        }
        Optional<Long> optionalId = parser.parseId(sid);
        optionalId.ifPresent(id -> Optional.ofNullable(uuid).ifPresent(name -> roomService.addRoom(new Room(id))));


        return this.displayMainPage(optionalId.orElse(null), uuid, principal);
    }

    /**
     * 환자 정보 확인
     */
    @GetMapping("{appointmentId}")
    public String loadMyPageDoctorAppointment(
            Model model,
            @PathVariable long appointmentId
    ){
        LoadMyPageDoctorAppointment.ResponseDto appointment
                = appointmentService.findById(appointmentId);

        FindOneDiagnosis diagnosis = diagnosisService.findByAppointmentId(appointment.getId());


        model.addAttribute("appointment", appointment);
        model.addAttribute("member", appointment.getMember());
        model.addAttribute("animal", appointment.getAnimal());
        model.addAttribute("hospital", appointment.getHospital());
        model.addAttribute("diagnosis", diagnosis);
        model.addAttribute("doctor", appointment.getDoctor());
        return "myPage/doctor/member-manage-self";
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

}