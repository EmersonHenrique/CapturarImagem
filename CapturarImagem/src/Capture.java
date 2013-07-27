import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.Buffer;
//http://www.youtube.com/watch?v=SFrqhjWNjBA
import javax.imageio.ImageIO;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;
import javax.media.control.FrameGrabbingControl;
import javax.media.format.VideoFormat;
import javax.media.util.BufferToImage;


public class Capture { 

	
	private static Player player = null;
	private static Image image = null;
	
	public static void main(String[] args)
	
	{
		try
		{
			MediaLocator ml = new MediaLocator("vfw://0"); 
			player = Manager.createRealizedPlayer(ml);
			player.start();
			
		}catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		while(image == null)
		{
			FrameGrabbingControl fgc = (FrameGrabbingControl) player.getControl("javax.media.control.FrameGrabbingControl");
			javax.media.Buffer buf = fgc.grabFrame();
			
			BufferToImage bi = new BufferToImage((VideoFormat)buf.getFormat());
			image= bi.createImage(buf);
		}
		
		try
		{
		  
		ImageIO.write(toBufferedImage(image),"png",new File("sreenshort3.png"));
		System.out.println("Image Saved!");
			
		}
		catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		player.close();
		player.deallocate();

	}
   public static BufferedImage toBufferedImage(Image i)
   {
	 BufferedImage bi = new BufferedImage(i.getWidth(null), i.getHeight(null),BufferedImage.TYPE_INT_ARGB);
	 Graphics2D g2d = bi.createGraphics();
	 g2d.drawImage(i, 0, 0, null);
	 
	 return bi;
   }
}
