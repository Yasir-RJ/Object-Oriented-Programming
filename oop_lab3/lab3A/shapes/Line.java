package se.kth.yasir.labb3.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Line extends Shape{
    private double x2,y2;

    public Line(){     // default constructor
        super();
        this.x2=100;
        this.y2=100;
    }
    public Line(double x, double y, double x2, double y2, Color color){   // copy constructor
        super(x,y,color);
        this.x2=x2;
        this.y2=y2;
    }

    public double getX2() {
        return x2;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }

    public double getY2() {
        return y2;
    }

    public void setY2(double y2) {
        this.y2 = y2;
    }

    @Override
    public void move(long elapsedTimeNs){
        super.move(elapsedTimeNs);
        x2=x2+getDx()*elapsedTimeNs/BILLION;
        y2=y2+getDy()*elapsedTimeNs/BILLION;
    }

    @Override
    public void  constrain(double boxX, double boxY,double boxWidth, double boxHeight) {
        double lengthX = getX2()-getX();
        double lengthY = getY2()-getY();
        super.constrain(boxX, boxY, boxWidth-lengthX, boxHeight-lengthY);
    }

    @Override
    public void paint(GraphicsContext gc) {
        gc.setStroke(getColor());
        gc.setLineWidth(2);
        gc.strokeLine(getX(),getY(),getX2(),getY2());
    }

    @Override
    public String toString() {
        return super.toString() + ", x2=" + getX2() + ", y2=" + getY2();
    }
}
