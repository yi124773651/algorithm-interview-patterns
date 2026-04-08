package acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 牛客机试通用 Main 模板。
 *
 * 使用建议：
 * 1. 提交前把类名保持为 Main。
 * 2. 根据题目输入格式，保留对应的示例代码块即可。
 * 3. 输出必须是纯结果，不能拼接额外提示文字。
 */
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // 示例一：第一行 n，第二行 n 个整数
        // int n = Integer.parseInt(reader.readLine().trim());
        // int[] nums = parseIntArray(reader.readLine(), n);
        // System.out.println(sum(nums));

        // 示例二：单行字符串输入
        // String text = reader.readLine();
        // System.out.println(text.length());

        // 示例三：多组输入直到 EOF
        String line;
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) {
                continue;
            }

            String[] parts = line.split("\\s+");
            int[] nums = new int[parts.length];
            for (int index = 0; index < parts.length; index++) {
                nums[index] = Integer.parseInt(parts[index]);
            }

            System.out.println(sum(nums));
        }
    }

    /**
     * 解析固定长度整数数组。
     */
    private static int[] parseIntArray(String line, int n) {
        String[] parts = line.trim().split("\\s+");
        int[] nums = new int[n];
        for (int index = 0; index < n; index++) {
            nums[index] = Integer.parseInt(parts[index]);
        }
        return nums;
    }

    /**
     * 示例逻辑：计算数组元素和。
     */
    private static long sum(int[] nums) {
        long sum = 0L;
        for (int num : nums) {
            sum += num;
        }
        return sum;
    }

    /**
     * 如果题目需要判断字符串是否合法、回文等，可以直接复用这里的模板方法。
     */
    private static boolean isPalindrome(String text) {
        int left = 0;
        int right = text.length() - 1;
        while (left < right) {
            if (text.charAt(left) != text.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}