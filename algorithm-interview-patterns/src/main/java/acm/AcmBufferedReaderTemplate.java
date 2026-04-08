package acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 牛客机试常用 ACM 模式模板：适合数据量较大时使用。
 *
 * 使用说明：
 * 1. 该模板演示“第一行输入 n，第二行输入 n 个整数”的常见形式。
 * 2. 如果题目是多组输入，可以保留 while 循环直到 EOF。
 * 3. 真正做题时只需要替换 solve 方法中的核心逻辑。
 */
public class AcmBufferedReaderTemplate {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;

        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) {
                continue;
            }

            int n = Integer.parseInt(line);
            int[] nums = readIntArray(reader, n);
            long answer = solve(nums);
            System.out.println(answer);
        }
    }

    /**
     * 读取长度为 n 的整数数组。
     */
    private static int[] readIntArray(BufferedReader reader, int n) throws IOException {
        String line = reader.readLine();
        while (line != null && line.trim().isEmpty()) {
            line = reader.readLine();
        }

        String[] parts = line.trim().split("\\s+");
        int[] nums = new int[n];
        for (int index = 0; index < n; index++) {
            nums[index] = Integer.parseInt(parts[index]);
        }
        return nums;
    }

    /**
     * 这里放具体题目的求解逻辑。
     * 示例：返回数组元素和，提醒机试时注意累加结果尽量使用 long。
     */
    private static long solve(int[] nums) {
        long sum = 0L;
        for (int num : nums) {
            sum += num;
        }
        return sum;
    }
}
