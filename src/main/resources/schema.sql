CREATE TABLE Kinobillett
(
    film VARCHAR(255) NOT NULL,
    antall INTEGER (3) NOT NULL,
    fornavn VARCHAR(255) NOT NULL,
    etternavn VARCHAR(255) NOT NULL,
    tlf INTEGER(20) NOT NULL,
    epost VARCHAR(255) NOT NULL,
    PRIMARY KEY (tlf)
);