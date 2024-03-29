package P05_Telephony;

import java.util.List;

public class Smartphone implements Browsable, Callable {
    private List<String> numbers;
    private List<String> urls;

    public Smartphone(List<String> numbers, List<String> urls) {
        this.numbers = numbers;
        this.urls = urls;
    }

    @Override
    public String browse() {
            StringBuilder sb = new StringBuilder();
        for (String url : urls) {
            if(hasNumber(url)){
                sb.append("Invalid URL!").append(System.lineSeparator());
            }else {
               sb.append(String.format("Browsing: %s!", url)).append(System.lineSeparator());

            }
        }
        return sb.toString();
    }

    private boolean hasNumber(String url) {
        for (char curChar : url.toCharArray()) {
            if(Character.isDigit(curChar)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String call() {
        StringBuilder sb = new StringBuilder();
        for (String number : numbers) {
            if (hasLetter(number)){
                sb.append(String.format("Calling... %s", number)).append(System.lineSeparator());
            }else {
                sb.append("Invalid number!").append(System.lineSeparator());
            }

        }
        return sb.toString();

    }

    private boolean hasLetter(String number) {
        for (char curChar : number.toCharArray()) {
            if (Character.isLetter(curChar)){
                return false;
            }
        }
        return true;
    }
}
