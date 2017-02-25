package MyselfCollection;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.NoSuchElementException;

class MyselfCollection<E> implements Iterable<E> {
    private int size;

    private Object[] objArray;

    public void add(Object o){
        Object[] temporaryObjArray = new Object[++size];
        System.arraycopy(objArray, 0, temporaryObjArray, 0, size - 1);
        temporaryObjArray[size - 1] = o;
        objArray = temporaryObjArray;
    }

    public void remove(int index){
        Object[] temporaryObjArray = new Object[--size];
        System.arraycopy(objArray, 0, temporaryObjArray, 0, index);
        if(size > index)
            System.arraycopy(objArray, index + 1, temporaryObjArray, index, size - index);
        objArray = temporaryObjArray;
    }

    public void replace(int index, Object o){
        if(index >= 0 || index < size)
            throw new IndexOutOfBoundsException();
        objArray[index] = o;
    }

    Object get(int index){
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        return objArray[index];
    }

    MyselfCollection(){
        objArray = new Object[size];
    }

    public Iterator iterator() {
        return new Iterator<Object>() {
            int curent = -1;

            public boolean hasNext() {
                return curent + 1 < size;
            }

            public Object next() {
                while (hasNext()){
                    curent++;
                    return objArray[curent];
                }
                throw new NoSuchElementException();
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public static void main(String[] args) {
        MyselfCollection<String> str  = new MyselfCollection<String>();

        Iterator it = str.iterator();

        str.add("1");
        str.add("4");
        str.add("8");
        str.add("8");
        str.remove(0);

        while (it.hasNext()){
            System.out.print(it.next());
        }
    }
}
