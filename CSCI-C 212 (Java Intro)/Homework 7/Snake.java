import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList; 

/**
 * creates a snake (currently of fixed length) created of circles that can move aroumd the screen given a direction and x, y starting position
 */
public class Snake {
  final static int R = 8; //the radius of the circles
  private int x, y; //the x, y position of the head of the snake
  private String direction; //the direction of the snake is headed
  ArrayList<Circle> body = new ArrayList<Circle>(); //the body of the snake maded of circles
  Circle head; //the head of the snake
  
  /**
   * sets the current direction of the snake
   * @param newDirection the new direction the snake should go
   */
  public void setDirection(String newDirection) {
    this.direction = newDirection;  
  }
  
  /**
   * creates a snake with an x, y of the head, and a starting direction
   * @param x the x cordinate of the head
   * @param y the y cordinate of the head
   * @param d the direction the snake should go
   */
  public Snake(int x, int y, String d) {
    this.x = x; 
    this.y = y;
    this.direction = d;
    this.head = new Circle(this.x + 0 * Snake.R, this.y + 0 * Snake.R, Snake.R, Color.BLUE, Color.YELLOW);
    
    //---------------------UPDATE THIS TO NOT HAVE A STARTING AMOUNT IN BODY-----------------------------
    // this.body.add( new Circle(this.x + 0 * Snake.R, this.y + 0 * Snake.R, Snake.R) ); 
    this.body.add( new Circle(this.x +  0 * Snake.R, this.y +  2 * Snake.R, Snake.R, Color.RED, Color.BLACK) ); // down
  }
  
  /**
   * draws the snake onto the graphics (given in game)
   */
  public void draw(Graphics g) {
    // g.drawString("I am here: ( " + this.x + ", " + this.y + ")", this.x, this.y); 
    this.head.draw(g);    
    for (Circle c : this.body)
      c.draw(g); 
  }
  
  /**
   * moves the snake by moving each body to the previous cordinate of the body and moving head in proper direction
   */
  public void move() {
    // System.out.println("I am moving!");  
    if ("north:south:east:west".contains(this.direction)) { 
      if (this.body.size() > 0) {
        for (int i = this.body.size() - 1; i > 0; i--) 
          this.body.get(i).moveTo(this.body.get(i-1).x(), this.body.get(i-1).y());
        this.body.get(0).moveTo(this.head.x(), this.head.y()); 
      }
      if (this.direction.equals("north")) this.y -= 2 * Snake.R; 
      else if (this.direction.equals("south")) this.y += 2 * Snake.R; 
      else if (this.direction.equals("west" )) this.x -= 2 * Snake.R; 
      else if (this.direction.equals("east" )) this.x += 2 * Snake.R;  
      this.head.moveTo(this.x, this.y); 
    }
  }
  
  /**
   * adds to body of snake. should be called after eating a food
   */
  public void addToBody() {
    this.body.add( new Circle(Integer.MAX_VALUE, Integer.MAX_VALUE, Snake.R, Color.RED, Color.BLACK) );//we don't care where the body is added because the next update cycle it will appear in the correct spot
  }
  
  /**
   * checks if there is a collision with itself
   * @return True if collided with self, false otherwise
   */
  public boolean collisionWithSelf() {
    for(Circle part: this.body) {
      if(part.x() == this.x && part.y() == this.y)
        return true;
    }
    return false;
  }
  
  /**
   * checks if snake went out of bounds too far
   */
  public boolean outOfBounds() {
    if(this.x < -200 ||
       this.x > 700 ||
       this.y < -200 ||
       this.y > 700) {
      return true;
    }
    return false;
  }
  
  /**
   * returns the current x value of head
   */
  public int x() { return this.x; }
  
  /**
   * returns the center of the snake head, for better collision detection
   */
  public int centerX() { return this.x + this.R; }
  
  /**
   * returns the current y value of head
   */
  public int y() { return this.y; }
  
  /**
   * returns the center of the snake head, for better collision detection
   */
  public int centerY() { return this.y + this.R; }
}
