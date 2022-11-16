package ex01;

public class App {
    public static void main(String[] args) {
        Mouse m = new Mouse();
        Cat c = new Cat();
        DoorMan doorMan = new DoorMan();
        doorMan.쫒아내(m);
        doorMan.쫒아내(c);
    }
}
