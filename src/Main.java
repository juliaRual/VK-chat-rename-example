import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

class Main {

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter your VK token: "); //get your token from vkhost.github.io
        String token = input.nextLine();

        System.out.println("Enter your chat id: ");
        String chat_id = input.nextLine();

        System.out.println("Enter new title: ");
        String title = "С днем рождения, " + dateChecker() + "!!";

        editChat(chat_id, title, token);
    }

    public static void editChat (String chat_id, String title, String token) throws IOException {
        title = title.replace(" ", "+"); //if name include spaces
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

    public static String dateChecker () {
        // Get today date //
        DateFormat dateFormat = new SimpleDateFormat("dd.MM");
        Date date = new Date();
        String reDate = dateFormat.format(date);
        // Compare with array //
        String[] person = new String[13];
        person = peopleName();

        for (int i = 0; i < 13; i++) {
            if (person[i] != null) {
                String[] personInfo = person[i].split(" ");
                if (personInfo[1].equals(reDate)) {
                    return (personInfo[0]);
                }
            }
        }
        return ("Original group name");
    }

    public static String[] peopleName() {
        int numberOfPeople = 13;
        String[] person = new String[numberOfPeople];

        person[0] = "Vlad 03.03";
        person[1] = "Today 02.07";
        person[2] = "Yulia 07.02";

        return person;
    }


}