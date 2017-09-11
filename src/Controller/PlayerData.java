package Controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by dianazhang on 2017/4/24.
 */
public class PlayerData {

    // player's name, score and level
    private String pName;
    private int pScore = 0;
    private int level;
    public File file;
    //public String jsonPath = "/Users/dianazhang/hzhan107/Final_Project_2/src/test.json";

    public PlayerData(String name, int level){
        this.pName = name;
        this.level = level;
        file = new File("/Users/dianazhang/hzhan107/Final_Project_3/src/test.json");

    }

    public String getpName(){
        return this.pName;
    }

    public int getpScore(){
        return pScore;
    }

    public void setPScore(int score){
        this.pScore = score;
    }

    public int getLevel(){
        return this.level;
    }

    public void writeData() {
        JSONObject player = new JSONObject();
        player.put("Name", this.pName);
        player.put("Score", this.pScore);

        JSONArray playerList = new JSONArray();
        playerList.add(player);

        JSONObject root = new JSONObject();
        root.put("playerList", playerList);

        try(PrintWriter writer = new PrintWriter(file)){
            writer.print(root.toJSONString());
        }catch (FileNotFoundException ex){
            System.out.println(ex.toString());
        }
        System.out.println(root.toJSONString());

    }

    public void readData(){

    }

    public void setpName(String pName){
        this.pName = pName;
    }

}

// Source: https://www.tutorialspoint.com/json/json_java_example.htm
// https://www.mkyong.com/java/json-simple-example-read-and-write-json/