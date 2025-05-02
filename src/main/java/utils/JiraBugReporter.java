//package utils;
//
//import org.json.JSONObject;
//
//import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
//import java.util.Base64;
//import io.github.cdimascio.dotenv.Dotenv;
//
//
//public class JiraBugReporter {
//
//    private static final Dotenv dotenv = Dotenv.load();
//
//    private static final String EMAIL = "mtester139@gmail.com";
//    private static final String API_TOKEN = dotenv.get("JIRA_API_TOKEN");
//    private static final String PROJECT_KEY = "ATE";
//    private static final String JIRA_URL = "https://mtester139.atlassian.net/jira/software/c/projects/ATE/issues";
//
//    public static void createBug(String summary, String description) {
//        try {
//            String auth = EMAIL + ":" + API_TOKEN;
//            String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
//
//            // Build JSON safely using JSONObject
//            JSONObject issue = new JSONObject();
//            JSONObject fields = new JSONObject();
//            JSONObject project = new JSONObject();
//            JSONObject issueType = new JSONObject();
//
//            project.put("key", PROJECT_KEY);
//            issueType.put("name", "Bug");
//
//            fields.put("project", project);
//            fields.put("summary", summary);
//            fields.put("description", description);
//            fields.put("issuetype", issueType);
//
//            issue.put("fields", fields);
//
//            HttpClient client = HttpClient.newHttpClient();
//            HttpRequest request = HttpRequest.newBuilder()
//                    .uri(URI.create(JIRA_URL))
//                    .header("Authorization", "Basic " + encodedAuth)
//                    .header("Content-Type", "application/json")
//                    .POST(HttpRequest.BodyPublishers.ofString(issue.toString()))
//                    .build();
//
//            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//            System.out.println("Jira bug creation response:");
//            System.out.println("Status: " + response.statusCode());
//            System.out.println("Body: " + response.body());
//
//        } catch (Exception e) {
//            System.err.println("Failed to create Jira bug:");
//            e.printStackTrace();
//        }
//    }
//}

/*
package utils;

import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Base64;

public class JiraBugReporter {

    private static final String EMAIL = System.getenv("JIRA_USERNAME");
    private static final String API_TOKEN = System.getenv("JIRA_TOKEN");
    private static final String PROJECT_KEY = System.getenv("JIRA_PROJECT_KEY");
    private static final String JIRA_URL = System.getenv("JIRA_API_URL") + "/rest/api/2/issue";

    public static void createBug(String summary, String description) {
        try {
            System.out.println("📢 JiraBugReporter started");

            if (EMAIL == null || API_TOKEN == null || PROJECT_KEY == null || JIRA_URL == null) {
                System.err.println("❌ Missing environment variables for Jira integration");
                return;
            }

            String auth = EMAIL + ":" + API_TOKEN;
            String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());

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

            HttpClient client = HttpClient.newBuilder()
                    .connectTimeout(Duration.ofSeconds(10))
                    .build();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(JIRA_URL))
                    .timeout(Duration.ofSeconds(15)) // ⏳ Prevent hanging
                    .header("Authorization", "Basic " + encodedAuth)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(issue.toString()))
                    .build();

            System.out.println("🌐 Sending request to Jira...");

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("✅ Jira response received.");
            System.out.println("Status: " + response.statusCode());
            System.out.println("Body: " + response.body());

        } catch (Exception e) {
            System.err.println("❌ Failed to create Jira bug:");
            e.printStackTrace();
        }
    }
}*/


package utils;

import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Base64;

public class JiraBugReporter {

    private static final String EMAIL = System.getenv("JIRA_USERNAME");
    private static final String API_TOKEN = System.getenv("JIRA_TOKEN");
    private static final String PROJECT_KEY = System.getenv("JIRA_PROJECT_KEY");
    private static final String JIRA_URL = System.getenv("JIRA_API_URL") + "/rest/api/2/issue";

    public static void createBug(String summary, String description) {
        System.out.println("📢 JiraBugReporter started");

        if (EMAIL == null || API_TOKEN == null || PROJECT_KEY == null || JIRA_URL == null) {
            System.err.println("❌ Missing environment variables for Jira integration");
            System.err.println("EMAIL: " + EMAIL);
            System.err.println("TOKEN: " + (API_TOKEN == null ? "null" : "***hidden***"));
            System.err.println("PROJECT_KEY: " + PROJECT_KEY);
            System.err.println("JIRA_URL: " + JIRA_URL);
            return;
        }

        try {
            // Encode Jira credentials
            String auth = EMAIL + ":" + API_TOKEN;
            String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());

            // Build issue payload
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

            // Build HTTP request
            HttpClient client = HttpClient.newBuilder()
                    .connectTimeout(Duration.ofSeconds(10))
                    .build();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(JIRA_URL))
                    .timeout(Duration.ofSeconds(15))
                    .header("Authorization", "Basic " + encodedAuth)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(issue.toString()))
                    .build();

            System.out.println("🌐 Sending request to Jira...");

            // Send request
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Log response
            System.out.println("✅ Jira response received.");
            System.out.println("Status: " + response.statusCode());
            System.out.println("Body: " + response.body());

            if (response.statusCode() != 201) {
                System.err.println("⚠️ Failed to create Jira issue. Check project key, auth, or request body.");
            }

        } catch (Exception e) {
            System.err.println("❌ Exception occurred while creating Jira bug:");
            e.printStackTrace();
        }
    }
}
