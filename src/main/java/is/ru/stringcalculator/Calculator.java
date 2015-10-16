package is.ru.stringcalculator;

import java.util.regex.*;

public class Calculator {

	public static int add(String text){


        String defDelimiter = ",";

        if(text.contains("//")){
            text = setDefaultDelimiter(text);
        }

        if(text.contains("-")){
            String negatives = parseNegatives(splitNumbers(text, defDelimiter));
            throw new IllegalArgumentException("Negatives not allowed: " + negatives);
        }

        if(text.equals("")){
			return 0;
		}
		else if(text.contains(defDelimiter) || text.contains("\n")){
            text = replaceNewLine(text, defDelimiter);
			return sum(splitNumbers(text, defDelimiter));
		}
		else
			return toInt(text);
	}

	private static int toInt(String number){
		return Integer.parseInt(number)%1000;
	}

	private static String[] splitNumbers(String numbers, String delimiter){
	    return numbers.split(delimiter);
	}
      
    private static int sum(String[] numbers){
 	    int total = 0;
        for(String number : numbers){
		    total += toInt(number);
		}
		return total;
    }

    private static String replaceNewLine(String numbers, String delimiter){
        return numbers.replaceAll("\n",delimiter);
    }

    private static String parseNegatives(String[] numbers){
        String negatives = "";
        for(String number : numbers){
            if(toInt(number) < 0) {
                negatives += number + ",";
            }
        }
        return negatives.substring(0, negatives.length() - 1);
    }

    private static String setDefaultDelimiter(String text){
        if(text.contains("[")) {
            Matcher m = Pattern.compile("(?<=\\[).*?(?=\\])").matcher(text);

            while (m.find()) {
                text = text.replaceAll(Pattern.quote(m.group()), ",");
            }

        }
        else
            text = text.replaceAll(text.substring(2, 3), ",");

        return text.substring(text.indexOf("\n")+1,text.length());
    }
}