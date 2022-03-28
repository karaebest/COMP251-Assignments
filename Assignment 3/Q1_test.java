

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Scanner;

public class Q1_test {

    final static String TEST_FOLDER = "Q1t";

    public void test_files() {
        File f = new File(TEST_FOLDER);
		for (String name : f.list()) {
            if (name.endsWith(".in")) {
                String[][][] input;
                int expected;
                try {
                    File in = new File(TEST_FOLDER + "/" + name);
                    File out = new File(TEST_FOLDER + "/" + name.substring(0, name.length()-3)+".ans");
                    Scanner in_scan = new Scanner(in);
                    Scanner out_scan = new Scanner(out);
                    System.out.printf("Attempting file %s\n", name);

                    expected = out_scan.nextInt();
                    out_scan.close();

                    // Make our board
                    ArrayList<ArrayList<ArrayList<String>>> board = new ArrayList<>();
                    // First layer
                    ArrayList<ArrayList<String>> layer = new ArrayList<>();
                    String line = in_scan.nextLine();
                    int length = line.length(); // Length of the given jail (number of elements per line)
                    int width = 0;  // Width of the given jail (number of lines per layer)
                    while (true) {
                        width += 1;
                        if (!(line.length() == length)) {   // Make sure every line is the same length
                            throw new IllegalArgumentException("Line is incorrect length");
                        }
                        ArrayList<String> parsed = new ArrayList<>();
                        for (char c : line.toCharArray()) {
                            parsed.add(to_str(c));
                        }
                        layer.add(parsed);
                        if (!in_scan.hasNext()) {
                            break;
                        }
                        line = in_scan.nextLine();
                        if (line.equals("")) {
                            break;
                        }
                    }
                    // Add this layer to our board
                    board.add(layer);
                    // rest of the layers
                    while (in_scan.hasNext()) {
                        layer = new ArrayList<>();
                        for (int y = 0; y < width; y++) {
                            line = in_scan.nextLine();
                            if (!(line.length() == length)) {
                                throw new IllegalArgumentException("Line is incorrect length");
                            }
                            ArrayList<String> parsed = new ArrayList<>();
                            for (char c : line.toCharArray()) {
                                parsed.add(to_str(c));
                            }
                            layer.add(parsed);
                        }
                        board.add(layer);
                        if (!in_scan.hasNext()) {
                            break;
                        }
                        // Get rid of empty line between layers
                        if (!in_scan.nextLine().equals("")) {
                            throw new IllegalArgumentException("Expected empty line between layers");
                        }
                    }

                    in_scan.close();
                    
                    // Now turn our board into array
                    int height = board.size();
                    input = new String[height][width][length];
                    for (int x = 0; x < height; x ++) {
                        for (int y = 0; y < width; y++) {
                            for (int z = 0; z < length; z++) {
                                input[x][y][z] = board.get(x).get(y).get(z);
                            }
                        }
                    }
                    // System.out.println(Arrays.deepToString(input));
                } catch (FileNotFoundException e) {
                    System.out.printf("Failed to access file %s\n", name);
                    System.out.println(e);
                    continue;
                } catch (IllegalArgumentException e) {
                    System.out.printf("Case %s is not correctly formatted\n", name);
                    System.out.println(e);
                    continue;
                }
                System.out.println("Parsed successfully");
                try {
                    // Get our return value
                    int got = Q1.find_exit(input);

                    if (got != expected) {
                        System.out.printf("Expected %d but got %d %d\n", expected, got);
                    } else {
                        System.out.printf("Passed file %s\n", name);
                    }
                }
                catch (Exception e) {
                    System.out.println("Your code threw an exception");
                    System.out.println(e);
                }
                    
                
            }
        }
    }

    private String to_str(char c) {
        switch (c) {
            case '#': return "#"; 
            case '.': return "."; 
            case 'S': return "S"; 
            case 'E': return "E"; 
            default: throw new IllegalArgumentException(String.format("Expected one of '#', '.', 'S', 'E' but got %c", c));
        } 
    }

    public static void main(String[] args) {
        new Q1_test().test_files();
    }
}