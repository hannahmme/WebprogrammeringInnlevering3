package webprogrammeringinnlevering3.packages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
class KinobillettController {

    @Autowired
    private KinobillettRepository kbRep;


    //billett sendes fra klient-siden
    @PostMapping("/lagreBillett")
    public String arrayFiller(Kinobillett billett) {
        if (Kinobillett.billettIsValid(billett)) {

            //generer en billettID
            String billettID = Kinobillett.genererBillettID();

            //billetten tilegnes billettID
            billett.setbillettID(billettID);

            //lagrer billetten i databasen
            kbRep.lagreBillett(billett);
            return "Billetten er registrert!";
        } else {
            return "Noe gikk galt ved kjøp av billett. Prøv igjen.";
        }
    }

    //metode som sletter en billett basert på billettID
    @GetMapping("/slettEnBillett")
    public void slettEnBillett(String billettID){
        kbRep.slettEnBillett(billettID);
    }

    //kall fra klient-siden om å slette alle billetter.
    //Sender kall videre i backend som sletter alle billettene i databasen
    @PostMapping("/slettAlleBilletter")
    public void slettAlleBilletter(){ kbRep.slettAlleBilletter(); }

    //metode som sender et kall på metoden i repository-klassen,
    //og returnerer alle billettene fra databasen
    @GetMapping("/lastAlleBilletter")
    public List<Kinobillett> lastAlleBilletter(){
        return kbRep.lastAlleBilletter();
    }

}