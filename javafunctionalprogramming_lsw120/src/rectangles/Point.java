package rectangles;

public class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Point() {
        this(0,0);
    }
    public Point(int x) {
        this(x,0);
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public Point setX(int newX) {
        Point point = new Point();
        if (newX >= 0) {
            point = new Point(newX, getY());
        }
        return point;
    }
    public Point setY(int newY) {
        Point point = new Point();
        if (newY >= 0) {
            point = new Point(getX(), newY);
        }
        return point;
    }
    public Boolean isLeftOf(Point other){
        return (getX() < other.getX());
    }
    public Boolean isRightOf(Point other){
        return (getX() > other.getX());
    }
    public Boolean isAbove(Point other){
        return (getY() < other.getY());
    }
    public Boolean isBelow(Point other){
        return (getY() > other.getY());
    }
    public Point add(Point vector){
        return new Point(getX() + vector.getX(), getY() + vector.getY());
    }

}

