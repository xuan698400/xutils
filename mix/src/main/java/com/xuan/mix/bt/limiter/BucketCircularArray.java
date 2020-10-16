package com.xuan.mix.bt.limiter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * @author xuan
 * @since 2019/5/22
 */
public class BucketCircularArray implements Iterable<Bucket> {
    private final AtomicReference<ListState> state;

    /**
     * 环形数组实际长度
     */
    private final int dataLength;
    /**
     * 环形数组存放Bucket数量：numBuckets = dataLength - 1
     */
    private final int numBuckets;

    public BucketCircularArray(int size) {
        AtomicReferenceArray<Bucket> buckets = new AtomicReferenceArray<>(size + 1);
        state = new AtomicReference<>(new ListState(buckets, 0, 0));
        dataLength = buckets.length();
        numBuckets = size;
    }

    @Override
    public Iterator<Bucket> iterator() {
        return Collections.unmodifiableList(Arrays.asList(getArray())).iterator();
    }

    public void clear() {
        while (true) {
            ListState current = state.get();
            ListState newState = current.clear();
            if (state.compareAndSet(current, newState)) {
                return;
            }
        }
    }

    public void addLast(Bucket o) {
        ListState currentState = state.get();
        ListState newState = currentState.addBucket(o);

        if (state.compareAndSet(currentState, newState)) {
            // 表示新增一个Bucket成功
            return;
        } else {
            //表示有其他线程，新增了Bucket，那就放弃（实际上，上层在使用时做了单线程访问处理的）
            return;
        }
    }

    public Bucket peekLast() {
        return state.get().tail();
    }

    private Bucket[] getArray() {
        return state.get().getArray();
    }

    private class ListState {
        private final AtomicReferenceArray<Bucket> data;
        private final int size;
        private final int tail;
        private final int head;

        private ListState(AtomicReferenceArray<Bucket> data, int head, int tail) {
            this.head = head;
            this.tail = tail;
            if (head == 0 && tail == 0) {
                size = 0;
            } else {
                this.size = (tail + dataLength - head) % dataLength;
            }
            this.data = data;
        }

        public ListState clear() {
            return new ListState(new AtomicReferenceArray<>(dataLength), 0, 0);
        }

        public ListState addBucket(Bucket b) {
            data.set(tail, b);
            return incrementTail();
        }

        public ListState incrementTail() {
            if (size == numBuckets) {
                return new ListState(data, (head + 1) % dataLength, (tail + 1) % dataLength);
            } else {
                return new ListState(data, head, (tail + 1) % dataLength);
            }
        }

        public Bucket tail() {
            if (size == 0) {
                return null;
            } else {
                return data.get(convert(size - 1));
            }
        }

        private Bucket[] getArray() {
            ArrayList<Bucket> array = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                array.add(data.get(convert(i)));
            }
            return array.toArray(new Bucket[array.size()]);
        }

        private int convert(int index) {
            return (index + head) % dataLength;
        }
    }

}
