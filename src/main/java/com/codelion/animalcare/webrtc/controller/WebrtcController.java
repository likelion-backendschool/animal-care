package com.codelion.animalcare.webrtc.controller;

import com.codelion.animalcare.webrtc.service.WebrtcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@ControllerAdvice
public class WebrtcController {
    private final WebrtcService webrtcService;
    
    @Autowired
    public WebrtcController(final WebrtcService webrtcService) {
        this.webrtcService = webrtcService;
    }

//    @GetMapping({"", "/", "/index", "/home", "/webrtc/webrtc_main"})
    @GetMapping({"/diagnosis", "/webrtc/webrtc_main"})
    public ModelAndView displayMainPage(final Long id, final String uuid) {
        return this.webrtcService.displayMainPage(id, uuid);
    }

    @PostMapping(value = "/room", params = "action=create")
    public ModelAndView processRoomSelection(@ModelAttribute("id") final String sid, @ModelAttribute("uuid") final String uuid, final BindingResult binding) {
        return this.webrtcService.processRoomSelection(sid, uuid, binding);
    }

    @GetMapping("/room/{sid}/user/{uuid}")
    public ModelAndView displaySelectedRoom(@PathVariable("sid") final String sid, @PathVariable("uuid") final String uuid) {
        return this.webrtcService.displaySelectedRoom(sid, uuid);
    }

    @GetMapping("/room/{sid}/user/{uuid}/exit")
    public ModelAndView processRoomExit(@PathVariable("sid") final String sid, @PathVariable("uuid") final String uuid) {
        return this.webrtcService.processRoomExit(sid, uuid);
    }

    @GetMapping("/room/random")
    public ModelAndView requestRandomRoomNumber(@ModelAttribute("uuid") final String uuid) {
        return webrtcService.requestRandomRoomNumber(uuid);
    }

    @GetMapping("/offer")
    public ModelAndView displaySampleSdpOffer() {
        return new ModelAndView("webrtc/sdp_offer");
    }

    @GetMapping("/stream")
    public ModelAndView displaySampleStreaming() {
        return new ModelAndView("webrtc/streaming");
    }
}
