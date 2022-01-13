package Solution.udp;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.DatagramSocket;
import java.util.Base64;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;

import org.json.*;

import netscape.javascript.JSObject;

public class Server {

    static boolean timeOk = true;

    static class startTimer {
        Timer timer;

        public startTimer(int seconds) {
            timeOk = true;
            timer = new Timer();
            taskRunner task = new taskRunner();
            timer.schedule(task, seconds * 1000);
            System.out.println("Timer started for " + seconds + " seconds.");
            System.out.println("timerOk set to " + timeOk);
        }

        public void stopTimer() {
            timer.cancel();
            System.out.println("Stopped timer");
        }

        class taskRunner extends TimerTask {
            public void run() {
                System.out.println("Time's up");
                timeOk = false;
                System.out.println("timerOk set to " + timeOk);
                timer.cancel();
            }
        }
    }

    public static JSONObject getMore(int imgid, int zoomid) throws IOException {
        JSONObject json = new JSONObject();

        switch (imgid) {
        case (0):
            if (zoomid == 2) {
                json = carImg2();
            } else {
                json = carImg3();
            }
            break;
        case (1):
            if (zoomid == 2) {
                json = catImg2();
            } else {
                json = catImg3();
            }
            break;
        case (2):
            if (zoomid == 2) {
                json = cucImg2();
            } else {
                json = cucImg3();
            }
            break;
        case (3):
            if (zoomid == 2) {
                json = hatImg2();
            } else {
                json = hatImg3();
            }
            break;
        case (4):
            if (zoomid == 2) {
                json = pugImg2();
            } else {
                json = pugImg3();
            }
            break;
        case (5):
            if (zoomid == 2) {
                json = pupImg2();
            } else {
                json = pupImg3();
            }
            break;
        }

        return json;

    }

    public static JSONObject greeting(String username) {
        JSONObject json = new JSONObject();
        json.put("order", 1);
        json.put("type", "greeting");
        json.put("payload", "Hello, " + username + ".  How many rounds would you like to play?(Max. 6)");
        return json;
    }

    public static JSONObject goodbye(String username) {
        JSONObject json = new JSONObject();
        json.put("order", 7);
        json.put("type", "goodbye");
        json.put("payload", "Goodbye, " + username + "!");
        return json;
    }

    public static JSONObject roundStart(String username, int rounds) {
        JSONObject json = new JSONObject();
        int timer = 30 * rounds;
        json.put("order", 2);
        json.put("type", "start-prompt");
        json.put("payload", "Thanks, " + username + ".  I will show you " + rounds + " images to guess.\nYou have "
                + timer + " seconds to guess it.\nPlease type `start` when you're ready to begin.");
        return json;
    }

