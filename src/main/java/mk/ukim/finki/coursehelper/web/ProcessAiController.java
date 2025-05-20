////package mk.ukim.finki.coursehelper.web;
////
////import mk.ukim.finki.coursehelper.dto.*;
////import mk.ukim.finki.coursehelper.service.PythonService;
////import org.springframework.http.HttpStatus;
////import org.springframework.web.bind.annotation.*;
////
////import java.util.Map;
////
////@RestController
////@CrossOrigin
////@RequestMapping("/api/ai")
////public class AiController {
////
////    private final PythonService python;
////
////    public AiController(PythonService python) {
////        this.python = python;
////    }
////
////    /**
////     * Proxy endpoint for your frontend (or Postman) to hit.
////     */
////    @PostMapping("/process")
////    @ResponseStatus(HttpStatus.ACCEPTED)
////    public ProcessAiResponseDTO processAi(@RequestBody ProcessAiRequestDTO req) {
////        // you could do validation here, e.g. ensure exactly one of pdf_/docx_/video_ is non-null
////        return python.processAi(req);
////    }
////
////    @GetMapping("/status/{taskId}")
////    public ProcessAiResponseDTO checkStatus(@PathVariable String taskId) {
////        // you could reuse PythonService.getTaskStatus and wrap it in ProcessAiResponseDTO
////        var raw = python.getTaskStatus(taskId);
////        // raw is a Map; you can convert or simply return as-is if your DTO matches
////        return new ProcessAiResponseDTO(
////                (String)raw.get("task_id"),
////                (String)raw.get("status"),
////                (Map<String,Object>)raw.get("result")
////        );
////    }
////}
//
//
//package mk.ukim.finki.coursehelper.web;
//
//import mk.ukim.finki.coursehelper.dto.ProcessAiRequestDTO;
//import mk.ukim.finki.coursehelper.dto.TaskResponseDTO;
//import mk.ukim.finki.coursehelper.service.PythonService;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api")
//public class ProcessAiController {
//
//    private final PythonService pythonService;
//
//    public ProcessAiController(PythonService pythonService) {
//        this.pythonService = pythonService;
//    }
//
//    @PostMapping("/process-ai")
//    public ResponseEntity<TaskResponseDTO> processAi(@RequestBody ProcessAiRequestDTO dto) {
//        TaskResponseDTO resp = pythonService.processAi(dto);
//        return ResponseEntity.status(HttpStatus.OK).body(resp);
//    }
//}