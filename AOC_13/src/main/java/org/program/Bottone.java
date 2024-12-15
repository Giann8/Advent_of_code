package org.program;

public record Bottone(Long x, Long y,Integer calledtime, String label) {

    public Bottone calledBottone(){
        return new Bottone(x,y,calledtime+1, label);
    }
}
