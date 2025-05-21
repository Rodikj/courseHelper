package mk.ukim.finki.coursehelper.dto;

public class Video {
    private String type;
    private String uri;
    private UriData uri_data;
    private String path;
    private Duration duration;

    public static class UriData {
        private String uri_file_name;
        private String uri_state;
        private String uri_mime_type;
    }

    public static class Duration {
        private int minutes;
        private int seconds;

    }
}
