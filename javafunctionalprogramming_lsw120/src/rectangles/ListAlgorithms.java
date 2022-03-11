package rectangles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListAlgorithms {

  /**
   * Returns a new list of rectangles by translating (moving) each rectangle according to the given
   * distance vector.
   *
   * @param rectangles The rectangles to be translated
   * @param vector The distance vector
   * @return The translated rectangles
   */
  public static List<Rectangle> translate(List<Rectangle> rectangles, Point vector) {
    List<Rectangle> newRects = new ArrayList<>();
    for (Rectangle r : rectangles) {
      Point newtl = new Point(r.getTopLeft().getX() + vector.getX(),
              r.getTopLeft().getY() + vector.getY());
      Rectangle newr = new Rectangle(newtl, r.getWidth(), r.getHeight());
      newRects.add(newr);
    }
    return newRects;
  }

  /**
   * Returns a new list of rectangles by scaling each rectangle by a given amount.
   *
   * @param rectangles The rectangles to be scaled
   * @param factor A non-negative scale factor
   * @return The scaled rectangles
   */
  public static List<Rectangle> scale(List<Rectangle> rectangles, int factor) {
    List<Rectangle> newRects = new ArrayList<>();
    for (Rectangle r : rectangles) {
      Rectangle newr = new Rectangle(r.getTopLeft(), r.getWidth() * factor,
              r.getHeight() * factor);
      newRects.add(newr);
    }
    return newRects;
  }

  /** Returns a list containing, in order, the bottom-left point of each input rectangle. */
  public static List<Point> getBottomLeftPoints(List<Rectangle> rectangles) {
    List<Point> points = new ArrayList<>();
    for (Rectangle r : rectangles) {
      points.add(r.getBottomLeft());
    }
    return points;
  }

  /**
   * Returns a list containing all rectangles that intersect with the given rectangle.
   *
   * @param rectangles A list of rectangles to be checked for intersection
   * @param rectangle The rectangle against which intersection should be checked
   * @return All rectangles that do intersect with the given rectangle
   */
  public static List<Rectangle> getAllIntersecting(
      List<Rectangle> rectangles, Rectangle rectangle) {
    List<Rectangle> newRects = new ArrayList<>();
    for (Rectangle r : rectangles) {
      if (r.intersects(rectangle)){
        newRects.add(r);
      }
    }
    return newRects;
  }

  /**
   * Returns a list containing all rectangles with a bigger area than the given rectangle.
   *
   * @param rectangles A list of rectangles whose area is to be checked
   * @param rectangle The rectangle against which areas are to be compared
   * @return All rectangles that have a larger area than the given rectangle
   */
  public static List<Rectangle> getAllWithBiggerAreaThan(
      List<Rectangle> rectangles, Rectangle rectangle) {
    List<Rectangle> newRects = new ArrayList<>();
    for (Rectangle r : rectangles) {
      if (r.area() > rectangle.area()){
        newRects.add(r);
      }
    }
    return newRects;
  }

  /** Returns the largest area among the given rectangles. */
  public static int findLargestArea(List<Rectangle> rectangles) {
    int area = 0;
    for (Rectangle r : rectangles) {
      if (r.area() > area){
        area = r.area();
      }
    }
    return area;
  }

  /** Returns the largest height among all the given rectangles. */
  public static int findMaxHeight(List<Rectangle> rectangles) {
    int height = 0;
    for (Rectangle r : rectangles) {
      if (r.getHeight() > height){
        height = r.getHeight();
      }
    }
    return height;
  }

  /** Computes the sum of areas of all the given rectangles. */
  public static int getSumOfAreas(List<Rectangle> rectangles) {
    int area = 0;
    for (Rectangle r : rectangles) {
        area = area + r.area();
    }
    return area;
  }

  /**
   * Computes the sum of areas of all rectangles that intersect with the given rectangle.
   *
   * @param rectangles The rectangles whose areas to be considered and summed
   * @param rectangle The rectangle with which intersection is to be checked
   * @return The sum of areas of all rectangles that do intersect with the given rectangle
   */
  public static int getSumOfAreasOfAllIntersecting(
      List<Rectangle> rectangles, Rectangle rectangle) {
    return getSumOfAreas(getAllIntersecting(rectangles, rectangle));
  }

  /** Returns collection that maps each rectangle to its computed area. */
  public static Map<Rectangle, Integer> getAreaMap(List<Rectangle> rectangles) {
    Map<Rectangle, Integer> map = new HashMap<>();
    for (Rectangle r : rectangles) {
       map.put(r, r.area());
    }
    return map;
  }
}
