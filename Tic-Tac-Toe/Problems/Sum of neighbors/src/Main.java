import java.util.*;

class Main {
    public static void main(String[] args) {
        // put your code here
        final Scanner sc = new Scanner(System.in);
        int columns = 0, rows;
        StringBuilder nums = new StringBuilder();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if ("end".equals(line)) {
                break;
            }
            nums.append(line).append("  ");
            columns = line.split(" ").length;
        }
        String[] parts = nums.toString().split(" {2}");
        rows = parts.length;
        int[][] matrix = new int[rows][columns];
        int[][] result = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                String[] numbers = parts[i].split(" ");
                matrix[i][j] = Integer.parseInt(numbers[j]);
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result[i][j] = matrix[(i - 1 + rows) % rows][j] + matrix[(i + 1 + rows) % rows][j]
                        + matrix[i][(j - 1 + columns) % columns] + matrix[i][(j + 1 + columns) % columns];
            }
        }
        for (int[] row : result) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}