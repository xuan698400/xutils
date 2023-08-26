package com.xuan.xutils.sequence.impl.snowflake;

import com.xuan.xutils.sequence.Sequence;
import com.xuan.xutils.sequence.SequenceException;

/**
 * 使用雪花算法生产序列号，具体算法如下:
 *
 * 一个long类型的数据，64位。以下是每位的具体含义。
 * <br>
 * snowflake的结构如下(每部分用-分开):
 * <br>
 * 0 - 0000000000 0000000000 0000000000 0000000000 0 - 00000 - 00000 - 000000000000
 * <br>
 * （1）第一位为未使用
 * （2）接下来的41位为毫秒级时间(41位的长度可以使用69年)
 * （3）然后是5位datacenterId
 * （4）5位workerId
 * （5）最后12位是毫秒内的计数（12位的计数顺序号支持每个节点每毫秒产生4096个ID序号）
 * <br>
 * 一共加起来刚好64位，为一个Long型。(转换成字符串长度为18)
 *
 * @author xuan
 * @date 2018/5/9
 */
public class SnowflakeSequence implements Sequence {

    /**
     * 开始时间截 (2018-01-01)
     */
    private static final long TWEPOCH = 1514736000000L;

    /**
     * 机器id所占的位数
     */
    private final long workerIdBits = 5L;

    /**
     * 数据标识id所占的位数
     */
    private final long datacenterIdBits = 5L;

    /**
     * 支持的最大机器id，结果是31 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数)
     */
    private final long maxWorkerId = -1L ^ (-1L << workerIdBits);

    /**
     * 支持的最大数据标识id，结果是31
     */
    private final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);

    /**
     * 序列在id中占的位数
     */
    private final long sequenceBits = 12L;

    /**
     * 机器ID向左移12位
     */
    private final long workerIdShift = sequenceBits;

    /**
     * 数据标识id向左移17位(12+5)
     */
    private final long datacenterIdShift = sequenceBits + workerIdBits;

    /**
     * 时间截向左移22位(5+5+12)
     */
    private final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;

    /**
     * 生成序列的掩码，这里为4095 (0b111111111111=0xfff=4095)
     */
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);

    /**
     * 工作机器ID(0~31)
     */
    private long workerId;

    /**
     * 数据中心ID(0~31)
     */
    private long datacenterId;

    /**
     * 毫秒内序列(0~4095)
     */
    private long sequence = 0L;

    /**
     * 上次生成ID的时间截
     */
    private long lastTimestamp = -1L;

    @Override
    public synchronized long nextValue() throws SequenceException {
        long timestamp = timeGen();

        //如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (timestamp < lastTimestamp) {
            throw new SequenceException("[SnowflakeSequence-nextValue] 当前时间小于上次生成序列号的时间，时间被回退了，请确认服务器时间的设置.");
        }

        //如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            //毫秒内序列溢出
            if (sequence == 0) {
                //阻塞到下一个毫秒,获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            //时间戳改变，毫秒内序列重置
            sequence = 0L;
        }

        //上次生成ID的时间截
        lastTimestamp = timestamp;

        //移位并通过或运算拼到一起组成64位的ID
        return ((timestamp - TWEPOCH) << timestampLeftShift) //
            | (datacenterId << datacenterIdShift) //
            | (workerId << workerIdShift) //
            | sequence;
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     *
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 返回以毫秒为单位的当前时间
     *
     * @return 当前时间(毫秒)
     */
    private long timeGen() {
        return System.currentTimeMillis();
    }

    public void setWorkerId(long workerId) {
        if (workerId > maxWorkerId) {
            throw new SequenceException("Snowflake sequence workerId cannot be greater than 31.");
        }

        this.workerId = workerId;
    }

    public void setDatacenterId(long datacenterId) {
        if (datacenterId > maxDatacenterId) {
            throw new SequenceException("Snowflake sequence datacenterId cannot be greater than 31.");
        }

        this.datacenterId = datacenterId;
    }

}