    public static JSONObject carImg() throws IOException {
        JSONObject json = new JSONObject();
        json.put("order", 3);
        json.put("image-zoom", 1);
        json.put("image-id", 0);
        json.put("answer", "car");
        json.put("type", "image");
        File file = new File("img/car/car1.png");
        if (!file.exists()) {
            System.err.println("Cannot find file: " + file.getAbsolutePath());
            System.exit(-1);
        }

        BufferedImage img = ImageIO.read(file);
        byte[] bytes = null;
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            ImageIO.write(img, "png", out);
            bytes = out.toByteArray();
        }
        if (bytes != null) {
            Base64.Encoder encoder = Base64.getEncoder();
            json.put("data", encoder.encodeToString(bytes));
            return json;
        }
        return error("Unable to save image to byte array");
    }

    public static JSONObject carImg2() throws IOException {
        JSONObject json = new JSONObject();
        json.put("order", 3);
        json.put("image-zoom", 2);
        json.put("image-id", 0);
        json.put("answer", "car");
        json.put("type", "image");
        File file = new File("img/car/car2.png");
        if (!file.exists()) {
            System.err.println("Cannot find file: " + file.getAbsolutePath());
            System.exit(-1);
        }

        BufferedImage img = ImageIO.read(file);
        byte[] bytes = null;
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            ImageIO.write(img, "png", out);
            bytes = out.toByteArray();
        }
        if (bytes != null) {
            Base64.Encoder encoder = Base64.getEncoder();
            json.put("data", encoder.encodeToString(bytes));
            return json;
        }
        return error("Unable to save image to byte array");
    }

    public static JSONObject carImg3() throws IOException {
        JSONObject json = new JSONObject();
        json.put("order", 3);
        json.put("image-zoom", 3);
        json.put("image-id", 0);
        json.put("answer", "car");
        json.put("type", "image");
        File file = new File("img/car/car3.png");
        if (!file.exists()) {
            System.err.println("Cannot find file: " + file.getAbsolutePath());
            System.exit(-1);
        }

        BufferedImage img = ImageIO.read(file);
        byte[] bytes = null;
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            ImageIO.write(img, "png", out);
            bytes = out.toByteArray();
        }
        if (bytes != null) {
            Base64.Encoder encoder = Base64.getEncoder();
            json.put("data", encoder.encodeToString(bytes));
            return json;
        }
        return error("Unable to save image to byte array");
    }

    public static JSONObject catImg() throws IOException {
        JSONObject json = new JSONObject();
        json.put("order", 3);
        json.put("image-id", 1);
        json.put("image-zoom", 1);
        json.put("answer", "cat");
        json.put("type", "image");
        File file = new File("img/cat/cat1.png");
        if (!file.exists()) {
            System.err.println("Cannot find file: " + file.getAbsolutePath());
            System.exit(-1);
        }

        BufferedImage img = ImageIO.read(file);
        byte[] bytes = null;
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            ImageIO.write(img, "png", out);
            bytes = out.toByteArray();
        }
        if (bytes != null) {
            Base64.Encoder encoder = Base64.getEncoder();
            json.put("data", encoder.encodeToString(bytes));
            return json;
        }
        return error("Unable to save image to byte array");
    }

    public static JSONObject catImg2() throws IOException {
        JSONObject json = new JSONObject();
        json.put("order", 3);
        json.put("image-id", 1);
        json.put("image-zoom", 2);
        json.put("answer", "cat");
        json.put("type", "image");
        File file = new File("img/cat/cat2.png");
        if (!file.exists()) {
            System.err.println("Cannot find file: " + file.getAbsolutePath());
            System.exit(-1);
        }

        BufferedImage img = ImageIO.read(file);
        byte[] bytes = null;
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            ImageIO.write(img, "png", out);
            bytes = out.toByteArray();
        }
        if (bytes != null) {
            Base64.Encoder encoder = Base64.getEncoder();
            json.put("data", encoder.encodeToString(bytes));
            return json;
        }
        return error("Unable to save image to byte array");
    }

    public static JSONObject catImg3() throws IOException {
        JSONObject json = new JSONObject();
        json.put("order", 3);
        json.put("image-id", 1);
        json.put("image-zoom", 3);
        json.put("answer", "cat");
        json.put("type", "image");
        File file = new File("img/cat/cat3.png");
        if (!file.exists()) {
            System.err.println("Cannot find file: " + file.getAbsolutePath());
            System.exit(-1);
        }

        BufferedImage img = ImageIO.read(file);
        byte[] bytes = null;
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            ImageIO.write(img, "png", out);
            bytes = out.toByteArray();
        }
        if (bytes != null) {
            Base64.Encoder encoder = Base64.getEncoder();
            json.put("data", encoder.encodeToString(bytes));
            return json;
        }
        return error("Unable to save image to byte array");
    }

    public static JSONObject cucImg() throws IOException {
        JSONObject json = new JSONObject();
        json.put("order", 3);
        json.put("image-id", 2);
        json.put("image-zoom", 1);
        json.put("answer", "cucumber");
        json.put("type", "image");
        File file = new File("img/cucumber/cucumber1.png");
        if (!file.exists()) {
            System.err.println("Cannot find file: " + file.getAbsolutePath());
            System.exit(-1);
        }

        BufferedImage img = ImageIO.read(file);
        byte[] bytes = null;
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            ImageIO.write(img, "png", out);
            bytes = out.toByteArray();
        }
        if (bytes != null) {
            Base64.Encoder encoder = Base64.getEncoder();
            json.put("data", encoder.encodeToString(bytes));
            return json;
        }
        return error("Unable to save image to byte array");
    }

    public static JSONObject cucImg2() throws IOException {
        JSONObject json = new JSONObject();
        json.put("order", 3);
        json.put("image-id", 2);
        json.put("image-zoom", 2);
        json.put("answer", "cucumber");
        json.put("type", "image");
        File file = new File("img/cucumber/cucumber2.png");
        if (!file.exists()) {
            System.err.println("Cannot find file: " + file.getAbsolutePath());
            System.exit(-1);
        }

        BufferedImage img = ImageIO.read(file);
        byte[] bytes = null;
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            ImageIO.write(img, "png", out);
            bytes = out.toByteArray();
        }
        if (bytes != null) {
            Base64.Encoder encoder = Base64.getEncoder();
            json.put("data", encoder.encodeToString(bytes));
            return json;
        }
        return error("Unable to save image to byte array");
    }

    public static JSONObject cucImg3() throws IOException {
        JSONObject json = new JSONObject();
        json.put("order", 3);
        json.put("image-id", 2);
        json.put("image-zoom", 3);
        json.put("answer", "cucumber");
        json.put("type", "image");
        File file = new File("img/cucumber/cucumber3.png");
        if (!file.exists()) {
            System.err.println("Cannot find file: " + file.getAbsolutePath());
            System.exit(-1);
        }

        BufferedImage img = ImageIO.read(file);
        byte[] bytes = null;
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            ImageIO.write(img, "png", out);
            bytes = out.toByteArray();
        }
        if (bytes != null) {
            Base64.Encoder encoder = Base64.getEncoder();
            json.put("data", encoder.encodeToString(bytes));
            return json;
        }
        return error("Unable to save image to byte array");
    }

    public static JSONObject hatImg() throws IOException {
        JSONObject json = new JSONObject();
        json.put("order", 3);
        json.put("image-id", 3);
        json.put("image-zoom", 1);
        json.put("answer", "hat");
        json.put("type", "image");
        File file = new File("img/hat/hat1.png");
        if (!file.exists()) {
            System.err.println("Cannot find file: " + file.getAbsolutePath());
            System.exit(-1);
        }

        BufferedImage img = ImageIO.read(file);
        byte[] bytes = null;
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            ImageIO.write(img, "png", out);
            bytes = out.toByteArray();
        }
        if (bytes != null) {
            Base64.Encoder encoder = Base64.getEncoder();
            json.put("data", encoder.encodeToString(bytes));
            return json;
        }
        return error("Unable to save image to byte array");
    }

    public static JSONObject hatImg2() throws IOException {
        JSONObject json = new JSONObject();
        json.put("order", 3);
        json.put("image-id", 3);
        json.put("image-zoom", 2);
        json.put("answer", "hat");
        json.put("type", "image");
        File file = new File("img/hat/hat2.png");
        if (!file.exists()) {
            System.err.println("Cannot find file: " + file.getAbsolutePath());
            System.exit(-1);
        }

        BufferedImage img = ImageIO.read(file);
        byte[] bytes = null;
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            ImageIO.write(img, "png", out);
            bytes = out.toByteArray();
        }
        if (bytes != null) {
            Base64.Encoder encoder = Base64.getEncoder();
            json.put("data", encoder.encodeToString(bytes));
            return json;
        }
        return error("Unable to save image to byte array");
    }

    public static JSONObject hatImg3() throws IOException {
        JSONObject json = new JSONObject();
        json.put("order", 3);
        json.put("image-id", 3);
        json.put("image-zoom", 3);
        json.put("answer", "hat");
        json.put("type", "image");
        File file = new File("img/hat/hat3.png");
        if (!file.exists()) {
            System.err.println("Cannot find file: " + file.getAbsolutePath());
            System.exit(-1);
        }

        BufferedImage img = ImageIO.read(file);
        byte[] bytes = null;
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            ImageIO.write(img, "png", out);
            bytes = out.toByteArray();
        }
        if (bytes != null) {
            Base64.Encoder encoder = Base64.getEncoder();
            json.put("data", encoder.encodeToString(bytes));
            return json;
        }
        return error("Unable to save image to byte array");
    }

    public static JSONObject pugImg() throws IOException {
        JSONObject json = new JSONObject();
        json.put("order", 3);
        json.put("image-id", 4);
        json.put("image-zoom", 1);
        json.put("answer", "pug");
        json.put("type", "image");
        File file = new File("img/pug/pug1.png");
        if (!file.exists()) {
            System.err.println("Cannot find file: " + file.getAbsolutePath());
            System.exit(-1);
        }

        BufferedImage img = ImageIO.read(file);
        byte[] bytes = null;
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            ImageIO.write(img, "png", out);
            bytes = out.toByteArray();
        }
        if (bytes != null) {
            Base64.Encoder encoder = Base64.getEncoder();
            json.put("data", encoder.encodeToString(bytes));
            return json;
        }
        return error("Unable to save image to byte array");
    }

    public static JSONObject pugImg2() throws IOException {
        JSONObject json = new JSONObject();
        json.put("order", 3);
        json.put("image-id", 4);
        json.put("image-zoom", 2);
        json.put("answer", "pug");
        json.put("type", "image");
        File file = new File("img/pug/pug2.png");
        if (!file.exists()) {
            System.err.println("Cannot find file: " + file.getAbsolutePath());
            System.exit(-1);
        }

        BufferedImage img = ImageIO.read(file);
        byte[] bytes = null;
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            ImageIO.write(img, "png", out);
            bytes = out.toByteArray();
        }
        if (bytes != null) {
            Base64.Encoder encoder = Base64.getEncoder();
            json.put("data", encoder.encodeToString(bytes));
            return json;
        }
        return error("Unable to save image to byte array");
    }

    public static JSONObject pugImg3() throws IOException {
        JSONObject json = new JSONObject();
        json.put("order", 3);
        json.put("image-id", 4);
        json.put("image-zoom", 3);
        json.put("answer", "pug");
        json.put("type", "image");
        File file = new File("img/pug/pug3.png");
        if (!file.exists()) {
            System.err.println("Cannot find file: " + file.getAbsolutePath());
            System.exit(-1);
        }

        BufferedImage img = ImageIO.read(file);
        byte[] bytes = null;
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            ImageIO.write(img, "png", out);
            bytes = out.toByteArray();
        }
        if (bytes != null) {
            Base64.Encoder encoder = Base64.getEncoder();
            json.put("data", encoder.encodeToString(bytes));
            return json;
        }
        return error("Unable to save image to byte array");
    }

    public static JSONObject pupImg() throws IOException {
        JSONObject json = new JSONObject();
        json.put("order", 3);
        json.put("image-id", 5);
        json.put("image-zoom", 1);
        json.put("answer", "puppy");
        json.put("type", "image");
        File file = new File("img/puppy/puppy1.png");
        if (!file.exists()) {
            System.err.println("Cannot find file: " + file.getAbsolutePath());
            System.exit(-1);
        }

        BufferedImage img = ImageIO.read(file);
        byte[] bytes = null;
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            ImageIO.write(img, "png", out);
            bytes = out.toByteArray();
        }
        if (bytes != null) {
            Base64.Encoder encoder = Base64.getEncoder();
            json.put("data", encoder.encodeToString(bytes));
            return json;
        }
        return error("Unable to save image to byte array");
    }

    public static JSONObject pupImg2() throws IOException {
        JSONObject json = new JSONObject();
        json.put("order", 3);
        json.put("image-id", 5);
        json.put("image-zoom", 2);
        json.put("answer", "puppy");
        json.put("type", "image");
        File file = new File("img/puppy/puppy2.png");
        if (!file.exists()) {
            System.err.println("Cannot find file: " + file.getAbsolutePath());
            System.exit(-1);
        }

        BufferedImage img = ImageIO.read(file);
        byte[] bytes = null;
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            ImageIO.write(img, "png", out);
            bytes = out.toByteArray();
        }
        if (bytes != null) {
            Base64.Encoder encoder = Base64.getEncoder();
            json.put("data", encoder.encodeToString(bytes));
            return json;
        }
        return error("Unable to save image to byte array");
    }

    public static JSONObject pupImg3() throws IOException {
        JSONObject json = new JSONObject();
        json.put("order", 3);
        json.put("image-id", 5);
        json.put("image-zoom", 3);
        json.put("answer", "puppy");
        json.put("type", "image");
        File file = new File("img/puppy/puppy3.png");
        if (!file.exists()) {
            System.err.println("Cannot find file: " + file.getAbsolutePath());
            System.exit(-1);
        }

        BufferedImage img = ImageIO.read(file);
        byte[] bytes = null;
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            ImageIO.write(img, "png", out);
            bytes = out.toByteArray();
        }
        if (bytes != null) {
            Base64.Encoder encoder = Base64.getEncoder();
            json.put("data", encoder.encodeToString(bytes));
            return json;
        }
        return error("Unable to save image to byte array");
    }

    public static JSONObject winner(String username) throws IOException {
        JSONObject json = new JSONObject();
        json.put("order", 5);
        json.put("type", "winner-prompt");
        json.put("image-id", 7);
        json.put("image-zoom", 1);
        json.put("payload", "Great job " + username + ", you've won!");
        json.put("type", "image");
        File file = new File("img/win.jpg");
        if (!file.exists()) {
            System.err.println("Cannot find file: " + file.getAbsolutePath());
            System.exit(-1);
        }

        BufferedImage img = ImageIO.read(file);
        byte[] bytes = null;
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            ImageIO.write(img, "jpg", out);
            bytes = out.toByteArray();
        }
        if (bytes != null) {
            Base64.Encoder encoder = Base64.getEncoder();
            json.put("data", encoder.encodeToString(bytes));
            return json;
        }
        return error("Unable to save image to byte array");

    }

    public static JSONObject loser() throws IOException {
        JSONObject json = new JSONObject();
        json.put("order", 5);
        json.put("image-id", 6);
        json.put("image-zoom", 1);
        json.put("payload", "You have guessed none correctly, fellow loser");
        json.put("type", "image");
        File file = new File("img/lose.jpg");
        if (!file.exists()) {
            System.err.println("Cannot find file: " + file.getAbsolutePath());
            System.exit(-1);
        }

        BufferedImage img = ImageIO.read(file);
        byte[] bytes = null;
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            ImageIO.write(img, "jpg", out);
            bytes = out.toByteArray();
        }
        if (bytes != null) {
            Base64.Encoder encoder = Base64.getEncoder();
            json.put("data", encoder.encodeToString(bytes));
            return json;
        }
        return error("Unable to save image to byte array");
    }

    public static JSONObject loser2() throws IOException {
        JSONObject json = new JSONObject();
        json.put("order", 5);
        json.put("image-id", 6);
        json.put("image-zoom", 1);
        json.put("payload", "You weren't able to guess it in time, fellow loser");
        json.put("type", "image");
        File file = new File("img/lose.jpg");
        if (!file.exists()) {
            System.err.println("Cannot find file: " + file.getAbsolutePath());
            System.exit(-1);
        }

        BufferedImage img = ImageIO.read(file);
        byte[] bytes = null;
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            ImageIO.write(img, "jpg", out);
            bytes = out.toByteArray();
        }
        if (bytes != null) {
            Base64.Encoder encoder = Base64.getEncoder();
            json.put("data", encoder.encodeToString(bytes));
            return json;
        }
        return error("Unable to save image to byte array");
    }

    public static JSONObject incorrect() {
        JSONObject json = new JSONObject();
        json.put("order", 4);
        json.put("type", "wrong-prompt");
        json.put("payload", "Wrong, try again!");
        return json;
    }

    public static JSONObject randomImg() throws IOException {
        Random rand = new Random();
        int random = rand.nextInt(6);
        JSONObject json = new JSONObject();
        if (random == 0) {
            json = carImg();
        } else if (random == 1) {
            json = catImg();
        } else if (random == 2) {
            json = cucImg();
        } else if (random == 3) {
            json = hatImg();
        } else if (random == 4) {
            json = pugImg();
        } else {
            json = pupImg();
        }
        return json;
    }

    public static JSONObject error(String err) {
        JSONObject json = new JSONObject();
        json.put("error", err);
        return json;
    }


    public static void main(String[] args) throws IOException{
        DatagramSocket sock = null;
        Integer port = 9000;
        String name = "";
        int rounds = 0;
        String ans = "";
        int zoomid = -1;
        int imgid = -1;
        int timer = 0;
        startTimer st = null;
        final int maxTraffic = 1;
        int currentTraffic = 0;
        
        if (args.length > 0) { // host, if provided
            if (args.length == 1){                
                port = Integer.valueOf(args[0]);
            } else {
                System.out.println("Too many arguments");
            }
        } 


        try{
            sock = new DatagramSocket(port);
            System.out.println("Server ready for connections at port " + port);
            String userResp;
            final String regex = "^\\d+$";
            int num;
            JSONObject request;
            request = null;

            while(true){
                try{
                    while(true){                        
                        NetworkUtils.Tuple messageTuple = NetworkUtils.Receive(sock);
                        JSONObject msg = JsonUtils.fromByteArray(messageTuple.Payload);
                        JSONObject returnmsg = null;
                        
                        // Name handling
                        if (msg.has("name")) {
                            System.out.println("Received `name` response");
                            name = (String) msg.get("name");
                            System.out.println("Player's name: " + name);
                            returnmsg = greeting(name);
                            System.out.println("Sent greeting to user");
                        }

                        // Rounds handling
                        else if (msg.has("rounds")) {
                            System.out.println("Received `rounds` response");
                            rounds = (Integer) msg.get("rounds");
                            timer = 30 * rounds;
                            System.out.println("Rounds: " + rounds);
                            System.out.println("Timer: " + timer);
                            returnmsg = roundStart(name, rounds);
                            System.out.println("Sent start prompt msg to user");
                        }

                        // Start handling
                        else if (msg.has("start")) {
                            System.out.println("Received `start` response");
                            st = new startTimer(timer); // Start timer
                            returnmsg = randomImg();
                            --rounds;
                            ans = (String) returnmsg.get("answer");
                            imgid = (Integer) returnmsg.get("image-id");
                            zoomid = (Integer) returnmsg.get("image-zoom");
                            System.out.println("Answer: " + ans);
                            System.out.println("Image id: " + imgid);
                            System.out.println("Zoom id: " + zoomid);
                        }

                        // Guesses handling
                        else if (msg.has("guess") && timeOk) {
                            System.out.println("Received `guess` response");
                            if (msg.get("guess").equals(ans)) {
                                System.out.println("Winning guess!");
                                if (rounds > 0) {
                                    returnmsg = randomImg();
                                    --rounds;
                                    ans = (String) returnmsg.get("answer");
                                    imgid = (Integer) returnmsg.get("image-id");
                                    zoomid = (Integer) returnmsg.get("image-zoom");
                                    System.out.println("Sent image to user with properties: ");
                                    System.out.println("Answer: " + ans);
                                    System.out.println("Image id: " + imgid);
                                    System.out.println("Zoom id: " + zoomid);
                                    System.out.println("Answer: " + ans);

                                } else {
                                    returnmsg = winner(name);
                                    st.stopTimer();
                                    System.out.println("Sent winning msg to user");
                                    // Exit?
                                }
                            } else if (rounds == 0) {
                                returnmsg = loser();
                                st.stopTimer();
                                System.out.println("Sent losing msg to user");

                            } else {
                                System.out.println("Not a winning guess");
                                returnmsg = incorrect();
                                System.out.println("Sent incorrect msg to user");
                            }
                        }

                        // "More" option handling
                        else if (msg.has("more")) {
                            System.out.println("Received `more` response");
                            if (zoomid < 3) {
                                returnmsg = getMore(imgid, ++zoomid);
                            } else {
                                if (rounds > 0) {
                                    returnmsg = randomImg();
                                    --rounds;
                                    ans = (String) returnmsg.get("answer");
                                    imgid = (Integer) returnmsg.get("image-id");
                                    zoomid = (Integer) returnmsg.get("image-zoom");
                                    System.out.println("Sent image to user with properties: ");
                                    System.out.println("Answer: " + ans);
                                    System.out.println("Image id: " + imgid);
                                    System.out.println("Zoom id: " + zoomid);
                                    System.out.println("Answer: " + ans);
                                } else {
                                    returnmsg = loser();
                                    st.stopTimer();
                                    System.out.println("Sent losing msg to user");
                                    // Exit?
                                }
                            }
                        }

                        // Next handling
                        else if (msg.has("next")) {
                            System.out.println("Received `next` response");
                            if (rounds > 0) {
                                returnmsg = randomImg();
                                --rounds;
                                ans = (String) returnmsg.get("answer");
                                imgid = (Integer) returnmsg.get("image-id");
                                zoomid = (Integer) returnmsg.get("image-zoom");
                                System.out.println("Sent image to user with properties: ");
                                System.out.println("Answer: " + ans);
                                System.out.println("Image id: " + imgid);
                                System.out.println("Zoom id: " + zoomid);
                                System.out.println("Answer: " + ans);
                            } else {
                                returnmsg = loser();
                                st.stopTimer();
                                System.out.println("Sent losing msg to user");
                                // Exit?
                            }
                        }

                        // Quit handling
                        else if (msg.has("exit")) {
                            System.out.println("Received `exit` response");
                            returnmsg = goodbye(name);
                            System.out.println("Sent goodbye response to user");
                        }

                        // Error handling
                        else {
                            System.out.println("Received invalid msg");
                            returnmsg = error("Invalid message received");
                            System.out.println("Error response sent");
                        }

                        if (timeOk == false) {
                            returnmsg = loser2();
                            System.out.println("Sent losing(time) msg to user");
                        }                  

                        byte[] output = JsonUtils.toByteArray(returnmsg);
                        NetworkUtils.Send(sock, messageTuple.Address, messageTuple.Port, output);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sock != null) {
                sock.close();
            }
        } 
    }
}
