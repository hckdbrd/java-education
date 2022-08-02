package com.knubisoft.base.queue;

import com.knubisoft.base.queue.car.Car;
import com.knubisoft.base.queue.car.CarComparator;

import java.util.*;

public class QueueTasksImpl implements QueueTasks {

    @Override
    public Queue<Integer> reverseQueueUsingRecursion(Queue<Integer> queue) {
        if (queue.isEmpty()) return queue;

        int firstElement = queue.remove();
        queue = reverseQueueUsingRecursion(queue);
        queue.add(firstElement);

        return queue;
    }

    @Override
    public Queue<Integer> reverseFirstKElementsOfQueue(Queue<Integer> queue, int k) {

        if ( k == queue.size()) {
            return reverseQueueUsingRecursion(queue);
        } else if ( k > queue.size() || k < 0) {
            throw new IllegalArgumentException();
        }

        Queue<Integer> kElementsQueue = new LinkedList<>();

        while ( k > 0 ) {
            kElementsQueue.add(queue.remove());
            k--;
        }

        kElementsQueue = reverseQueueUsingRecursion(kElementsQueue);

        while (!queue.isEmpty()){
            kElementsQueue.add(queue.remove());
        }

        return kElementsQueue;
    }

    @Override
    public Queue<Integer> sortQueueOfInt(Queue<Integer> queue) {

        // Non-optimal due to such quantity of steps and cycles, but first implemented algorithm.

        // Sorted queues by element for compare.
        Queue<Queue<Integer>> nQueues = new LinkedList<>();

        // Fulfill nQueue with sorted queues
        while (queue.size() > 0) {

            // Middleman queue for sorted items
            Queue<Integer> sortedQueue = new LinkedList<>();

            int elementForCompare = queue.remove();
            sortedQueue.add(elementForCompare);

            int size = queue.size();

            // Sort items for middleman queue
            for (int i = 0; i < size; i++) {
                if (elementForCompare <= queue.element()) {
                    sortedQueue.add(queue.remove());
                } else {
                    queue.add(queue.remove());
                }
            }

            nQueues.add(sortedQueue);
        }

        // stack for reversed nQueue elements
        Stack<Queue<Integer>> queueStack = new Stack<>();

        // remove all queues to stack
        int size = nQueues.size();
        for (int i = 0; i < size; i++) {
            queueStack.push(nQueues.poll());
        }

        // return all queues in reversed order to nQueue
        for (int i = 0; i < size; i++) {
            nQueues.add(queueStack.pop());
        }

        // return ascending ordered queue
        for (Queue<Integer> nQueue : nQueues) {
            queue.addAll(nQueue);
        }

        return queue;
    }

    @Override
    public boolean validParentheses(String parentheses) {

        if (parentheses == null) throw new IllegalArgumentException();
        if (parentheses.length() % 2 == 1) return false;

        char[] symbolArray = parentheses.toCharArray();
        ArrayDeque<Character> deque = new ArrayDeque<>();

        for (char element : symbolArray) {
            deque.add(element);
        }

        for (int i = deque.size() / 2; i > 0; i--) {

            int firstSymbol = deque.removeFirst();
            int secondSymbol = deque.getFirst();

            switch (firstSymbol) {
                case '(' -> {
                    if (secondSymbol == ')') {
                        deque.removeFirst();
                    } else {
                        secondSymbol = deque.removeLast();
                        if (secondSymbol != ')') return false;
                    }
                }
                case '{' -> {
                    if (secondSymbol == '}') {
                        deque.removeFirst();
                    } else {
                        secondSymbol = deque.removeLast();
                        if (secondSymbol != '}') return false;
                    }
                }
                case '[' -> {
                    if (secondSymbol == ']') {
                        deque.removeFirst();
                    } else {
                        secondSymbol = deque.removeLast();
                        if (secondSymbol != ']') return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public PriorityQueue<Car> implementPriorityQueueThroughComparator(List<Car> cars) {
        PriorityQueue<Car> carPriorityQueue = new PriorityQueue<>();
        carPriorityQueue.addAll(cars);

        return carPriorityQueue;
    }

}
