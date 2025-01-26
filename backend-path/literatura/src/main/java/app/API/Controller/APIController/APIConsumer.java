package app.API.Controller.APIController;

import com.fasterxml.jackson.databind.ObjectMapper;

import app.API.Domain.Book;
import app.API.Domain.ListBooks;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class APIConsumer {

    private final ObjectMapper objectMapper;

    public APIConsumer() {
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Sends an HTTP GET request to the specified API URL and retrieves the response as a string.
     *
     * @param apiUrl the URL of the API to send the request to
     * @return the response body of the API as a string if the status code is 200
     * @throws URISyntaxException   if the given API URL has an invalid URI syntax
     * @throws IOException          if an I/O error occurs during the request or response handling
     * @throws InterruptedException if the HTTP request is interrupted
     */
    private HttpResponse<String> sendRequest(String apiUrl) throws URISyntaxException, IOException, InterruptedException {
        HttpResponse<String> response;
        try (HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS) // Configurar para seguir redirecciones
                .build()) {

            // Crear solicitud HTTP
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(apiUrl))
                    .GET() // Método HTTP GET
                    .build();

            // Enviar la solicitud y obtener la respuesta
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        }
        return response;
    }

    /**
     * Fetches a list of books from the specified API URL by making an HTTP GET request.
     *
     * @param apiUrl the URL of the API endpoint to retrieve books from
     * @return a {@code ListBooks} object containing the book data, which includes a list of books, count, and pagination details
     * @throws URISyntaxException   if the specified API URL has invalid URI syntax
     * @throws IOException          if an I/O error occurs during the request or response handling
     * @throws InterruptedException if the HTTP request is interrupted
     */
    public ListBooks fetchBooks(String apiUrl) throws URISyntaxException, IOException, InterruptedException {
        try {
            HttpResponse<String> response = sendRequest(apiUrl);

            // Manejar el código de respuesta HTTP
            if (response.statusCode() == 200) {
                // Mapeo a ListBooks
                return this.objectMapper.readValue(response.body(), ListBooks.class);
            } else {
                throw new IllegalAccessError("El API ha respondido con un status distinto al 200 " + response.statusCode());
            }
        } catch (URISyntaxException e) {
            throw new URISyntaxException(apiUrl, "Error en firma de URI: " + e.getMessage());
        } catch (IOException e) {
            throw new IOException("Error de archivo en la lectura: " + e.getMessage());
        } catch (InterruptedException e) {
            throw new InterruptedException("Se ha interrumpido la conexión: " + e.getMessage());
        }
    }

    /**
     * Fetches a single book from the specified API URL by making an HTTP GET request.
     *
     * @param apiUrl the URL of the API endpoint to retrieve the book from
     * @return a {@code Book} object representing the book fetched from the API
     * @throws URISyntaxException   if the specified API URL has invalid URI syntax
     * @throws IOException          if an I/O error occurs during the request or response handling
     * @throws InterruptedException if the HTTP request is interrupted
     */
    public Book fetchBook(String apiUrl) throws URISyntaxException, IOException, InterruptedException {
        try {
            HttpResponse<String> response = sendRequest(apiUrl);

            // Manejar el código de respuesta HTTP
            if (response.statusCode() == 200) {
                // Mapeo a Book (assuming the response contains a single Book object)
                return this.objectMapper.readValue(response.body(), Book.class);
            } else {
                throw new IllegalAccessError("El API ha respondido con un status distinto al 200 " + response.statusCode());
            }
        } catch (URISyntaxException e) {
            throw new URISyntaxException(apiUrl, "Error en firma de URI: " + e.getMessage());
        } catch (IOException e) {
            throw new IOException("Error de archivo en la lectura: " + e.getMessage());
        } catch (InterruptedException e) {
            throw new InterruptedException("Se ha interrumpido la conexión: " + e.getMessage());
        }
    }
}
