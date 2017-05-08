package algorithm.baidu;

/**
 * Created by louyuting on 2017/2/24.
 *
 * Question: 对某公司员工的所有电话号码进行排序, 数据量级别明显是万级别的.
 *
 * category: 大数据量进行排序
 *
 * solution: (1)首先根据电话号码的号段进行分类, 分为130-139, 150-159, 180-189 也就是30个分段,这里就是桶排序的思想
 *           (2)然后就是对每个分段进行排序(也就是对每个桶进行排序)
 *                  这里利用类似于hash表的思路,因为每个电话号码要么存在要么不存在,所以用bit来标识每个位是否存在.
 *                  如果不用bit,用整形的话,计算一下,30*8^10 =  30亿条记录, 如果存整形的话,一个数字是4字节,总共是120亿个字节. 计算一下就是11.4GB,这就占太大的内存了;
 *                  这个时候我们换个思路,利用bit来存储,因为电话号码本来就是连续的,而且我们只标识某一个电话号码是否存在,所以没毛病,
 *                  这时候原本4字节保存一个标志位,就变成了bit位,缩小了 4*16=64倍, 所以占内存就变成了178M, 这时候就能接受了.
 *                  而且每个分段的内存大小是约5.9M
 *
 *           (3)由于每个分段之间本身就是有序的,所以现在只用关心各自分段的部分. 这就类似于利用哈希表来标识每个电话号存不存在.
 *              1)这时候的核心就是: 输入一个电话号码,装换为整形,根据前面三位分别进入不同的桶;
 *              2)在不同桶内,根据电话后8位,计算出在一个桶内的坐标位,然后响应的bit 置为1;
 *              3)判断完所有电话号码之后,就可以通过一轮遍历获取所有电话号码和排序结果.
 *
 *  analysis: 时间复杂度是O(n), 空间复杂度是:O(n/64);
 *
 *  缺点: 算法很快,但是只能用于整形的排序, 只能用于正数的排序;
 *       不能处理重复的数据排除;
 *
 *  优化: 其实可以用30个线程来分别处理每个桶的排序,加快速度.
 */
public class PhoneSort {

    /**
     * 这里只给出伪代码的实现
     * @param phones 输入的电话号码数组
     * @return
     */
    public static int[][] phoneSort(String[] phones){


        //定义30个桶 ,每个桶的大小是 (100000000/8/4)个整形数据,这里为了方便,就用二维数组了,
        int[][] bucks = new int[30][100000000/32];

        for(int i=0; i<phones.length; i++){
            //根据电话号码,计算出属于哪个桶
            //int buckI = getBuckI(phones[i]);

            //根据电话号码计算出属于每个桶中的哪个数据的哪一位
            //比如对于xxx-1000-0000这个号码,
            //10000000/8/4 = 312500, 也就对于数组下标为 312500的这个整数的最低位,将被置为1;
            //根据电话号码对32做除法,获得数组中索引的下标
            //int index = getIndex(phones[i]);
            //根据电话号码对32做取余,获得数组中index索引代表的整数中第bit位 为1
            //int bit = getIndex(phones[i]);

            //设置bucks[buckI][index] 的值
           // bucks[buckI][index] = bucks[buckI][index]| (0x01<<bit);
        }
        return bucks;
    }

}
