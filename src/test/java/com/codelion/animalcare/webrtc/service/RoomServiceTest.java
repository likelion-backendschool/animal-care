//package com.codelion.animalcare.webrtc.service;
//
//import com.codelion.animalcare.webrtc.domain.Room;
//import com.codelion.animalcare.webrtc.domain.RoomService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.web.WebAppConfiguration;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest
//@WebAppConfiguration
//public class RoomServiceTest {
//    @Autowired
//    private RoomService service;
//
//    @Test
//    public void shouldReturnRoom_whenFindRoomByStringId() {
//        Room room = new Room(1L);
//        service.addRoom(room);
//        Room actualRoom = service.findRoomByStringId(Long.valueOf(1L).toString()).get();
//
//        assertThat(actualRoom)
//                .isNotNull()
//                .isEqualToComparingFieldByFieldRecursively(room);
//    }
//}
