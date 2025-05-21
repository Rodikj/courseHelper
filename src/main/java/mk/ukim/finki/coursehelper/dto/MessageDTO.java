//package mk.ukim.finki.coursehelper.dto;
//
//// MessageDTO.java
//
//
//import com.fasterxml.jackson.annotation.JsonInclude;
//
//@JsonInclude(JsonInclude.Include.NON_NULL)
//public record MessageDTO(
//        String role,
//        String content,
//        VideoDTO video,
//        // in future: you might have tool_calls, tool_results
//        Object tool_calls,
//        Object tool_results
//) {}
//
package mk.ukim.finki.coursehelper.dto;

import java.util.List;

public record MessageDTO(
                String role,
                String content,
                Video video,
                List<String> toolCalls,
                List<String> toolResults) {
}