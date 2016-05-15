package dataminer.database;


import dataminer.config.Config;
import dataminer.parser.CardSet;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CardSetRepository {

    public void insert(CardSet cardSet) throws SQLException, IOException {
        System.out.println("Inserting set:" + cardSet);
        try(Connection connection = connect();
            PreparedStatement ps = createCardSetInsertStatement(connection, cardSet);
        ){
            ps.executeUpdate();
        }
    }

    public List<String> getNamesAll() throws SQLException, IOException {
        try(Connection connection = connect();
            PreparedStatement ps = createGetNameAllStatement(connection);
            ResultSet rs = ps.executeQuery()){

            ArrayList<String> names = new ArrayList<>();
            while (rs.next()){
                names.add(rs.getString(1));
            }
            return names;
        }
    }

    /**
     *
     * @return
     * @throws SQLException
     * @throws IOException
     */
    public String getName(CardSet cardSet) throws SQLException, IOException {
        try(Connection connection = connect();
            PreparedStatement ps = createGetNameStatement(connection, cardSet);
            ResultSet rs = ps.executeQuery()){

            if(rs.next()) {
                return rs.getString(1);
            }else{
                return null;
            }
        }
    }

    public Integer getDbId(CardSet cardset) throws IOException, SQLException {
        try(Connection connection = connect();
            PreparedStatement ps = createGetIdStatement(connection, cardset);
            ResultSet rs = ps.executeQuery()
        ){
            rs.next();
            return rs.getInt(1);
        }
    }

    private PreparedStatement createGetNameStatement(Connection connection, CardSet cardset) throws SQLException {
        String select = "SELECT name FROM cardset WHERE name = ?";
        PreparedStatement ps = connection.prepareStatement(select);
        ps.setString(1, cardset.getName());
        return ps;
    }

    private PreparedStatement createGetIdStatement(Connection connection, CardSet cardset) throws SQLException {
        String select = "SELECT id FROM cardset WHERE name = ?";
        PreparedStatement ps = connection.prepareStatement(select);
        ps.setString(1, cardset.getName());
        return ps;
    }

    private PreparedStatement createCardSetInsertStatement(Connection connection, CardSet cardSet) throws SQLException{
        String insert = "INSERT INTO cardset (name) VALUES (?)";
        PreparedStatement ps = connection.prepareStatement(insert);
        ps.setString(1, cardSet.getName());
        return ps;
    }

    private PreparedStatement createGetNameAllStatement(Connection connection) throws SQLException{
        String select = "SELECT name FROM cardset ORDER BY name";
        PreparedStatement ps = connection.prepareStatement(select);
        return ps;
    }

    private Connection connect() throws IOException, SQLException {
        String url = String.format("jdbc:postgresql://%s:%s/%s",
                Config.getHost(), Config.getPort(), Config.getDbName());
        String user = Config.getDbUser();
        String pass = Config.getDbPassword();
        return DriverManager.getConnection(url, user, pass);
    }
}
