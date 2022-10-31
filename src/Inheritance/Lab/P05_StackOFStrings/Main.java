package Inheritance.Lab.P05_StackOFStrings;

public class Main {
    public static void main(String[] args) {
       /* Stack stack = new Stack(new ArrayDeque<>());*/
        StackOfStrings stack = new StackOfStrings();
        stack.push("one");
        stack.push("two");
        stack.push("three");

        System.out.println(stack.isEmpty());
        System.out.println(stack.peek());

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }


}
