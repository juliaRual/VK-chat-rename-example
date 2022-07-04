import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner input = new Scanner(System.in);

//        //System.out.println("Enter your VK token: "); //get your token from vkhost.github.io
//        String token = "vk1.a.ploGzRjmhl--L0RbWZVJk2JRsoaNGMlyu7KO3TrJ9rb8gx4Llq1Phqqst3PlHaV3PY87wCOI3IncMfJv7Eoue-y-u4JfRpq2gTokSngfIqwa8W6bYGb6EQhR7dao2Nl9n2LOxYLPFfeoPBtqg2dxoPO3w79xiro9-t0pio7T5jxKnIpczAGCYaBs-dX4jBxn";
//
//        System.out.println("Enter your chat id: ");
//        String chat_id = input.nextLine();

//        while (true) {
//            //triple launch for the case when several people have X day in a row
////            chatNameEditor(chat_id, token);
////            chatNameEditor(chat_id, token);
////            chatNameEditor(chat_id, token);
////            TimeUnit.SECONDS.sleep(86400);
//
//        }
        dateChecker();
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
        int numberOfPeople = 13;
        String[] person = new String[numberOfPeople];

        person[0] = "Vlad 03.03.2022";
        person[1] = "Today 03.07.2022";
        person[2] = "Yulia 07.02.2022";

        // Get today date //
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.YYYY");
        DateFormat nextYearDateFormat = new SimpleDateFormat("YYYY");
        Date date = new Date();
        String reDate = dateFormat.format(date);
        String nextYear = nextYearDateFormat.format(date);
        int nextYearInt = Integer.parseInt(nextYear) + 1;
        System.out.println(nextYearInt);
        // Compare with array //

        for (int i = 0; i < numberOfPeople; i++) {
            if (person[i] != null) {
                String[] personInfo = person[i].split(" ");
                if (personInfo[1].equals(reDate)) {
                    person[i] = person[0].split(".");
                    System.out.println(person[i]);
                    return (personInfo[0]);
                }
            }
        }
        return ("Original group name");
    }


    public static void chatNameEditor(String chat_id, String token) throws IOException, InterruptedException {
        String happyName = dateChecker();
        if (!happyName.equals("Original group name")) {
            String title = "С днем рождения, " + happyName + "!!";
            editChat(chat_id, title, token);

            TimeUnit.SECONDS.sleep(86400);
            title = "Original group name";
            editChat(chat_id, title, token);
        }
    }
}