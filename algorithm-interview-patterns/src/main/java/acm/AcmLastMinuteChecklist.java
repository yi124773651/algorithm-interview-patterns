package acm;

/**
 * 牛客机试考前 5 分钟检查清单。
 *
 * 使用方式：
 * 1. 考前直接运行 main 方法快速过一遍。
 * 2. 做题卡住时，也可以回来检查是否漏了基础项。
 */
public class AcmLastMinuteChecklist {

    public static void main(String[] args) {
        printBeforeExamChecklist();
        System.out.println();
        printDuringExamChecklist();
    }

    /**
     * 开考前快速检查。
     */
    private static void printBeforeExamChecklist() {
        String[] items = new String[]{
                "确认提交代码类名是否需要写成 Main。",
                "确认已经准备好 BufferedReader 或 Scanner 输入模板。",
                "确认知道多组输入通常要读取到 EOF。",
                "确认输出只能打印结果，不能输出提示文字。",
                "确认求和、前缀和、方案数尽量使用 long。",
                "确认二分、滑动窗口、哈希、排序模板都能默写。",
                "确认遇到区间题先想排序，遇到连续子数组先想滑动窗口。"
        };

        System.out.println("=== 考前 5 分钟检查 ===");
        printItems(items);
    }

    /**
     * 做题过程中快速检查。
     */
    private static void printDuringExamChecklist() {
        String[] items = new String[]{
                "先读清输入格式，是单组输入还是多组输入。",
                "先判断题目属于哪个模板，再开始写代码。",
                "先写能过样例的版本，再补边界条件。",
                "检查数组下标、空串、空数组、负数等边界。",
                "检查是否会整数溢出，必要时把 int 改成 long。",
                "卡题超过 10 到 15 分钟及时换题，先拿稳能拿的分。",
                "提交前最后检查一遍输出格式和方法返回值。"
        };

        System.out.println("=== 做题中检查 ===");
        printItems(items);
    }

    private static void printItems(String[] items) {
        for (int index = 0; index < items.length; index++) {
            System.out.println((index + 1) + ". " + items[index]);
        }
    }
}
