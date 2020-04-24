package webprogrammeringinnlevering3.packages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class KinobillettRepository {

    @Autowired
    private JdbcTemplate database;

    public void lagreBillett(Kinobillett kinobillett){
        String sql = "INSERT INTO Kinobillett VALUES(?,?,?,?,?,?)";
        database.update(sql, kinobillett.getFilm(), kinobillett.getAntall(),
        kinobillett.getFornavn(), kinobillett.getEtternavn(),
                kinobillett.getTlf(), kinobillett.getEpost());

    }

    public List<Kinobillett> lastBillett(){
        String sql = "SELECT * FROM Kinobillett";
        List<Kinobillett> alleBilletter = database.query(sql, new BeanPropertyRowMapper(Kinobillett.class));
        return alleBilletter;
    }

    public void slettBillett(){
        String sql = "DELETE FROM Kinobillett";
        database.update(sql);
    }
}
