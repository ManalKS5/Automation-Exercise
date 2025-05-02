package utils;

import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
import io.github.cdimascio.dotenv.Dotenv;


public class JiraBugReporter {

    private static final Dotenv dotenv = Dotenv.load();

    private static final String EMAIL = "mtester139@gmail.com";
    private static final String API_TOKEN = dotenv.get("JIRA_API_TOKEN");
    private static final String PROJECT_KEY = "SCRUM";
    private static final String JIRA_URL = "https://mtester139.atlassian.net/rest/api/2/issue";

    public static void createBug(String summary, String description) {
        try {
            String auth = EMAIL + ":" + API_TOKEN;
            String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());

            // Build JSON safely using JSONObject
            JSONObject issue = new JSONObject();
            JSONObject fields = new JSONObject();
            JSONObject project = new JSONObject();
            JSONObject issueType = new JSONObject();

            project.put("key", PROJECT_KEY);
            issueType.put("name", "Bug");

            fields.put("project", project);
            fields.put("summary", summary);
            fields.put("description", description);
            fields.put("issuetype", issueType);

            issue.put("fields", fields);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(JIRA_URL))
                    .header("Authorization", "Basic " + encodedAuth)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(issue.toString()))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Jira bug creation response:");
            System.out.println("Status: " + response.statusCode());
            System.out.println("Body: " + response.body());

        } catch (Exception e) {
            System.err.println("Failed to create Jira bug:");
            e.printStackTrace();
        }
    }
}
