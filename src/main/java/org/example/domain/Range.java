package org.example.domain;

public class Range {

    int from;
    int to;

    private Range(int from, int to) {
        this.from = from;
        this.to = to;
    }

    public static Range of(int from,int to){

        if(from > to){
            throw new RuntimeException("Invalid range from value is greater than to value");
        }

        return  new Range(from,to);
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }
}
