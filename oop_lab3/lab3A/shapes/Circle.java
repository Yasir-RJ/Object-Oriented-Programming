package se.kth.yasir.labb3.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle extends FillableShape{
    private double diameter;

    public Circle(double x, double y, double diameter, Color color) {
        super(x,y,color);
        this.diameter = diameter;
    }

    public double getDiameter() {
        return this.diameter;
    }

    public void setDiameter(double diameter) {
        this.diameter = diameter;
    }

    @Override
    public void paint(GraphicsContext gc) {
        if(isFilled()){
            gc.setFill(getColor());
            gc.fillOval(getX(),getY(),getDiameter()/2,getDiameter()/2);
        }
        else{
            gc.setStroke(getColor());
            gc.strokeOval(getX(),getY(),getDiameter()/2,getDiameter()/2);
        }
    }

    @Override
    public void  constrain(double boxX, double boxY,double boxWidth, double boxHeight) {
        super.constrain(boxX, boxY, boxWidth-getDiameter()/2, boxHeight-getDiameter()/2);
    }

    @Override
    public String toString() {
        return super.toString() + ", Diameter=" + getDiameter();
    }
}
