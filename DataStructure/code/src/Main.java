import java.util.Random;
public class Main {
//    // 测试运行opCount个入队和出队操作的时间
//    private static double testQueue(Queue<Integer> queue, int opCount) {
//        long startTime = System.nanoTime();
//        Random random = new Random();
//        for (int i = 0; i < opCount; i ++) {
//            queue.enqueue(Integer.MAX_VALUE);
//        }
//        for (int i = 0; i < opCount; i ++) {
//            queue.dequeue();
//        }
//        long endTime = System.nanoTime();
//        return (endTime - startTime) / 1000000000.0;
//    }
//    public static void main(String[] args) {
//        int opCount = 100000;
//        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
//        System.out.println("ArrayQueue: 进行" + opCount + "次入队、出队的时间: " + testQueue(arrayQueue, opCount) + "s");
//        LoopQueue<Integer> loopQueue = new LoopQueue<>();
//        System.out.println("LoopQueue: 进行" + opCount + "次入队、出队的时间: " + testQueue(loopQueue, opCount) + "s");
//        LinkedListQueue<Integer> linkedListQueue = new LinkedListQueue<>();
//        System.out.println("LinkedListQueue: 进行" + opCount + "次入队、出队的时间: " + testQueue(linkedListQueue, opCount) + "s");
//    }
    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        bst.add(5);
        bst.add(4);
        bst.add(6);
        bst.add(3);
        bst.add(7);

        bst.levelOrder();
        bst.preOrder();
        System.out.println(bst.toString());
        bst.removeMax();
        bst.levelOrder();
        bst.remove(4);
        bst.levelOrder();
    }
}