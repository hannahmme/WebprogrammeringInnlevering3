package webprogrammeringinnlevering3.packages;

public class Kinobillett {
    private String film;
    private int antall;
    private String fornavn;
    private String etternavn;
    private String tlf;
    private String epost;

    public Kinobillett(String inFilm, int inAntall, String inFornavn, String inEtternavn, String inTlf, String inEpost){
        this.film = inFilm;
        this.antall = inAntall;
        this.fornavn = inFornavn;
        this.etternavn = inEtternavn;
        this.tlf = inTlf;
        this.epost = inEpost;
    }

    public Kinobillett(){}

    public String getFilm() { return film; }

    public void setFilm(String film) { this.film = film; }

    public int getAntall() { return antall; }

    public void setAntall(int antall) { this.antall = antall; }

    public String getFornavn() { return fornavn; }

    public void setFornavn(String fornavn) { this.fornavn = fornavn; }

    public String getEtternavn() { return etternavn; }

    public void setEtternavn(String etternavn) { this.etternavn = etternavn; }

    public String getTlf() { return tlf; }

    public void setTlf(String tlf) { this.tlf = tlf; }

    public String getEpost() { return epost; }

    public void setEpost(String epost) { this.epost = epost; }

    private static String regexTlf = "[0-9]{8}";
    private static String regexEpost = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?!-)(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

    //validering på serversiden for å forhindre ugyldige inputverdier fra klientsiden.
    public static boolean billettIsValid(Kinobillett billett){
        if(billett.getAntall() > 99
            || billett.getAntall() <= 0){
            return false;
        }

        if(billett.getFornavn().isEmpty()
            || billett.getFornavn().isBlank()){
            return false;
        }

        if(billett.getEtternavn().isEmpty()
            || billett.getEtternavn().isBlank()){
            return false;
        }

        if(!billett.getEpost().matches(regexEpost)){
            return false;
        }

        if(!billett.getTlf().matches(regexTlf)
            || billett.getTlf().isEmpty()
            || billett.getTlf().isBlank()){
            return false;
        }

        if(billett.getFilm().isBlank()
            || billett.getFilm().isEmpty()){
            return false;
        }

        return true;
    }
}

