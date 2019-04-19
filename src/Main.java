import java.sql.Timestamp;

public class Main {
    public static void main(String[] args) {
        GebruikersApplicatieMetProxy gebruikersApplicatieMetProxy = new GebruikersApplicatieMetProxy();
//        gebruikersApplicatieMetProxy.startGebruikersapplicatie();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        gebruikersApplicatieMetProxy.loopGebruikersApplicatie(3000);
        Timestamp timestamp2 = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp);
        System.out.println(timestamp2);
    }
}
