//package com.codelion.animalcare.webrtc.socket;
//
//import com.codelion.animalcare.webrtc.domain.Room;
//import com.codelion.animalcare.webrtc.domain.RoomService;
//import com.codelion.animalcare.webrtc.domain.WebSocketMessage;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.ObjectWriter;
//import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.Before;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.web.socket.CloseStatus;
//import org.springframework.web.socket.TextMessage;
//import org.springframework.web.socket.WebSocketSession;
//
//import java.util.UUID;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.mock;
//
//@SpringBootTest
//@WebAppConfiguration
//public class SignalHandlerTest {
//    @Autowired private RoomService service;
//    @Autowired private SignalHandler handler;
//
//    private String name;
//    private WebSocketSession session;
//    private Room room;
//
//    @BeforeEach
//    public void setup() {
//        Long id = 1L;
//        name = UUID.randomUUID().toString();
//        session = mock(WebSocketSession.class);
//        room = new Room(id);
//        service.addRoom(room);
//    }
//
//    @Test
//    public void shouldRemoveClient_whenConnectionClosed() throws Exception {
//        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
//        WebSocketMessage message = new WebSocketMessage(name,"join", room.getId().toString(), null, null);
//        handler.handleTextMessage(session, new TextMessage(ow.writeValueAsString(message)));
//        message = new WebSocketMessage(name, "leave", room.getId().toString(), null, null);
//        handler.handleTextMessage(session, new TextMessage(ow.writeValueAsString(message)));
//        handler.afterConnectionClosed(session, CloseStatus.NORMAL);
//
//        assertThat(service.getClients(room))
//                .isEmpty();
//    }
//
//    @AfterEach
//    public void teardown() {
//        name = null;
//        session = null;
//        room = null;
//    }
//}
