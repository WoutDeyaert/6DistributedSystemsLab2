package org.example;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class APIClient {
    private final HttpClient httpClient;
    private final String url;

    public APIClient(String url) {
        this.httpClient = HttpClient.newHttpClient();
        this.url = url;
    }

    public BankAccount createAccount(String username)  {
        String fullUri = url + "/createAccount/"+username;
        URI uri = URI.create(fullUri);
        HttpRequest request = HttpRequest.newBuilder().uri(uri).POST(HttpRequest.BodyPublishers.noBody()).build();
        try{
            HttpResponse response = this.sendRequest(request);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(response.body().toString(), BankAccount.class);
        }
        catch (RuntimeException | JsonProcessingException e ) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public BankAccount getAccount(long id)  {
        String fullUri = url + "/getAccountByID/"+id;
        URI uri = URI.create(fullUri);
        HttpRequest request = HttpRequest.newBuilder().uri(uri).GET().build();
        try{
            HttpResponse response = this.sendRequest(request);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(response.body().toString(), BankAccount.class);
        }
        catch (RuntimeException | JsonProcessingException e ) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public BankAccount getAccount(String name)  {
        String fullUri = url + "/getAccountByName/"+name;
        URI uri = URI.create(fullUri);
        HttpRequest request = HttpRequest.newBuilder().uri(uri).GET().build();
        try{
            HttpResponse response = this.sendRequest(request);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(response.body().toString(), BankAccount.class);
        }
        catch (RuntimeException | JsonProcessingException e ) {
            throw new RuntimeException(e.getMessage());
        }
    }



    public void addMoney(long id, double amount) {
        String fullUri = url + "/addMoney/"+id+"/"+amount;
        URI uri = URI.create(fullUri);
        HttpRequest request =  HttpRequest.newBuilder().uri(uri).PUT(HttpRequest.BodyPublishers.noBody()).build();
        try{
            this.sendRequest(request);
        }
        catch (RuntimeException e ) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void takeMoney(long id, double amount) {
        String fullUri = url + "/takeMoney/"+id+"/"+amount;
        URI uri = URI.create(fullUri);
        HttpRequest request =  HttpRequest.newBuilder().uri(uri).PUT(HttpRequest.BodyPublishers.noBody()).build();
        try{
            this.sendRequest(request);
        }
        catch (RuntimeException e ) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void transferMoney(long idfrom,long idto, double amount) {
        String fullUri = url + "/transferMoney/"+idfrom+"/"+idto+"/"+amount;
        URI uri = URI.create(fullUri);
        HttpRequest request =  HttpRequest.newBuilder().uri(uri).PUT(HttpRequest.BodyPublishers.noBody()).build();
        try{
            this.sendRequest(request);
        }
        catch (RuntimeException e ) {
            throw new RuntimeException(e.getMessage());
        }
    }



    private HttpResponse sendRequest(HttpRequest request)  {
        try {
            HttpResponse response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = response.statusCode();
            if (statusCode >= 200 && statusCode < 300) {
                return response;
            } else {
                throw new RuntimeException(response.body().toString());
            }
        }
        catch (IOException | InterruptedException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
