package com.dididi.rxjava.impl

/**
 * author: yechao
 * desc: 线程调度器
 * createTime:2020-09-30
 */

class Scheduler private constructor() {
    companion object {

        /**
         * 默认策略，也就是当前线程
         */
        fun immediate(): Scheduler {
            return Scheduler()
        }

        /**
         * 总是启用新线程，并在新线程执行操作。
         */
        fun newThread(): Scheduler {
            return Scheduler()
        }

        /**
         *  I/O 操作（读写文件、读写数据库、网络信息交互等）所使用的 Scheduler。
         *  行为模式和 newThread() 差不多，区别在于 io() 的内部实现是是用一个无数量上限的线程池，
         *  可以重用空闲的线程，因此多数情况下 io() 比 newThread() 更有效率。
         *  不要把计算工作放在 io() 中，可以避免创建不必要的线程。
         */
        fun io(): Scheduler {
            return Scheduler()
        }

        /**
         * 计算所使用的 Scheduler。
         * 这个计算指的是 CPU 密集型计算，即不会被 I/O 等操作限制性能的操作，例如图形的计算。
         * 这个 Scheduler 使用的固定的线程池，大小为 CPU 核数。
         * 不要把 I/O 操作放在 computation() 中，否则 I/O 操作的等待时间会浪费 CPU。
         */
        fun computation(): Scheduler {
            return Scheduler()
        }
    }
}