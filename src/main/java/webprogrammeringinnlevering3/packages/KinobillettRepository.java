package webprogrammeringinnlevering3.packages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

@Repository
public class KinobillettRepository {

    @Autowired
    private JdbcTemplate database;

    //metode som sletter en bestemt billett basert på ordrenummeret som er generert i backend.
    public void slettEnBillett(String billettID){
        String sql = "DELETE FROM Kinobillett WHERE billettID=?";
        database.update(sql, billettID);
    }

    //metode som lagrer en kinobillett i databasen ved hjelp av sql-spørring
    public void lagreBillett(Kinobillett kinobillett){
        String sql = "INSERT INTO Kinobillett (billettID, film, antall, fornavn, etternavn, tlf, epost) VALUES(?,?,?,?,?,?,?)";
        database.update(sql, kinobillett.getbillettID(), kinobillett.getFilm(), kinobillett.getAntall(),
        kinobillett.getFornavn(), kinobillett.getEtternavn(),
                kinobillett.getTlf(), kinobillett.getEpost());
    }

    //metode som laster inn alle billetter fra databasen
    public List<Kinobillett> lastAlleBilletter(){
        String sql = "SELECT * FROM Kinobillett";
        return database.query(sql, new BeanPropertyRowMapper(Kinobillett.class));
    }

    //sletter alle billettene i databasen ved hjelp av sql-spørring "DELETE FROM"
    public void slettAlleBilletter(){
        String sql = "DELETE FROM Kinobillett";
        database.update(sql);
    }
}
