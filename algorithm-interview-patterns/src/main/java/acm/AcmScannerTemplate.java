package acm;

import java.util.Scanner;

/**
 * 牛客机试 ACM 模式模板：Scanner 版。
 *
 * 适用场景：
 * 1. 输入规模不大，优先追求书写速度。
 * 2. 题目是单组输入，结构比较规则。
 *
 * 注意：
 * 1. 大数据场景下 Scanner 可能偏慢。
 * 2. 机试输出必须是纯结果，不能附加“答案是”这类提示文字。
 */
public class AcmScannerTemplate {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            return;
        }

        int n = scanner.nextInt();
        int target = scanner.nextInt();
        int[] nums = new int[n];
        for (int index = 0; index < n; index++) {
            nums[index] = scanner.nextInt();
        }

        int answer = countTarget(nums, target);
        System.out.println(answer);
    }

    /**
     * 示例逻辑：统计目标值出现次数。
     */
    private static int countTarget(int[] nums, int target) {
        int count = 0;
        for (int num : nums) {
            if (num == target) {
                count++;
            }
        }
        return count;
    }
}
