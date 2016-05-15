package dataminer.config;


import java.io.*;

public class Config {

    public static String getDbName() throws IOException {
        String value = getValue("dbname");
        if(value != null) {
            return value;
        }
        else{
            throw new ParamNotFoundException("Parameter dbname not found!");
        }
    }

    public static String getDbPassword() throws IOException {
        String value = getValue("dbpassword");
        if(value != null) {
            return value;
        }
        else{
            throw new ParamNotFoundException("Parameter dbpassword not found!");
        }
    }

    public static String getDbUser() throws IOException {
        String value = getValue("dbuser");
        if(value != null) {
            return value;
        }
        else{
            throw new ParamNotFoundException("Parameter dbuser not found!");
        }
    }

    public static String getHost() throws IOException {
        String value = getValue("host");
        if(value != null) {
            return value;
        }
        else{
            throw new ParamNotFoundException("Parameter host not found!");
        }
    }

    public static String getPort() throws IOException {
        String value = getValue("port");
        if(value != null) {
            return value;
        }
        else{
            throw new ParamNotFoundException("Parameter port not found!");
        }
    }

    /**
     * Goes threw config.txt and looking for parameter value specified by key
     * @param key
     * @return value or null if not found
     * @throws IOException
     */
    private static String getValue(String key) throws IOException{

        try(
                InputStream fis = Config.class.getResourceAsStream("/config.txt");
                InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
                BufferedReader br = new BufferedReader(isr);
                ){
            String line;
            while((line = br.readLine()) != null){
                if(line.contains(key)){
                    return line.split("=")[1];
                }
            }
            return null;
        }
    }
}
