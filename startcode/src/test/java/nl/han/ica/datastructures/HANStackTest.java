package nl.han.ica.datastructures;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HANStackTest {

        HANStack<Integer> stack = new HANStack<Integer>();

        @Test
        void testPush(){
            int firstItem = 1;
            int secondFirstItem = 2;

            stack.push(firstItem);

            assertEquals(stack.peek(), firstItem);

            stack.push(secondFirstItem);

            assertEquals(stack.peek(), secondFirstItem);

        }

        @Test
        void testPop(){
            int firstItem = 1;
            int secondFirstItem = 2;

            stack.push(firstItem);
            stack.push(secondFirstItem);

            assertEquals(stack.pop(), secondFirstItem);
            assertEquals(stack.pop(), firstItem);
        }

        @Test
        void testPeek(){
            int firstItem = 1;
            int secondFirstItem = 2;

            stack.push(firstItem);
            stack.push(secondFirstItem);

            assertEquals(stack.peek(), secondFirstItem);
            assertEquals(stack.peek(), secondFirstItem);
        }
}
