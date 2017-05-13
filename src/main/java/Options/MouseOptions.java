package Options;

public enum MouseOptions {
    LEFT("left"),
    RIGHT("right");

    String type;

    MouseOptions(String type) { this.type = type; }
    public String getType() { return this.type; }
}
