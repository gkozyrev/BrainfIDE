package BrainfIDE.Brainfuck;

import java.util.*;

public class Interpreter {
    private Scanner sc = new Scanner(System.in);
    private final int LENGTH = 65535;
    private byte[] mem = new byte[LENGTH];
    private int dataPointer;

    public String output;

    public String interpret(String code) {
        int l = 0;
        this.output = "";
        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == '>') {
                dataPointer = (dataPointer == LENGTH - 1) ? 0 : dataPointer + 1;
            } else if (code.charAt(i) == '<') {
                dataPointer = (dataPointer == 0) ? LENGTH - 1 : dataPointer - 1;
            } else if (code.charAt(i) == '+') {
                mem[dataPointer]++;
            } else if (code.charAt(i) == '-') {
                mem[dataPointer]--;
            } else if (code.charAt(i) == '.') {
                this.output = this.output + new String(new byte[] { mem[dataPointer] });
                System.out.print((char) mem[dataPointer]);
            } else if (code.charAt(i) == ',') {
                mem[dataPointer] = (byte) sc.next().charAt(0);
            } else if (code.charAt(i) == '[') {
                if (mem[dataPointer] == 0) {
                    i++;
                    while (l > 0 || code.charAt(i) != ']') {
                        if (code.charAt(i) == '[')
                            l++;
                        if (code.charAt(i) == ']')
                            l--;
                        i++;
                    }
                }
            } else if (code.charAt(i) == ']') {
                if (mem[dataPointer] != 0) {
                    i--;
                    while (l > 0 || code.charAt(i) != '[') {
                        if (code.charAt(i) == ']')
                            l++;
                        if (code.charAt(i) == '[')
                            l--;
                        i--;
                    }
                    i--;
                }
            }
        }
        return this.output;
    }

    // public static void main(String[] args) {
    // new Interpreter().interpret(args[0]);
    // }
}
