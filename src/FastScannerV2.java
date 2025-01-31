package md2html;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FastScannerV2 implements AutoCloseable {
    private final String separator = System.lineSeparator();
    private int pointer = 0;
    private char[] buffer = new char[8192];
    private final Reader sorce;
    private int howManySymb = 0;

    public interface Transfer{
        boolean token(char ch);
    }
    private final Transfer token;

    private boolean isItToken(char ch) {
        return Character.getType(ch) != Character.SPACE_SEPARATOR  && ch != '\n' && ch != '\r';
    }

    public FastScannerV2(InputStream sorce, Transfer inputToken) {
        this.sorce = new InputStreamReader(sorce, StandardCharsets.UTF_8);
        this.token = inputToken;
        this.input();
    }

    public FastScannerV2(String sorce, Transfer inputToken) {
        this.sorce = new StringReader(sorce);
        this.token = inputToken;
        this.input();
    }

    public FastScannerV2(InputStream sorce) {
        this.sorce = new InputStreamReader(sorce);
        this.token = this::isItToken;
        this.input();
    }

    public FastScannerV2(String sorce) {
        this.sorce = new StringReader(sorce);
        this.token = this::isItToken;
        this.input();
    }

    private void input() {
        if (howManySymb == pointer) {
            pointer = 0;
            try {
                howManySymb = this.sorce.read(buffer);
                if (howManySymb == -1) {
                    howManySymb++;
                }
            } catch (IOException e) {
                System.err.println("Input error: " + e.getMessage());
            }
        }
    }

    public String next() {
        StringBuilder basket = new StringBuilder();
        while (pointer < howManySymb) {
            if (this.token.token(buffer[pointer])) {
                while (pointer < howManySymb && this.token.token(buffer[pointer])) {
                    basket.append(buffer[pointer]);
                    pointer++;
                    this.input();
                }
                break;
            }
            pointer++;
            this.input();
        }
        return basket.toString();
    }

    public String nextLine() {
        this.input();
        StringBuilder basket = new StringBuilder();
        int separatorFlag = 0;
        while(pointer < howManySymb) {
            if (buffer[pointer] == separator.charAt(separatorFlag)) {
                separatorFlag++;
                if (separatorFlag == separator.length()) {
                    pointer++;
                    return basket.substring(0, basket.length() - separatorFlag + 1);
                }
            } else if (separatorFlag > 0) {
                separatorFlag = 0;
            }
            basket.append(buffer[pointer]);
            pointer++;
            this.input();
        }
        return basket.toString();
    }
    public boolean hasNextInLine() {
        while (pointer < howManySymb) {
            if (this.token.token(buffer[pointer])) {
                return true;
            }
            if (buffer[pointer] == '\n' || buffer[pointer] == '\r') {
                return false;
            }
            pointer++;
            this.input();
        }
        return false;
    }
    public boolean hasNextLine() {
        this.input();
        return pointer < howManySymb;
    }
    public boolean hasNext() {
        while (pointer < howManySymb) {
            if (this.token.token(buffer[pointer])) {
                return true;
            }
            pointer++;
            this.input();
        }
        return false;
    }

    @Override
    public void close() {
        try {
            sorce.close();
        } catch (IOException e) {
            System.err.println("Can't close " + e.getMessage());
        }
    }
}
