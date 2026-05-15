package aplicacao;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AnaliseSentimento {
    
    private static final String CHAVE = "BgcoXYM1xpv1uGeJP7NQrD2vYpodYHTwdfD42MialctJSG0L1b9OJQQJ99CEACYeBjFXJ3w3AAAEACOG79qd";
    private static final String ENDPOINT = "https://ia-joao-puc.cognitiveservices.azure.com/";

    public static void main(String[] args) {
        try {
            String caminhoUrl = ENDPOINT + "/text/analytics/v3.0/sentiment";
            
            String textoParaAnalisar = "A computação em nuvem é uma tecnologia fantástica e estou a adorar aprender!";
            
            String jsonBody = "{ \"documents\": [ { \"id\": \"1\", \"language\": \"pt\", \"text\": \"" + textoParaAnalisar + "\" } ] }";

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(caminhoUrl))
                    .header("Content-Type", "application/json")
                    .header("Ocp-Apim-Subscription-Key", CHAVE)
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();

            System.out.println("A enviar a frase para a IA da Microsoft...");
            
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("\n--- Resposta da Inteligência Artificial ---");
            System.out.println(response.body());

        } catch (Exception e) {
            System.out.println("Oops! Ocorreu um erro: " + e.getMessage());
        }
    }
}