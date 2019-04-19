import com.onsdomein.proxy.ProxyOnsDomein;
import java.io.IOException;
import java.util.Scanner;

public class GebruikersApplicatieMetProxy {
    Scanner in = new Scanner(System.in);
    ProxyOnsDomein proxyOnsDomein = new ProxyOnsDomein();
    String myId = "1234";

    /**
     * Method used for testing server with commandline
     */
    public void startGebruikersapplicatie() {
        System.out.println("Met welke HC wil je verbiding maken?\n");
        while (in.hasNext()) {
            String response;
            String requestFor = in.nextLine();
            System.out.println("setHc / getHc / setConfig / getConfig / setState / getState ?");
            String command = in.nextLine();
            System.out.println("What message do you want to send?\n");
            String message = in.nextLine();
            try {
                proxyOnsDomein.connectClientToServer(myId);
                response = proxyOnsDomein.sendRequest(command, myId, requestFor, message);
                System.out.println(response);

//                listenForReaction();
                proxyOnsDomein.closeConnection();
            } catch (IOException e) {
                System.out.println("Bericht verzenden niet gelukt omdat de server offline is.\n");
            }
            System.out.println("Met welke HC wil je verbinding maken?\n");
        }
    }

    /**
     * Method to stress test the server
     * @param aantal, times to loop through this test.
     */
    public void loopGebruikersApplicatie(int aantal) {
        for (int i = 1; i <= aantal; i++) {
            String response = "";
            try {
                if (i % 100 == 0) {
                    proxyOnsDomein.connectClientToServer(myId);
                    response = proxyOnsDomein.sendRequest("setConfig", myId, "5678", "config:data:" + i);
                } else if (i % 100 == 1) {
                    proxyOnsDomein.connectClientToServer(myId);
                    response = proxyOnsDomein.sendRequest("getConfig", myId, "5678", "config:data:" + i);
                } else {
                    proxyOnsDomein.connectClientToServer(myId);
                    response = proxyOnsDomein.sendRequest("setHc", myId, "5678", "setPortOfHc" + i);

                    proxyOnsDomein.closeConnection();
                }
            } catch (IOException e) {
                System.out.println("er gaat hier iets niet goed: " + e);
            }
            System.out.println(response);
        }
    }
}
