package dataminer;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dataminer.config.Config;
import dataminer.database.CardRepository;
import dataminer.database.CardSetRepository;
import dataminer.parser.*;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.spi.StringArrayOptionHandler;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    @Option(name = "-files", handler = StringArrayOptionHandler.class, usage = "Specifies one or more files to process.")
    private List<String> files;

    @Option(name = "-list", usage = "Lists all imported card sets.")
    private boolean list;

    @Option(name = "-all", usage = "Says that AllSets.json file is being used.")
    private boolean all;

    @Option(name = "-info", usage = "Card names will be written to console.")
    private boolean info;

    public static void main(String[] args) {
        new Main().doMain(args);
    }

    public void doMain(String[] args){

        CmdLineParser cmdLineParser = new CmdLineParser(this);
        try{
            cmdLineParser.parseArgument(args);

            if(list){
                list();
                return;
            }

            for(String f : files){
                System.out.println("IMPORTING FILE: " + f);
                List<CardSet> cardSets = parseJson(new File(f));
                for(CardSet cardSet : cardSets) {
                    if (alreadyImported(cardSet)) {
                        System.out.println("CARD SET: " + cardSet.getName() + " ALREADY IMPORTED - SKIPING");
                        continue;
                    }
                    insertCardSet(cardSet);

                }
                System.out.println("FILE " + f + " IMPORTED SUCCESSFULY");
            }
            System.out.println("------------------------------------------------------");
            System.out.println("ALL FILES IMPORTED SUCCESSFULY");

        } catch (CmdLineException e) {
            System.out.println("ERROR OCCURED WHEN PARSING COMMAND LINE ARGUMENTS");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("ERROR OCCURED DURING PARSING JSON FILE OR READING CONFIGURATION FILE!!!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("DATABASE ERROR OCCURED WHEN IMPORTING CARDS TO DATABASE!!!");
            e.printStackTrace();
        }
    }

    /**
     * List all card sets in database
     */
    private void list() throws IOException, SQLException {
        CardSetRepository cardSetRepository = new CardSetRepository();
        for(String cardSetName : cardSetRepository.getNamesAll()){
            System.out.println(cardSetName);
        }
    }

//    private CardSet parseJson(File file) throws IOException{
//
//        CardSet cardSet = null;
//        try (
//                FileInputStream fis = new FileInputStream(file);
//                InputStreamReader reader = new InputStreamReader(fis, "UTF-8")
//        ) {
//            GsonBuilder gsonBuilder = new GsonBuilder();
//            gsonBuilder.registerTypeAdapter(CardSet.class, new CardSetDeserializer());
//            Gson gson = gsonBuilder.create();
//            cardSet = gson.fromJson(reader, CardSet.class);
//            return cardSet;
//        }
//    }

    private List<CardSet> parseJson(File file) throws IOException {

        List<CardSet> sets = new ArrayList<>();
        try (
                FileInputStream fis = new FileInputStream(file);
                InputStreamReader reader = new InputStreamReader(fis, "UTF-8")
        ) {
            GsonBuilder gsonBuilder = new GsonBuilder();
            if (all) {
                AllSets allSets = null;
                gsonBuilder.registerTypeAdapter(AllSets.class, new AllSetsDeserializer());
                Gson gson = gsonBuilder.create();
                allSets = gson.fromJson(reader, AllSets.class);
                return allSets.getCardSets();
            } else {
                CardSet cardSet = null;
                gsonBuilder.registerTypeAdapter(CardSet.class, new CardSetDeserializer());
                Gson gson = gsonBuilder.create();
                cardSet = gson.fromJson(reader, CardSet.class);
                ArrayList<CardSet> oneSet = new ArrayList<>();
                oneSet.add(cardSet);
                return oneSet;
            }
        }
    }

    private void insertCardSet(CardSet cardSet) throws IOException, SQLException {

        CardSetRepository cardSetRepository = new CardSetRepository();
        CardRepository cardRepository = new CardRepository();
        cardSetRepository.insert(cardSet);
        for (Card c : cardSet.getCards()) {
            c.setSetID(cardSetRepository.getDbId(cardSet));
            if(info) System.out.println("Inserting card:" + c);
            cardRepository.insert(c);
        }
    }

    private Boolean alreadyImported(CardSet cardSet) throws IOException, SQLException {
        CardSetRepository cardSetRepository = new CardSetRepository();
        if(cardSetRepository.getName(cardSet) != null){
            return true;
        }else{
            return false;
        }
    }
}