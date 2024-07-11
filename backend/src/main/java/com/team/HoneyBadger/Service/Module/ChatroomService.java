package com.team.HoneyBadger.Service.Module;

import com.team.HoneyBadger.DTO.ChatroomRequestDTO;
import com.team.HoneyBadger.DTO.ChatroomResponseDTO;
import com.team.HoneyBadger.Entity.Chatroom;
import com.team.HoneyBadger.Entity.Message;
import com.team.HoneyBadger.Entity.Participant;
import com.team.HoneyBadger.Entity.SiteUser;
import com.team.HoneyBadger.Repository.ChatroomRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatroomService {
    private final ChatroomRepository chatroomRepository;

    @Transactional
    public Chatroom save(Chatroom chatroom) {
        return this.chatroomRepository.save(chatroom);
    }

    @Transactional
    public Chatroom create(String name) {
        return chatroomRepository.save(Chatroom.builder().name(name).build());
    }

    @Transactional
    public Chatroom getChatRoomById(Long chatroomId) {
        return chatroomRepository.findById(chatroomId).orElseThrow();
    }

    @Transactional
    public void delete(Chatroom chatroom) {
        chatroomRepository.delete(chatroom);
    }

    @Transactional
    public Chatroom updateChatroom(Chatroom chatroom, String name) {
        chatroom.setName(name);
        return chatroomRepository.save(chatroom);
    }

    @Transactional
    public List<Chatroom> getChatRoomListByUser(SiteUser user, String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            // 키워드가 없을 경우, 기본적으로 유저의 모든 채팅방을 반환
            return chatroomRepository.findChatroomsByUser(user);
        } else {
            // 키워드가 있을 경우, 키워드를 포함한 채팅방을 반환
            return chatroomRepository.findChatroomsByUserAndKeyword(user, keyword);
        }
    }

    public Chatroom notification(Chatroom chatroom, Message message) {
        chatroom.setNotification(message);
        return chatroomRepository.save(chatroom);
    }
}
