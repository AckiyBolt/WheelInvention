package ua.org.bolt.generics;

public class MultipleBoundedBox
        <T extends MultipleBoundedBox.A & MultipleBoundedBox.B & MultipleBoundedBox.C> extends Box<T> {

    public static class A {}
    public static interface B {}
    public static interface C {}
}
