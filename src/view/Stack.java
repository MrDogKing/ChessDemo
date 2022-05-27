package view;

import java.util.ArrayList;

public class Stack<T> {
    private ArrayList<T> elements ;
    public Stack (){elements = new ArrayList<>();}
    public void push(T value){
        elements.add(value);
    }
    public T pop(){
        if(elements.size() == 1){
            return elements.get(0) ;
        }
        else{
            elements.remove(elements.size() - 1) ;
            return elements.get(elements.size() - 1) ;
        }
    }
    public T init(){
        for (int i = elements.size() - 1; i > 0; i--) {
            elements.remove(i) ;
        }
        return elements.get(0);
    }

}
