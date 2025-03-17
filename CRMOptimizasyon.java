import java.util.*;

public class CRMOptimizasyon {

    // Müşteri Destek Temsilcisi Yönlendirme (Dinamik Programlama ile Atama Problemi)
    public static int temsilciYonlendirme(int[][] uygunluk) {
        int n = uygunluk.length;
        int m = uygunluk[0].length;
        int[][] dp = new int[n + 1][1 << m];

        for (int i = 0; i <= n; i++) Arrays.fill(dp[i], Integer.MIN_VALUE);
        dp[0][0] = 0;

        for (int i = 0; i < n; i++) {
            for (int maske = 0; maske < (1 << m); maske++) {
                if (dp[i][maske] == Integer.MIN_VALUE) continue;
                for (int j = 0; j < m; j++) {
                    if ((maske & (1 << j)) == 0) {
                        dp[i + 1][maske | (1 << j)] = Math.max(
                                dp[i + 1][maske | (1 << j)],
                                dp[i][maske] + uygunluk[i][j]
                        );
                    }
                }
            }
        }
        return Arrays.stream(dp[n]).max().getAsInt();
    }

    // Pazarlama Kampanyası Seçimi (Knapsack Problemi - Dinamik Programlama)
    public static int maksimumROI(int[] maliyetler, int[] getiriler, int butce) {
        int n = maliyetler.length;
        int[][] dp = new int[n + 1][butce + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= butce; j++) {
                if (maliyetler[i - 1] <= j)
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - maliyetler[i - 1]] + getiriler[i - 1]);
                else
                    dp[i][j] = dp[i - 1][j];
            }
        }
        return dp[n][butce];
    }

    public static void main(String[] args) {
        // Örnek veri: Müşteri Temsilcisi Yönlendirme
        int[][] uygunluk = {
                {9, 2, 7, 8},
                {6, 4, 3, 7},
                {5, 8, 1, 8},
                {7, 6, 9, 4}
        };
        System.out.println("En iyi müşteri temsilcisi yönlendirme skoru: " + temsilciYonlendirme(uygunluk));

        // Örnek veri: Pazarlama Kampanyası Seçimi
        int[] maliyetler = {3, 2, 4, 5};
        int[] getiriler = {6, 8, 7, 9};
        int butce = 8;
        System.out.println("Maksimum ROI: " + maksimumROI(maliyetler, getiriler, butce));
    }
}

