package lib;

/**
 * Created by Alexis Espinoza on 12/28/15.
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Properties;

public class FileHandler {
    /**
     * This method reads input text from a file and loads each line in a ArrayList
     *
     * @param  propertyFileName Name of property file to read(Defined in properties )
     * @return ArrayList Ecah sentence will be a row in the ArrayList
     */
    public static ArrayList<String> FileRead(String propertyFileName) {
        String line=null;
        ArrayList<String> arrayList = new ArrayList<>();
        Properties prop = FileHandler.propertyReader();
        if (!prop.isEmpty()) {
            String fileName=prop.getProperty(propertyFileName);
            String acceptableFileFormats=prop.getProperty("acceptableFileFormats");
            String fileFormat=getFileFormat(fileName);
            //We first check for the format of the file it should be compliant with what's defined in the properties
            if(acceptableFileFormats.toLowerCase().contains(fileFormat.toLowerCase())) {
                try {
                    // Open the file for reading.
                    FileInputStream f = new FileInputStream(fileName);
                    // Read all contents of the file.
                    try {
                        BufferedReader in = new BufferedReader(new InputStreamReader(f));
                        while ((line = in.readLine()) != null) {
                            arrayList.add(line);// adding a line to the ArrayList
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (FileNotFoundException e) {  //in case the file cannot be found
                    System.out.println(fileName + " not found in path. Make sure the file can at least be read");
                }
            }else {
                System.out.println(fileFormat+ " is an invalid file format. Formats allowed are "+acceptableFileFormats);
            }
        }else{
            System.out.println("Input file not found"); //Check the name for the properties file in propertyReader method
        }
        return arrayList;
    }
    /**
     * Returns String with the format of the file
     *
     * @param fullPathFile contains the relative path to the file or simply the name  ile
     * @return String. File format
     */
    private static String getFileFormat(String fullPathFile){
        String fileName=null;
        String[] fileParts;
        int last;
        if((!fullPathFile.isEmpty())&&(fullPathFile.contains("/"))) {
            String[] folderParts = fullPathFile.split("/");
            if(folderParts.length>0) {
                last = folderParts.length-1;
                if(folderParts[last].contains(".")) {
                    fileParts = folderParts[last].split("\\.");
                    last = fileParts.length-1;
                    fileName=fileParts[last];
                }
            }

        }else if (fullPathFile.contains(".")){
            fileParts=fullPathFile.split("\\.");
            last = fileParts.length-1;
            fileName=fileParts[last];
        }
        return fileName;
    }
    /**
     * Property file reader. Reads al variables located at  src/config/config.properties
     * do Properties.var to access the variables. var should be the name for the variable defined at the file
     * @return property variables set at src/config/config.properties
     */
    public static Properties propertyReader(){
        Properties prop = new Properties();
        InputStream input=null;
        try {
            input = new FileInputStream("src/config/config.properties"); //reads properties
            prop.load(input);
        }catch(IOException e ){ //In case the properties cannot be read for some reason
            //e.printStackTrace();// Activate in case you want to see the verbose stack
        }finally { // This will alwalys get executed no matter what.
            if (input != null) {
                try {
                    input.close();
                }catch (IOException e) {
                    // e.printStackTrace(); //Activate in case you want to see the verbose stack
                }
            }
        }
        return prop;
    }
}
