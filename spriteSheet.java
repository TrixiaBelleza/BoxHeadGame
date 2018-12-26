import java.awt.image.BufferedImage;

public class spriteSheet{
    private BufferedImage sheet;
    private int height, width, frames;

    public spriteSheet(BufferedImage sheet, int frames){
        this.sheet = sheet;
        this.frames = frames;
        this.height = sheet.getHeight()/frames;
        this.width = sheet.getWidth();
    }

    public BufferedImage getState(int index){
        return sheet.getSubimage(0, index*this.height, this.width, this.height); //kinocrop yung image
    }
}
