interface Interface<T extends List> {
    public T getValue();

    public static enum E {
        A(1),
        B(2);

        private int x;

        E(int x){
            this.x = x;
        }
    }
}
