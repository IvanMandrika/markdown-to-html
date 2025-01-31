package md2html;
import md2html.markup.*;

import java.io.*;
import java.nio.charset.StandardCharsets;


public class Md2Html {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Not enough arguments");
            System.exit(1);
        }
        try(FastScannerV2 scanner = new FastScannerV2(new FileInputStream(args[0]))) {
            try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(args[1]),
                    StandardCharsets.UTF_8
            ))) {
                while (scanner.hasNextLine()) {
                    boolean itsHeader = false;
                    String newLine = scanner.nextLine();
                    if (newLine.isEmpty()) continue;
                    StringBuilder stringBuilder = new StringBuilder();
                    if (newLine.charAt(0) == '#') {
                        int index = 0;
                        while (newLine.charAt(index) == '#' && index < 7) index++;
                        if (index < newLine.length() && newLine.charAt(index) == ' ') {
                            stringBuilder.append(newLine);
                            while (scanner.hasNextLine()) {
                                newLine = scanner.nextLine();
                                if (newLine.isEmpty()) {
                                    break;
                                } else {
                                    stringBuilder.append(System.lineSeparator());
                                    stringBuilder.append(newLine);
                                }
                            }
                            Header h = new Header();
                            itsHeader = true;
                            h.getFromMarkdown(stringBuilder, 0);
                            stringBuilder = new StringBuilder();
                            h.toHtml(stringBuilder);
                            stringBuilder.append(System.lineSeparator());
                            out.write(stringBuilder.toString());
                        }

                    } if(!itsHeader) {
                        stringBuilder.append(newLine);
                        while(scanner.hasNextLine()) {
                            newLine = scanner.nextLine();
                            if (newLine.isEmpty()) break;
                            stringBuilder.append(System.lineSeparator());
                            stringBuilder.append(newLine);
                        }
                        Paragraph p = new Paragraph();
                        p.getFromMarkdown(stringBuilder, 0);
                        stringBuilder = new StringBuilder();
                        p.toHtml(stringBuilder);
                        stringBuilder.append(System.lineSeparator());
                        out.write(stringBuilder.toString());
                    }
                }
            }
            catch (IOException e) {
                System.out.println("Sorry exception with writing" + " " + e.getMessage());
            }
        } catch (IOException e) {
            System.out.println("Sorry exception with reading" + " " + e.getMessage());
        }
    }


}
