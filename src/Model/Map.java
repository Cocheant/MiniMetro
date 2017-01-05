package Model;

import java.io.IOException;
import java.io.*;


/**
 * Created by hugo on 12/26/2016.
 */
public class Map {

    private int [] [] matrice;

    /**
     * default constructor
     */
    public Map(){
        try{
            load("DefaultPath");
        }catch(IOException e){

        }
    }

    /**
     * Initialization constructor
     * @param path the path of the map file
     */
    public Map(String path){
        try{
            load(path);
        }catch(IOException e){

        }
    }

    private void load(String path) throws IOException{
        FileReader f = null;

        try{
            f = new FileReader(path);
            int width;
            int height;
            width = f.read();
            height = f.read();

            this.matrice = new int[width][height];

            int c;

            for(int i=0; i<width; i++){
                for(int j=0; j<height; j++){
                    c = f.read();
                    matrice[i][j] = c;
                }
            }

        }catch(Exception e){

        }finally {
            f.close();
        }
    }
}
