package rectangles;

import java.util.Optional;
import java.util.stream.Stream;

public class Rectangle {
    private final int x1, x2, y1, y2;

    public Rectangle(Point topleft, int width, int height) {
        this.x1 = topleft.getX();
        this.y2 = topleft.getY() + height;
        this.x2 = topleft.getX() + width;
        this.y1 = topleft.getY();
    }
    public Rectangle(Point p1, Point p2) {
        if (p2.getX() >= p1.getX()) {
            this.x1 = p1.getX();
            this.x2 = p2.getX();
        } else {
            this.x1 = p2.getX();
            this.x2 = p1.getX();
        }
        if (p2.getY() >= p1.getY()) {
            this.y1 = p1.getY();
            this.y2 = p2.getY();
        } else {
            this.y1 = p2.getY();
            this.y2 = p1.getY();
        }
    }
    public Rectangle(int width, int height) {
        this.x1 = this.y1 = 0;
        this.x2 = width;
        this.y2 = height;
    }

    public int getWidth(){
        return x2 - x1;
    }

    public int getHeight(){
      return y2 - y1;
    }

    public Rectangle setWidth(int newWidth){
        return new Rectangle(getTopLeft(), newWidth, getHeight());
    }

    public Rectangle setHeight(int newHeight){
        return new Rectangle( getTopLeft(), getWidth(), newHeight);
    }

    public Point getTopLeft(){
        return new Point(x1,y1);
    }
    public Point getTopRight(){
        return new Point(x2,y1);
    }
    public Point getBottomLeft(){
        return new Point(x1,y2);
    }
    public Point getBottomRight(){
        return new Point(x2,y2);
    }

    public int area() {
        return getHeight() * getWidth();
    }

    public boolean intersects(Rectangle other){
        return !((other.x1 > this.x2)
                || (other.y1 > this.y2)
                || (other.x2 < this.x1)
                || (other.y2 < this.y1));
    }
    public Optional<Rectangle> intersection(Rectangle other){
        if (this.intersects(other)){
            Point p1 = new Point(Math.max(this.x1, other.x1), Math.max(this.y1,other.y1));
            Point p2 = new Point(Math.min(this.x2, other.x2), Math.min(this.y2,other.y2));
            Rectangle rect = new Rectangle(p1,p2);
            return Optional.of(rect);
                } else {
            return Optional.empty();
        }
            }
    }


