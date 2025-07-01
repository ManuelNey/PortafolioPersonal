public class Rectangle {
    public int height;
    public int width;
    public Rectangle() {}
    public Rectangle(int height, int width) {
        this.height = height;
        this.width = width;
    }
    public int area()
    {
        return height * width;
    }
}
