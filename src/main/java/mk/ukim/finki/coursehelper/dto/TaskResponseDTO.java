package mk.ukim.finki.coursehelper.dto;

import java.util.List;

public record TaskResponseDTO(
                String taskId,
                String status,
                ProcessAiResultDTO result) {
}
