const mangelMld = "Skriv inn i manglende felt.";
const antMsg    = "Skriv inn antall mellom 1 og 99 i dette feltet.";
const tlfMsg    = "Skriv inn telefonnummer med 8 tall.";
const epostMsg  = "Skriv inn gyldig epostadresse.";


function registrerBillett(){
    //Henter verdier fra inputfeltene.
    const kinobillett = {
        film        : $("#film").val(),
        antall      : $("#antall").val(),
        fornavn     : $("#fornavn").val(),
        etternavn   : $("#etternavn").val(),
        tlf         : $("#telefonnr").val(),
        epost       : $("#epost").val()
    };

    const regTlf = new RegExp(/[0-9]{8}/);
    const regEpost = new RegExp(/^[\w!#$%&'*+/=?`{|}~^-]+(?:\.[\w!#$%&'*+/=?`{|}~^-]+)*@(?!-)(?:[a-zA-Z0-9-]+\.)+[a-zA-Z]{2,6}$/);


    let isValid = true;

    if(kinobillett.film.length === 0){
        $("#filmId").html(mangelMld);
        $('#film').addClass("input-invalid");
        isValid = false;
    }

    if(isNaN(kinobillett.antall)
        || kinobillett.antall <= 0
        || kinobillett.antall > 99) {
        $("#antallId").html(antMsg);
        $('#antall').addClass("input-invalid");
        isValid = false;
    }

    if(kinobillett.fornavn.length === 0){
        $("#fornavnId").html(mangelMld);
        $('#fornavn').addClass("input-invalid");
        isValid = false;
    }

    if(kinobillett.etternavn.length === 0){
        $("#etternavnId").html(mangelMld);
        $('#etternavn').addClass("input-invalid");
        isValid = false;
    }

    if(kinobillett.epost.length === 0
        || regEpost.test(kinobillett.epost) === false){
        $("#epostId").html(epostMsg);
        $('#epost').addClass("input-invalid");
        isValid = false;
    }

    if(isNaN(kinobillett.tlf)
        || kinobillett.tlf.length === 0
        || kinobillett.tlf.length !== 8
        || regTlf.test(kinobillett.tlf) === false){
        $("#telefonnrId").html(tlfMsg);
        $('#telefonnr').addClass("input-invalid");
        isValid = false;
    }

    //kinobillett sendes til PostMapping("/"), for å legges til i en List. PostMapping returnerer en melding om den ble registrert eller ikke.
    if(isValid) {
        $.post("/lagreBillett", kinobillett, function (melding) {
            lastInnBillett();
            clearInputfelter();
            $("#valideringsmld").html(melding);
            $("#valideringsmld").fadeIn(500);
            $('#epost').removeClass("input-invalid");
        });
    }
}

//metode som gjør at dersom vi refresher siden, så vil de lagrede billettene fortsatt
//vises i tabellen.
$(document).ready(function() {
    lastInnBillett();
});


//metode som blir kalt på når vi registrerer en billett. Denne metoden returnerer listen med billetter
//og kaller på en metode som får hvert element i listen formatert og skrevet ut.
function lastInnBillett(){
    $.get("/lastBillett", function (billettListe){
        formaterBillettinfo(billettListe);
    })
}

//Blir kalt på at lastInnBillett. Lager informasjon fra billettlisten over til tabell-form og sender det ut på nettsiden.
function formaterBillettinfo(billettListe) {
    let tabell =
        "<table class='table'>" +
            "<tr class='bg-success'>" +
            "<th>Film</th>" +
            "<th>Antall</th>" +
            "<th>Fornavn</th>" +
            "<th>Etternavn</th>" +
            "<th>Telefonnummer</th>" +
            "<th>Epost</th>" +
        "</tr>";
    for (const kinobillett of billettListe) {
            tabell +=
                "<tr class='bg-info'>" +
                "<td>" + kinobillett.film       +"</td>" +
                "<td>" + kinobillett.antall     +"</td>" +
                "<td>" + kinobillett.fornavn    +"</td>" +
                "<td>" + kinobillett.etternavn  +"</td>" +
                "<td>" + "+47 "+kinobillett.tlf +"</td>" +
                "<td>" + kinobillett.epost      +"</td>" +
                "</tr>";
        }
        tabell += "</table>";

    //hvis listen er tom, skal den ikke skrives ut i tabellen.
    //(for å unngå table-header med tom liste hver gang siden refreshes, ref. (document).ready-metoden.).
        if(billettListe.length > 0){
            $("#textfield").html(tabell);
        }
}

    //sletter listen og setter tabellen i tekstfeltet med tom liste.
    function slettBillett() {
        $.post("/slettBillett", function () {
            $("#textfield").html("");
        })
    }

    //sletter errormelding på tilknyttet elementId.
    function slettErrormelding(elementId) {
        $("#" + elementId).html("");
        $("#valideringsmld").val("");

    }

    //fader ut melding om billett er registrert eller om noe gikk galt.
    function slettValideringsmelding(){
        $("#valideringsmld").fadeOut(500);
    }

    //gjør alle feltene tomme.
    function clearInputfelter(){
        $("#film").val("");
        $("#antall").val("");
        $("#fornavn").val("");
        $("#etternavn").val("");
        $("#telefonnr").val("");
        $("#epost").val("");
    }

    //fjerner klassen som gir rød border på inputfeltene.
    function slettRedBorders(elementId){
        $("#" + elementId).removeClass("input-invalid");

    }
