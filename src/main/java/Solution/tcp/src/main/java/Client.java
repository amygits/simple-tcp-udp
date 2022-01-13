package Solution.tcp;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Base64;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.json.*;

public class Client {

    public static JSONObject rounds(int num) {
        JSONObject rnds = new JSONObject();
        rnds.put("rounds", num);
        return rnds;
    }

    public static JSONObject name(String name) {
        JSONObject username = new JSONObject();
        username.put("name", name);
        return username;
    }

    public static JSONObject start() {
        JSONObject start = new JSONObject();
        start.put("start", true);
        return start;
    }

    public static JSONObject response(String gues) {
        JSONObject guess = new JSONObject();
        guess.put("guess", gues);
        return guess;
    }

    public static JSONObject more() {
        JSONObject more = new JSONObject();
        more.put("more", true);
        return more;
    }

    public static JSONObject next() {
        JSONObject next = new JSONObject();
        next.put("next", true);
        return next;
    }

    public static JSONObject quit() {
        JSONObject quit = new JSONObject();
        quit.put("quit", true);
        return quit;
    }

    public static void main(String[] args) throws IOException {
        Socket sock;
        String host = "localhost";
        Integer port = 9000;

        if (args.length > 0) {
            /*for(int index = 0; index < args.length; index++){
                System.out.println("arg " + index + ": " + args[index]);
            }*/
            if (args[0] != null) {// host, if provided                
                host = args[0];
            }
            if (args.length > 1) {
                if (args[1] != null) {
                    //System.out.println("arg 1 " + args[1]);
                    port = Integer.valueOf(args[1]);
                }

                if (args.length > 2) {
                    System.out.println("Extra arguments disregarded");
                }
            }
        }

        try {
            System.out.println("host: " + host);
            System.out.println("port: " + port);
            sock = new Socket(host, port);
            System.out.println("Successfully connected to server " + host + " @ " + port);
            OutputStream out = sock.getOutputStream();
            InputStream in = sock.getInputStream();
            Scanner input = new Scanner(System.in);
            final String regex = "^\\d+$";
            String userResp;
            int num;

            JSONObject request = null;

            do {

                if (request == null) {
                    /*
                     * byte[] responseBytes = NetworkUtils.Receive(in);
                     * System.out.println("Received something from server"); JSONObject response =
                     * JsonUtils.fromByteArray(responseBytes);
                     * 
                     * if (response.getString("type").equals("bad-request")) {
                     * System.out.println(response.getString("payload")); break; }
                     */

                    System.out.println("Welcome!  Please enter your name: ");
                    userResp = input.nextLine();
                    request = name(userResp);
                }

                if (request != null) {
                    NetworkUtils.Send(out, JsonUtils.toByteArray(request));
                    System.out.println("Sent something to server");
                    byte[] responseBytes = NetworkUtils.Receive(in);
                    System.out.println("Received something from server");
                    JSONObject response = JsonUtils.fromByteArray(responseBytes);
                    if (response.has("error")) {
                        System.out.println(response.getString("error"));
                    } else {
                        switch (response.getInt("order")) {

                        // First name + Greeting display
                        case (1):
                            System.out.println("Type: " + response.getString("type"));
                            System.out.println(response.getString("payload"));
                            break;

                        // Start prompt display
                        case (2):
                            System.out.println("Type: " + response.getString("type"));
                            System.out.println(response.getString("payload"));
                            break;

                        // Display image for user to guess
                        case (3): {
                            System.out.println("Your image");
                            Base64.Decoder decoder = Base64.getDecoder();
                            byte[] bytes = decoder.decode(response.getString("data"));
                            ImageIcon icon = null;
                            try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes)) {
                                BufferedImage image = ImageIO.read(bais);
                                icon = new ImageIcon(image);
                            }
                            if (icon != null) {
                                JFrame frame = new JFrame();
                                JLabel label = new JLabel();
                                label.setIcon(icon);
                                frame.add(label);
                                frame.setSize(icon.getIconWidth(), icon.getIconHeight());
                                frame.show();
                            }
                        }
                            break;

                        // Incorrect display
                        case (4):
                            System.out.println("Type: " + response.getString("type"));
                            System.out.println(response.getString("payload"));
                            break;

                        // Loser / Winner display
                        case (5): {
                            System.out.println(response.getString("payload"));
                            Base64.Decoder decoder = Base64.getDecoder();
                            byte[] bytes = decoder.decode(response.getString("data"));
                            ImageIcon icon = null;
                            try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes)) {
                                BufferedImage image = ImageIO.read(bais);
                                icon = new ImageIcon(image);
                            }
                            if (icon != null) {
                                JFrame frame = new JFrame();
                                JLabel label = new JLabel();
                                label.setIcon(icon);
                                frame.add(label);
                                frame.setSize(icon.getIconWidth(), icon.getIconHeight());
                                frame.show();
                            }
                        }
                            request = null;
                            continue;

                        // Goodbye display
                        case (7):
                            System.out.println("Type: " + response.getString("type"));
                            System.out.println(response.getString("payload"));
                            break;
                        }
                    }
                }

                userResp = input.nextLine();
                request = null;

                if (userResp.matches(regex)) {
                    int number = Integer.parseInt(userResp);
                    request = rounds(number);
                } else if (userResp.toLowerCase().equals("start")) {
                    request = start();
                } else if (userResp.toLowerCase().equals("more")) {
                    request = more();
                } else if (userResp.toLowerCase().equals("next")) {
                    request = next();
                } else if (userResp.toLowerCase().equals("quit")) {
                    request = quit();
                    break;
                } else {
                    request = response(userResp.toLowerCase());
                }
            } while (true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
