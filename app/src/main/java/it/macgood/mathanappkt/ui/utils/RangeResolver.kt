package it.macgood.mathanappkt.ui.utils
object RangeResolver {
    fun resolveDemidovichRange(taskId: Int) : IntRange {
        val chapter1 = IntRange(1, 340)
        val chapter2 = IntRange(341, 810)
        val chapter3 = IntRange(811, 1030)
        val chapter4 = IntRange(1031, 1500)
        val chapter5 = IntRange(1501, 1781)
        val chapter6 = IntRange(1782, 2112)
        val chapter7 = IntRange(2113, 2400)
        val chapter8 = IntRange(2401, 2703)
        val chapter9 = IntRange(2704, 3107)
        val chapter10 = IntRange(3108, 3193)
        if (chapter1.contains(taskId)) {
            return chapter1
        } else if (chapter2.contains(taskId)) {
            return chapter2
        } else if (chapter3.contains(taskId)) {
            return chapter3
        } else if (chapter4.contains(taskId)) {
            return chapter4
        } else if (chapter5.contains(taskId)) {
            return chapter5
        } else if (chapter6.contains(taskId)) {
            return chapter6
        } else if (chapter7.contains(taskId)) {
            return chapter7
        } else if (chapter8.contains(taskId)) {
            return chapter8
        } else if (chapter9.contains(taskId)) {
            return chapter9
        } else if (chapter10.contains(taskId)) {
            return chapter10
        } else {
            return chapter1
        }

    }
}


