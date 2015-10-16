package is.ru.stringcalculator;
public class Calculator {

	public static int add(String text){

        String delimiter = ",";

        if(text.contains("//")){
            delimiter = text.substring(2,3);
            text = text.substring(4,text.length());
        }

        if(text.equals("")){
			return 0;
		}
		else if(text.contains(delimiter) || text.contains("\n")){
            text = replaceNewLine(text, delimiter);
			return sum(splitNumbers(text, delimiter));
		}
		else
			return toInt(text);
	}

	private static int toInt(String number){
		return Integer.parseInt(number);
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


}