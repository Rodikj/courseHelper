package mk.ukim.finki.coursehelper.service;
<<<<<<< HEAD
<<<<<<< HEAD
=======


//import org.springframework.stereotype.Service;
//
//@Service
//public class PythonService {

//    // Just a mock method that simulates uploading file
//    public String uploadFile(MockFile file) {
//        System.out.println("Mock uploadFile called with file: " + file.getName());
//        return "Mock upload successful";
//    }
//
//    // Simple mock file class to represent a file (replace with your actual file object)
//    public static class MockFile {
//        private final String name;
//
//        public MockFile(String name) {
//            this.name = name;
//        }
//
//        public String getName() {
//            return name;
//        }
//    }
//}



//import mk.ukim.finki.coursehelper.dto.ProcessAiRequestDTO;
//import mk.ukim.finki.coursehelper.dto.ProcessAiResponseDTO;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.core.io.ByteArrayResource;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Service;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.util.Map;
//
//
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.web.client.RestTemplate;
//
//
//@Service
//public class PythonService {
//    private final RestTemplate restTemplate = new RestTemplate();
//
//    @Value("${python.base-url:http://localhost:8000}")
//    private String pythonBaseUrl;
//
//    @Value("${python.api-key:}")
//    private String pythonApiKey;
//
//    public Map<?, ?> uploadFile(MultipartFile file) throws Exception {
//        String url = pythonBaseUrl + "/api/upload/?api_key=" + pythonApiKey;
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
//
//        var body = new LinkedMultiValueMap<String, Object>();
//        body.add("file", new ByteArrayResource(file.getBytes()) {
//            @Override public String getFilename() {
//                return file.getOriginalFilename();
//            }
//        });
//
//        HttpEntity<LinkedMultiValueMap<String,Object>> request = new HttpEntity<>(body, headers);
//        return restTemplate.postForObject(url, request, Map.class);
//    }
//
//    public Map<?, ?> getTaskStatus(String taskId) {
//        String url = pythonBaseUrl + "/api/task-status/" + taskId;
//        return restTemplate.getForObject(url, Map.class);
//    }
//
//
//}



import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
>>>>>>> cc5b632 (modified everything)
=======
>>>>>>> f93b757 (some reshuffling with service more like wp with impl)
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

<<<<<<< HEAD
<<<<<<< HEAD

public interface PythonService
{

    Map<?, ?> uploadFile(MultipartFile file) throws Exception;
    Map<?, ?> getTaskStatus(String taskId);
=======
@Service
public class PythonService {
=======
>>>>>>> f93b757 (some reshuffling with service more like wp with impl)

public interface PythonService
{

<<<<<<< HEAD
    // Base URL of your Python service (e.g. http://localhost:8000)
    @Value("${python.base-url:http://localhost:8000}")
    private String pythonBaseUrl;

    // API key to pass along to the Python endpoints
    @Value("${python.api-key:}")
    private String pythonApiKey;

    /**
     * Uploads a file (PDF, DOCX, video) to the Python service.
     *
     * @param file the MultipartFile sent from your Spring Boot controller
     * @return the JSON response from Python (e.g. contains "task_id", etc.) as a Map
     */
    public Map<?, ?> uploadFile(MultipartFile file) throws Exception {
        String url = pythonBaseUrl + "/api/upload/?api_key=" + pythonApiKey;

        // Prepare headers for multipart/form-data
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        // Build the body: wrap the file bytes in a ByteArrayResource
        LinkedMultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", new ByteArrayResource(file.getBytes()) {
            @Override
            public String getFilename() {
                return file.getOriginalFilename();
            }
        });

        // Combine into an HttpEntity
        HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity =
                new HttpEntity<>(body, headers);

        // Send the POST and parse the JSON into a Map
        return restTemplate.postForObject(url, requestEntity, Map.class);
    }

    /**
     * Polls the Python service for the status/result of an earlier task.
     *
     * @param taskId the ID returned from uploadFile(...)
     * @return the status JSON (e.g. { "task_id": "...", "status": "SUCCESS", "result": { ... } })
     */
    public Map<?, ?> getTaskStatus(String taskId) {
        String url = pythonBaseUrl + "/api/task-status/" + taskId;
        return restTemplate.getForObject(url, Map.class);
    }

//     If in the future you want to call /api/process-ai directly from Java,
//     you could add something like this (and define your DTOs):
//
//     public ProcessAiResponseDTO processAi(ProcessAiRequestDTO dto) {
//         String url = pythonBaseUrl + "/api/process-ai/?api_key=" + pythonApiKey;
//         return restTemplate.postForObject(url, dto, ProcessAiResponseDTO.class);
//     }
>>>>>>> cc5b632 (modified everything)
=======
    Map<?, ?> uploadFile(MultipartFile file) throws Exception;
    Map<?, ?> getTaskStatus(String taskId);
>>>>>>> f93b757 (some reshuffling with service more like wp with impl)
}

