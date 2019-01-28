package com.xuan.mix.bt;

import com.xuan.mix.bt.status.StatusChecker;
import com.xuan.mix.bt.status.impl.LoadStatusChecker;
import com.xuan.mix.bt.status.impl.MemoryStatusChecker;
import org.junit.Test;

public class StatusTest {

    @Test
    public void testStatus() {
        StatusChecker m = new MemoryStatusChecker();
        System.out.println("++++++++++m:" + m.check());

        StatusChecker l = new LoadStatusChecker();
        System.out.println("++++++++++l:" + l.check());
    }

}
