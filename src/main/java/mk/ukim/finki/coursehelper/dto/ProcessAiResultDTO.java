//package mk.ukim.finki.coursehelper.dto;
//
//
//
//import java.util.List;
//
//public record ProcessAiResultDTO(
//        List<MessageDTO> conversation_history
//) {}
//
package mk.ukim.finki.coursehelper.dto;

import java.util.List;

public record ProcessAiResultDTO(
        ConversationHistoryDTO conversationHistory
) {}