package dataminer.database;


import dataminer.config.Config;
import dataminer.parser.Card;
import org.postgresql.util.PSQLException;

import java.io.IOException;
import java.sql.*;
import java.util.Arrays;

public class CardRepository {

    public void insert(Card card) throws SQLException, IOException {

        try(Connection connection = connect();
            PreparedStatement ps = createCardInsertStatement(connection, card);
            ){
            ps.executeUpdate();
        }
    }

    private PreparedStatement createCardInsertStatement(Connection connection, Card card) throws SQLException{

        String insert = "";
        insert += "INSERT INTO card (";
        insert += "name, set_id, manacost, cmc, colors, type, supertypes, types, subtypes, ";
        insert += "rarity, text, flavor, artist, power, toughness, multiverseid )";
        insert += "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement ps = connection.prepareStatement(insert);
        ps.setString(1, card.getName());
        ps.setInt(2, card.getSetID());

        if(card.getManaCost() == null){
            ps.setNull(3, Types.VARCHAR);
        }else{
            ps.setString(3, card.getManaCost());
        }
        if(card.getCmc() == null){
            ps.setNull(4, java.sql.Types.INTEGER);
        }else{
            ps.setInt(4, card.getCmc());
        }
        if(card.getColors() == null){
            ps.setNull(5, Types.VARCHAR);
        }else{
            ps.setString(5, Arrays.toString(card.getColors()));
        }
        if(card.getType() == null){
            ps.setNull(6, Types.VARCHAR);
        }else{
            ps.setString(6, card.getType());
        }
        if(card.getSupertypes() == null){
            ps.setNull(7, Types.VARCHAR);
        }else{
            ps.setString(7, Arrays.toString(card.getSupertypes()));
        }
        if(card.getTypes() == null){
            ps.setNull(8, Types.VARCHAR);
        }else{
            ps.setString(8, Arrays.toString(card.getTypes()));
        }
        if(card.getSubtypes() == null){
            ps.setNull(9, Types.VARCHAR);
        }else{
            ps.setString(9, Arrays.toString(card.getSubtypes()));
        }
        if(card.getRarity() == null){
            ps.setNull(10, Types.VARCHAR);
        }else{
            ps.setString(10, card.getRarity());
        }
        if(card.getText() == null){
            ps.setNull(11, Types.VARCHAR);
        }else{
            ps.setString(11, card.getText());
        }
        if(card.getFlavor() == null){
            ps.setNull(12, Types.VARCHAR);
        }else{
            ps.setString(12, card.getFlavor());
        }
        if(card.getArtist() == null){
            ps.setNull(13, Types.VARCHAR);
        }else {
            ps.setString(13, card.getArtist());
        }
        if(card.getPower() == null){
            ps.setNull(14, Types.VARCHAR);
        }else{
            ps.setString(14, card.getPower());
        }
        if(card.getToughness() == null){
            ps.setNull(15, Types.VARCHAR);
        }else{
            ps.setString(15, card.getToughness());
        }
        if(card.getMultiverseid() == null){
            ps.setNull(16, Types.INTEGER);
        }else{
            ps.setInt(16, card.getMultiverseid());
        }
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
