package com.origgin.boafo.utils;

/**
 * Created by root on 6/25/17.
 */

public class TransactionTime {
    long start;
    long end;

    public TransactionTime() {
        this.start = 0;
        this.end = 0;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public long getDuration(){
        if (this.start>0 && this.end>0){
            return this.end - this.start;
        }else {
            return 0;
        }
    }
}