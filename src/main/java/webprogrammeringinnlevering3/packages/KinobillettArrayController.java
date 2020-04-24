package webprogrammeringinnlevering3.packages;

import org.aspectj.weaver.patterns.KindedPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
class KinobillettController {

    @Autowired
    private KinobillettRepository kbRep;

    @PostMapping("/lagreBillett")
    public String arrayFiller(Kinobillett billett) {
        if (Kinobillett.billettIsValid(billett)) {
            kbRep.lagreBillett(billett);
            return "Billetten er registrert!";
        } else {
            return "Noe gikk galt ved kjøp av billett. Prøv igjen.";
        }
    }

    @PostMapping("/slettBillett")
    public void slettBillett(){
        kbRep.slettBillett();
    }

    @GetMapping("/lastBillett")
    public List<Kinobillett> lastBillett(){
        return kbRep.lastBillett();
    }
}