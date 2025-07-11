public class TakeBox {
    public int solution(int total, int colSize, int find) {
        int rowSize = getTableRow(total, colSize);
        int[][] table = initializeTable(rowSize, colSize);

        int num = 1;
        boolean isRight = false;
        int answer = 0;

        for (int row = table.length - 1; row >= 0; row--) {
            if (!isRight) {
                for (int col = 0; col < table[row].length; col++) {
                    table[row][col] = num;

                    if (num == total) break;
                    num++;
                }

                isRight = true;
                continue;
            }

            for (int col = colSize - 1; col >= 0; col--) {
                table[row][col] = num;

                if (num == total) break;
                num++;
                isRight = false;
            }
        }

        for (int row = 0; row < table.length; row++) {
            for (int col = 0; col < table[row].length; col++) {
                if (table[row][col] == find) {
                    for (int i = row; i >= 0; i--) {
                        if (table[i][col] != 0) answer++;
                    }
                }
            }
        }

        return answer;
    }

    private int[][] initializeTable(int rowSize, int colSize) {
        return new int[rowSize][colSize];
    }

    private int getTableRow(int n, int w) {
        int extra = n % w;
        int result = n / w;

        if (extra == 0) return result;

        return result + 1;
    }
}
