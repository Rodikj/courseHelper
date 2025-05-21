// package mk.ukim.finki.coursehelper.service.impl;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.scheduling.TaskScheduler;
// import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
// import org.springframework.stereotype.Service;
// import org.springframework.web.reactive.function.client.WebClient;

// import jakarta.annotation.PostConstruct;
// import mk.ukim.finki.coursehelper.dto.TaskStatusResponse;

// import java.time.Duration;
// import java.time.Instant;
// import java.util.Map;
// import java.util.concurrent.ConcurrentHashMap;
// import java.util.concurrent.ScheduledFuture;
// import java.util.function.Consumer;

// @Service
// public class TaskPollingServiceImpl {

// private TaskScheduler taskScheduler;
// private final WebClient webClient;

// // Store all active polling tasks
// private final Map<String, ScheduledFuture<?>> activeTasks = new
// ConcurrentHashMap<>();

// @Autowired
// public TaskPollingServiceImpl(WebClient.Builder webClientBuilder) {
// this.webClient =
// webClientBuilder.baseUrl("http://your-api-base-url").build();
// this.taskScheduler = createTaskScheduler();
// }

// @PostConstruct
// public void init() {
// this.taskScheduler = createTaskScheduler();
// }

// private TaskScheduler createTaskScheduler() {
// ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
// scheduler.setPoolSize(5);
// scheduler.setThreadNamePrefix("task-status-poller-");
// scheduler.initialize();
// return scheduler;
// }

// /**
// * Poll for task status until it's no longer PENDING
// *
// * @param taskId The ID of the task to poll
// * @param resultConsumer Consumer that processes the final result
// * @param maxAttempts Maximum number of polling attempts (use -1 for
// * unlimited)
// * @param intervalSeconds Seconds between polling attempts
// */
// public void pollUntilComplete(String taskId, Consumer<TaskStatusResponse>
// resultConsumer,
// int maxAttempts, int intervalSeconds) {
// // Create atomic reference to track attempts
// final int[] attempts = { 0 };

// // Create the recurring task
// ScheduledFuture<?> scheduledTask = taskScheduler.scheduleAtFixedRate(() -> {
// attempts[0]++;

// try {
// // Fetch the current status
// TaskStatusResponse statusResponse = getTaskStatus(taskId);

// // If not pending anymore or max attempts reached
// if (!"PENDING".equals(statusResponse.getStatus()) ||
// (maxAttempts > 0 && attempts[0] >= maxAttempts)) {

// // Cancel this scheduled task
// cancelPolling(taskId);

// // Process the result
// resultConsumer.accept(statusResponse);
// }
// } catch (Exception e) {
// // Handle error - log and cancel polling
// System.err.println("Error polling task status for " + taskId + ": " +
// e.getMessage());
// cancelPolling(taskId);

// // Create error response
// TaskStatusResponse errorResponse = new TaskStatusResponse();
// errorResponse.setTask_id(taskId);
// errorResponse.setStatus("ERROR");

// // Send error to consumer
// resultConsumer.accept(errorResponse);
// }
// }, Instant.now(), Duration.ofSeconds(intervalSeconds));

// // Store the scheduled task
// activeTasks.put(taskId, scheduledTask);
// }

// /**
// * Cancel active polling for a task
// */
// public void cancelPolling(String taskId) {
// ScheduledFuture<?> task = activeTasks.remove(taskId);
// if (task != null && !task.isCancelled() && !task.isDone()) {
// task.cancel(false);
// }
// }

// /**
// * Get the current task status
// */
// private TaskStatusResponse getTaskStatus(String taskId) {
// // Here you would make the actual API call to get task status
// // For example using WebClient:
// return webClient.get()
// .uri("/api/task-status/" + taskId)
// .retrieve()
// .bodyToMono(TaskStatusResponse.class)
// .block();

// // If your task status is stored in a database or in-memory,
// // you would query that storage instead of making an HTTP call
// }
// }