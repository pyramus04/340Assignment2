package com.csc340.restapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestApiApplication.class, args);
        getRandomRecipe();
        System.exit(0);
    }

   /*
    Gets a random recipe and prints its info to the console
    */
    
    public static void getRandomRecipe() {
        
        try{
            String url = "https://www.themealdb.com/api/json/v1/1/random.php";
        
            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();

            String jSonFact = restTemplate.getForObject(url, String.class);
            JsonNode root = mapper.readTree(jSonFact);

            String recipeName = root.findValue("strMeal").asText();
            System.out.println("\nRecipe Name: " + recipeName);
        
            int ingredNum = 1;
        
            String ingredient = "default";
       
        System.out.println("\nIngredients: \n");
       
       while (ingredient != "") 
       {
           String ingredStr = "strIngredient";
           ingredStr = ingredStr + ingredNum;
           
           ingredient = root.findValue(ingredStr).asText();
           
           if (ingredient != "") 
           {
                System.out.println(ingredNum + ". " + ingredient);
           }
           
           ingredNum += 1;
           
       }
       
        
        String recipe = root.findValue("strInstructions").asText();
        
        System.out.println("\nThe Recipe:\n" + recipe);
        } 
        catch (JsonProcessingException ex) {
            Logger.getLogger(RestApiApplication.class.getName()).log(
                Level.SEVERE,
                null, ex);
            System.out.println("error in getCatFact");

        }
        
        
    }

}
