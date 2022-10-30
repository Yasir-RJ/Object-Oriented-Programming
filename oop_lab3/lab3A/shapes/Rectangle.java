package se.kth.yasir.labb3.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rectangle extends FillableShape{

    private double width,height;

    public Rectangle(double x, double y, double width, double height, Color color) {
        super(x, y, color);
        this.width=width;
        this.height=height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public void paint(GraphicsContext gc) {
        if(isFilled()){
            gc.setFill(getColor());
            gc.fillRect(getX(),getY(),getWidth(),getHeight());
        }
        else{
            gc.setStroke(getColor());
            gc.strokeRect(getX(),getY(),getWidth(),getHeight());
        }

    }

    @Override
    public void  constrain(double boxX, double boxY,double boxWidth, double boxHeight) {
        super.constrain(boxX, boxY, boxWidth-getWidth(), boxHeight-getHeight());
    }

    @Override
    public String toString() {
        return super.toString() + ", Width=" + getWidth()+ ", Height=" + getHeight();
    }
}
