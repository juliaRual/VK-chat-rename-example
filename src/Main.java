import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

class Main {

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter your VK token: "); //get your token from vkhost.github.io
        String token = input.nextLine();

        System.out.println("Enter your chat id: ");
        String chat_id = input.nextLine();

        System.out.println("Enter new title: ");
        String title = input.nextLine();

        title = title.replace(" ", "+");

        URL url = new URL("https://api.vk.com/method/messages.editChat?chat_id=" + chat_id + "&title=" + title + "&access_token=" + token + "&v=5.131");
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setRequestMethod("GET");



        InputStream responseStream = httpConn.getResponseCode() / 100 == 2
                ? httpConn.getInputStream()
                : httpConn.getErrorStream();
        Scanner s = new Scanner(responseStream).useDelimiter("\\A");
        String response = s.hasNext() ? s.next() : "";
        System.out.println(response);
    }
}