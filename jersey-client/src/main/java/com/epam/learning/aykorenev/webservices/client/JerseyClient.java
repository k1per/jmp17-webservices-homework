package com.epam.learning.aykorenev.webservices.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;
import org.glassfish.jersey.media.multipart.internal.MultiPartWriter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.IOException;


/**
 * Created by Anton_Korenev on 3/18/2017.
 */
public class JerseyClient {
    private static Client client;
    // e.g. "C:/images/downloaded.jpg"
    private static String logoDownloadPath;

    public static void main(String[] args) throws JSONException, IOException {
        logoDownloadPath = args[0];

        DefaultClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getClasses().add(MultiPartWriter.class);
        client = Client.create(clientConfig);

        long userOneId = 1L;
        long userTwoId = 2L;
        long userThreeId = 3L;

        getAll();
        getById(userOneId);

        deleteById(userTwoId);

        createUser();

        updateUser(userThreeId);

        saveLogo(userOneId);
        getLogo(userOneId);
    }

    private static void updateUser(long userId) {
        System.out.println("Updating user with id " + userId);
        String userJson = "{" +
                "\"firstName\":\"Tyrion\"," +
                "\"lastName\":\"Lan1ster\"," +
                "\"login\":\"superImp\"," +
                "\"email\":\"imp2017@email.com\"}";

        WebResource resource = client.resource("http://localhost:8080/users/" + userId);
        ClientResponse response = resource.
                type(MediaType.APPLICATION_JSON).
                entity(userJson).
                put(ClientResponse.class);
        System.out.println(response);
        System.out.println("Checking update");
        getAll();
    }

    private static void createUser() {
        System.out.println("Creating user");
        String userXml = "<User>\n" +
                "<firstName>Petyr</firstName>\n" +
                "<lastName>Baelish</lastName>\n" +
                "<login>Littlefinger</login>\n" +
                "<email>littlefinger@email.com</email>\n" +
                "</User>";
        WebResource resource = client.resource("http://localhost:8080/users");
        ClientResponse response = resource.
                type(MediaType.APPLICATION_XML).
                entity(userXml).
                post(ClientResponse.class);
        System.out.println(response);
        System.out.println("Checking");
        getAll();
    }

    private static void deleteById(long userId) {
        System.out.println("Deleting user with id " + userId);
        WebResource webResource = client
                .resource("http://localhost:8080/users" + "/" + userId);
        webResource.delete();
        System.out.println("Deleted user with id. Checking...");
        getAll();
    }

    private static void getById(long userId) {
        System.out.println("Getting user by id " + userId);
        WebResource webResource = client
                .resource("http://localhost:8080/users" + "/" + userId);

        String body = webResource.
                accept("application/json").
                get(String.class);
        printPrettyObject(body);
    }

    private static void getLogo(long userId) throws IOException {
        System.out.println("Getting logo");
        WebResource resource = client.resource("http://localhost:8080/users/" + userId + "/logo");
        File file = resource.get(File.class);
        file.renameTo(new File(logoDownloadPath + "/downloaded.jpg"));
        System.out.println("Downloaded file to " + logoDownloadPath + "\n");
    }

    private static void saveLogo(long userId) {
        System.out.println("Uploading logo");
        FormDataMultiPart multiPart = new FormDataMultiPart();
        File file = new File(JerseyClient.class.getResource("/file.jpg").getFile());
        multiPart.bodyPart(new FileDataBodyPart("file.jpg", file,
                MediaType.APPLICATION_OCTET_STREAM_TYPE));
        WebResource resource = client.resource("http://localhost:8080/users/" + userId + "/logo");
        ClientResponse response = resource.type(
                MediaType.MULTIPART_FORM_DATA_TYPE).put(ClientResponse.class, multiPart);
        System.out.println(response + "\n");
    }

    private static void getAll() {
        System.out.println("Getting all users");
        WebResource webResource = client
                .resource("http://localhost:8080/users");

        String body = webResource.
                accept("application/json").
                get(String.class);

        printPrettyArray(body);
    }

    private static void printPrettyArray(String body) {
        try {
            JSONArray json = new JSONArray(body);
            System.out.println(json.toString(4) + "\n");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void printPrettyObject(String body) {
        try {
            JSONObject json = new JSONObject(body);
            System.out.println(json.toString(4) + "\n");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
