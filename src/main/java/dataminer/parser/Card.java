package dataminer.parser;


public class Card {

    private String name;
    private Integer setID;
    private String manaCost;
    private Integer cmc;
    private String[] colors;
    private String type;
    private String[] supertypes;
    private String[] types;
    private String[] subtypes;
    private String rarity;
    private String text;
    private String flavor;
    private String artist;
    private String power;
    private String toughness;
    private Integer multiverseid;
    private Byte[] image;

    public String getArtist() {
        return artist;
    }

    @Override
    public String toString() {
        return "Card{" +
                "name='" + name + '\'' +
                ", multiverseid=" + multiverseid +
                '}';
    }



    public void setArtist(String artist) {
        this.artist = artist;
    }

    public Integer getCmc() {
        return cmc;
    }

    public void setCmc(Integer cmc) {
        this.cmc = cmc;
    }

    public String[] getColors() {
        return colors;
    }

    public void setColors(String[] colors) {
        this.colors = colors;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }

    public String getManaCost() {
        return manaCost;
    }

    public void setManaCost(String manaCost) {
        this.manaCost = manaCost;
    }

    public Integer getMultiverseid() {
        return multiverseid;
    }

    public void setMultiverseid(Integer multiverseid) {
        this.multiverseid = multiverseid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public String[] getSubtypes() {
        return subtypes;
    }

    public void setSubtypes(String[] subtypes) {
        this.subtypes = subtypes;
    }

    public String[] getSupertypes() {
        return supertypes;
    }

    public void setSupertypes(String[] supertypes) {
        this.supertypes = supertypes;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getToughness() {
        return toughness;
    }

    public void setToughness(String toughness) {
        this.toughness = toughness;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String[] getTypes() {
        return types;
    }

    public void setTypes(String[] types) {
        this.types = types;
    }

    public Integer getSetID() {
        return setID;
    }

    public void setSetID(Integer setID) {
        this.setID = setID;
    }
}
