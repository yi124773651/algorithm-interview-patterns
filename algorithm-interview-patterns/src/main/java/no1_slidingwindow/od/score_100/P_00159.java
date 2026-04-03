package no1_slidingwindow.od.score_100;

/**
 * @ClassName P_00159
 * @Description TODO
 * @Author gm
 * @Date 2026/4/3 01:00
 * 近些年来，我国防沙治沙取得显著成果。某沙漠新种植N棵胡杨（编号1-N），排成一排。
 * 一个月后，有M棵胡杨未能成活。
 * 现可补种胡杨K棵，请问如何补种（只能补种，不能新种），可以得到最多的连续胡杨树？
 * <p>
 * 输入描述
 * N 总种植数量
 * M 未成活胡杨数量
 * M 个空格分隔的数，按编号从小到大排列
 * K 最多可以补种的数量
 * <p>
 * 其中：
 * 1 <= N <= 100000
 * 1 <= M <= N
 * 0 <= K <= M
 * <p>
 * 输出描述
 * 最多的连续胡杨棵树
 * <p>
 * 示例1  输入输出示例仅供调试，后台判题数据一般不包含示例
 * 输入
 * 5
 * 2
 * 2 4
 * 1
 * <p>
 * 输出
 * 3
 * <p>
 * 说明
 * 补种到2或4结果一样，最多的连续胡杨棵树都是3。
 * <p>
 * 示例2
 * 输入
 * 10
 * 3
 * 2 4 7
 * 1
 * <p>
 * 输出
 * 6
 * <p>
 * 说明
 * 补种第7棵树，最多的连续胡杨棵树为6(5,6,7,8,9,10)
 */
public class P_00159 {
    public static void main(String[] args) {
//        int N = 5;
//        int[] dead = new int[]{2, 4};
//        int K = 1;
        int N = 10;
        int[] dead = new int[]{2, 4, 7};
        int K = 1;
        int result = solution(N, dead, K);
        System.out.println(result);
    }

    /**
     * ===================== 错误写法（记录与分析） =====================
     *
     * private static int solution(int m, int n, int[] dead, int k) {
     *     int[] tree = new int[m];
     *     for (int i : dead) {
     *         tree[i - 1] = 1;
     *     }
     *     int maxSucessionCount = 0;
     *     int left = 0;
     *     int count = k;
     *     for (int right = 0; right < tree.length; right++) {
     *         if (tree[right] == 1) {
     *             if (count == 0) {
     *                 count = k;   // 复位
     *                 left = right; // 跳转
     *                 continue;
     *             } else {
     *                 count--;
     *             }
     *         }
     *         maxSucessionCount = Math.max(maxSucessionCount, right - left + 1);
     *     }
     *     return maxSucessionCount;
     * }
     *
     * -------- 用示例2手动追踪（N=10, dead=[2,4,7], K=1） --------
     *
     * tree = [0, 1, 0, 1, 0, 0, 1, 0, 0, 0]
     * index:  0  1  2  3  4  5  6  7  8  9
     *
     * right=0: tree[0]=0,                   left=0, count=1, maxLen=1
     * right=1: tree[1]=1, count 1→0,        left=0, count=0, maxLen=2
     * right=2: tree[2]=0,                   left=0, count=0, maxLen=3
     * right=3: tree[3]=1, count==0! 重置:    left=3, count=1, continue(跳过更新)
     * right=4: tree[4]=0,                   left=3, count=1, maxLen=3
     * right=5: tree[5]=0,                   left=3, count=1, maxLen=3
     * right=6: tree[6]=1, count 1→0,        left=3, count=0, maxLen=4
     * right=7: tree[7]=0,                   left=3, count=0, maxLen=5
     * right=8: tree[8]=0,                   left=3, count=0, maxLen=6
     * right=9: tree[9]=0,                   left=3, count=0, maxLen=7  ← 错误！正确答案是6
     *
     * -------- Bug分析 --------
     *
     * Bug1: 重置后窗口状态不一致
     *   right=3时触发重置: count=k=1, left=3。
     *   但tree[3]本身就是死树！count重置为1却没有为tree[3]扣减，相当于这棵死树白嫖进了窗口。
     *   后续right=6时遇到死树 count 1→0，窗口[3,6]里实际有2棵死树(index 3和6)，
     *   但只花了1个配额，状态已经错乱。
     *
     * Bug2: left = right 跳得太远
     *   right=3时，left直接跳到3。但最优做法只需让left越过窗口内第一棵死树即可——
     *   即跳到index 2(越过index 1的死树)，这样窗口[2,3]内只有1棵死树，仍然合法。
     *
     * Bug3: continue跳过了maxLen更新
     *   即使重置后的窗口是合法的，也不会更新答案。
     *
     * -------- 核心认知纠正 --------
     *
     * 错误心智模型: 配额用完 → 推倒重来，left一步跳到right，count全部复位
     * 正确心智模型: 条件不满足 → 左边界逐步收缩(while循环)，只释放刚好够的资源
     *
     * 纠正步骤:
     *   1. 把"剩余配额倒计数(count从k减到0)"改为"窗口内死树正计数(deadCount从0往上加)"
     *      → 正计数能随时精确知道窗口里有几棵死树，收缩时deadCount--状态始终正确
     *      → 倒计数在"重置"时无法正确恢复，因为不知道新窗口里有几棵死树
     *   2. 把"if跳转"改为"while收缩"
     *      → left不是"跳"到某个位置，而是"滑"过去，每步都维护状态
     *   3. 去掉continue，每个right位置经过while收缩后窗口一定合法，都应更新答案
     *
     * 滑动窗口万能模板（所有「最多允许K个不合法元素」的题都适用）:
     *   for (right = 0; right < n; right++) {
     *       // 1. right进入窗口，更新状态
     *       // 2. while 状态不合法 → 左边界收缩，恢复状态
     *       // 3. 更新答案（此时窗口一定合法）
     *   }
     */
    private static int solution(int n, int[] dead, int k) {
        int[] tree = new int[n];
        for (int i : dead) {
            tree[i - 1] = 1;  // 1=死亡 0=存活
        }
        int maxLen = 0;
        int left = 0;
        int deadCount = 0;
        for (int right = 0; right < tree.length; right++) {
            // 1. right进入窗口，更新状态
            if (tree[right] == 1) {
                deadCount++;
            }
            // 2. 窗口内死树超过k棵 → 左边界逐步收缩，直到移出一棵死树
            while (deadCount > k) {
                if (tree[left] == 1) {
                    deadCount--;
                }
                left++;
            }
            // 3. 此时窗口一定合法(deadCount <= k)，更新答案
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
}
