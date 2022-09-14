package com.codelion.animalcare.webrtc.service;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

public interface WebrtcService {
    ModelAndView displayMainPage(Long id, String uuid, Principal principal);
    ModelAndView processRoomSelection(String sid, String uuid, BindingResult bindingResult, Principal principal);
    ModelAndView displaySelectedRoom(String sid, String uuid, Principal principal);
    ModelAndView processRoomExit(String sid, String uuid, Principal principal);
    ModelAndView requestRandomRoomNumber(String uuid, Principal principal);
}
