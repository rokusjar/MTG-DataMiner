package dataminer.parser;


import java.io.File;

public class CardSet {

    private String code;
    private String name;
    private Card[] cards;

    @Override
    public String toString() {
        return "CardSet{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Card[] getCards() {
        return cards;
    }

    public void setCards(Card[] cards) {
        this.cards = cards;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
