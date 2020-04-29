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

    public void slettEnBillett(String ordreNr){
        String sql = "DELETE FROM Kinobillett WHERE ordrNr=?";
        database.update(sql, ordreNr);

    }

    public void lagreBillett(Kinobillett kinobillett){
        String sql = "INSERT INTO Kinobillett (film, antall, fornavn, etternavn, tlf, epost) VALUES(?,?,?,?,?,?)";
        database.update(sql, kinobillett.getFilm(), kinobillett.getAntall(),
        kinobillett.getFornavn(), kinobillett.getEtternavn(),
                kinobillett.getTlf(), kinobillett.getEpost());


    }

    public List<Kinobillett> lastBillett(){
        String sql = "SELECT * FROM Kinobillett";
        return database.query(sql, new BeanPropertyRowMapper(Kinobillett.class));

    }

    public void slettBillett(){
        String sql = "DELETE FROM Kinobillett";
        database.update(sql);
    }
}
