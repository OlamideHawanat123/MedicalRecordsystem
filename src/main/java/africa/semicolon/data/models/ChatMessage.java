package africa.semicolon.data.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessage {
    private String senderId;
    private String receiverId;
    private String content;
    private String chatRoomId; // unique room for doctor-patient pair
    private MessageType type;

    public enum MessageType {
        CHAT, JOIN, LEAVE
    }


}

