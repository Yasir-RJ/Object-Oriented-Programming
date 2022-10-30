package se.kth.yasir.labb3.shapes;

import javafx.scene.paint.Color;

abstract public class FillableShape extends Shape{
    private boolean filled=false;

    protected FillableShape(double x, double y, Color color) {
        super(x, y, color);
    }

    public boolean isFilled(){
        return (this.filled);
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }
}
