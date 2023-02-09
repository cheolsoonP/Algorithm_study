import java.io.*;

class Main {

    static int n;
    static StringBuilder result = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        backTrack(0, 0, "");

        System.out.println(result);
    }

    private static void backTrack(int depth, int flag, String number) {
        if (depth == n) {
            result.append(number).append('\n');
            return;
        }

        for (int i = 1; i < 10; i++) {
            String newNumber = number + i;
            if (isPrime(Integer.parseInt(newNumber))) {
                backTrack(depth + 1, flag | 1 << i, newNumber);
            }
        }
    }

    public static boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }
}