/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSVReader;

/**
 *
 * @author Nicola
 */
public class ParseString {

    private String toParse;
    private String delimiters; //delimitatori
    private String[] splitedElement;
    private int tokenPos = 0;
    private String filename = null;

    public ParseString(String toParse, String delimiters) {
        this.toParse = toParse;
        this.delimiters = delimiters;
        this.splitedElement = tokens();
    }

    public ParseString(String toParse, String delimiters, String filename) {
        this.toParse = toParse;
        this.delimiters = delimiters;
        this.filename = filename;
        this.splitedElement = tokens();
    }

    public String getDelimiters() {
        return delimiters;
    }

    public void setDelimiters(String delimiters) {
        this.delimiters = delimiters;
    }

    private void fixData(String[] arr) {
        //System.out.println(filename);
        switch (filename) {
            case "stop_times.txt":
                if (arr[1].startsWith("24:")) {
                    arr[1] = "00" + arr[1].substring(2, arr[1].length());
                }
                if (arr[2].startsWith("24:")) {
                    arr[2] = "00" + arr[2].substring(2, arr[2].length());
                }
                if (arr[1].startsWith("25:")) {
                    arr[1] = "01" + arr[1].substring(2, arr[1].length());
                }
                if (arr[2].startsWith("25:")) {
                    arr[2] = "01" + arr[2].substring(2, arr[2].length());
                }
                if (arr[1].startsWith("26:")) {
                    arr[1] = "02" + arr[1].substring(2, arr[1].length());
                }
                if (arr[2].startsWith("26:")) {
                    arr[2] = "02" + arr[2].substring(2, arr[2].length());
                }
                if (arr[1].startsWith("27:")) {
                    arr[1] = "03" + arr[1].substring(2, arr[1].length());
                }
                if (arr[2].startsWith("27:")) {
                    arr[2] = "03" + arr[2].substring(2, arr[2].length());
                }
                if (arr[1].startsWith("28:")) {
                    arr[1] = "04" + arr[1].substring(2, arr[1].length());
                }
                if (arr[2].startsWith("28:")) {
                    arr[2] = "04" + arr[2].substring(2, arr[2].length());
                }
                if (arr[1].startsWith("29:")) {
                    arr[1] = "05" + arr[1].substring(2, arr[1].length());
                }
                if (arr[2].startsWith("29:")) {
                    arr[2] = "05" + arr[2].substring(2, arr[2].length());
                }
                break;

        }

    }

    private String[] tokens() {
        String[] ret = null;
        ret = this.toParse.replace(",,", ", ,").split(delimiters);
        if (filename != null) {
            fixData(ret);
        }
        return ret;
    }

    public String nextToken() {

        return splitedElement[tokenPos++];

    }

    public boolean hasMoreTokens() {
        return tokenPos < splitedElement.length;
    }

    public static void main(String... args) {
        String str = "5789,24:24:00,24:24:00,5014,5,P.LE ROMA,0,null,null";
        ParseString ps = new ParseString(str, ",", "stop_times.txt");
        int i = 0;
        while (ps.hasMoreTokens()) {
            System.out.println("token:" + ps.nextToken() + " " + i++);
        }
        System.out.println(ps.tokenPos);

    }

}
