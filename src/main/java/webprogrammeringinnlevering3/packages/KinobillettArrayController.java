package webprogrammeringinnlevering3.packages;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
class KinobillettController {
    private List<Kinobillett> kinobillettArrayList = new ArrayList<>();

    public List<Kinobillett> getKinobillettArrayList() {
        return kinobillettArrayList;
    }

    @PostMapping("/lagreBillett")
    public String arrayFiller(Kinobillett billett) {
        if (Kinobillett.billettIsValid(billett)) {
            getKinobillettArrayList().add(billett);
            return "Billetten er registrert!";
        } else {
            return "Noe gikk galt ved kjøp av billett. Prøv igjen.";
        }
    }

    @PostMapping("/slettBillett")
    public void slettBillett(){
        getKinobillettArrayList().clear();
    }

    @GetMapping("/lastBillett")
    public List<Kinobillett> lastBillett(){
        return getKinobillettArrayList();
    }
}