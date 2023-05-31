package co.uk.Murphy.Jack;

import java.text.ParseException;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Comparator;

public class Shares_Traded {


    public static void main(String[] args) {

        String data = "CFLT 100,MSFT 2000,IBM 900,TOP 3,CFLT 1200,TOP 1,AAPL 6000,TOP 2,CFLT 400,MSFT 1500,AAPL 2000,IBM 1000,CFLT 600,CFLT 500,IBM 2000,CFLT 2000,TOP 4";

        String records[] = data.split(",");

        ProcessStream(records);
    }


    private static void ProcessStream(String[] records){

        int index = 0;
        Dictionary<String, Integer> SharesTraded= new Hashtable<>();

        // This for loop processes every share entry as it comes in, it diverges if the key is Top
        for (String i : records) {

            String company = records[index].split(" ")[0];

            String trade = records[index].split(" ")[1];

            if (!(company.equals("TOP")) ){ //



                if (SharesTraded.get(company) == null){ // create the first trade value for the company
                    SharesTraded.put(company, (Integer.parseInt(trade)));


                } else {
                    SharesTraded.put(company, (SharesTraded.get(company) + Integer.parseInt(trade)));
                }
                System.out.println(company + " " + trade);


            } else {

                PrintTopX(SharesTraded, trade);

            }
            index += 1;

        }
    }
    private static void PrintTopX(Dictionary<String, Integer> SharesTraded, String iterations){

        int interations_int = Integer.parseInt(iterations);

        // Making the dictionary into a list so that it may be ordered from highest to lowest

        // This turns the dictionary into a list (see SharesTraded at the end)
        List<Map.Entry<String, Integer>> MostTrades = new ArrayList<>(((Hashtable<String, Integer>) SharesTraded).entrySet());

        //This sorts the list, although I don't fully understand this line
        Collections.sort(MostTrades, Comparator.comparing(Map.Entry<String, Integer>::getValue).reversed());

        // This object helps in the creation of strings
        StringBuilder result = new StringBuilder();

        // This is complicated, but in sum it creates the string by mapping each key:value pair to the string
        for (int count = 0; count <= interations_int && count < MostTrades.size(); count++) {
            Map.Entry<String, Integer> entry = MostTrades.get(count);
            result.append(entry.getKey()).append(" ").append(entry.getValue()).append(", ");
        }

        // previously
        //for (Map.Entry<String, Integer> entry : MostTrades) {
        //    result.append(entry.getKey()).append(" ").append(entry.getValue()).append(", ");
        //}

        // This removes the trailing comma and space
        if (result.length() > 2) {
            result.setLength(result.length() - 2);
        }

        //
        String TopXShares = result.toString();

        System.out.println(TopXShares);
    }
}




// Next steps:

// This program actually needs to print the current share purchase/sale, not the current total for the company.
// Quick fix, methinks.



// Questions:

// By convention do methods go above or below main?

// Are my methods in the right order?
