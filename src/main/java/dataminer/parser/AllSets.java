package dataminer.parser;


import java.util.ArrayList;
import java.util.List;

public class AllSets {

    List<CardSet> cardSets;

    public AllSets() {
        this.cardSets = new ArrayList<>();
    }

    public List<CardSet> getCardSets() {
        return cardSets;
    }

    public void add(CardSet cardSet){
        cardSets.add(cardSet);
    }

    public void remove(CardSet cardSet){
        cardSets.remove(cardSet);
    }

    public void setCardSets(List<CardSet> cardSets) {
        this.cardSets = cardSets;
    }
}
