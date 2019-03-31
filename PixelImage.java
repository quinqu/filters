import java.awt.image.*;
/**
 * Provides an interface to a picture as an array of Pixels
 */
public class PixelImage 
{
  private BufferedImage myImage;
  private int width;
  private int height;

  /**
   * Map this PixelImage to a real image
   * @param bi The image
   */
  public PixelImage(BufferedImage bi)
  {
    // initialize instance variables
    this.myImage = bi;
    this.width = bi.getWidth();
    this.height = bi.getHeight();
  }

  /**
   * Return the width of the image
   */
  public int getWidth()
  {
    return this.width;
  }

  /**
   * Return the height of the image
   */
  public int getHeight()
  {
    return this.height;
  }

  /**
   * Return the BufferedImage of this PixelImage
   */
  public BufferedImage getImage()
  {
    return this.myImage;
  }

  /**
   * Return the image's pixel data as an array of Pixels.  The
   * first coordinate is the x-coordinate, so the size of the
   * array is [width][height], where width and height are the
   * dimensions of the array
   * @return The array of pixels
   */
  public  Pixel[][] getData()
  {
    Raster r = this.myImage.getRaster();
    Pixel[][] data = new Pixel[r.getHeight()][r.getWidth()];
    int[] samples = new int[3];

    for (int row = 0; row < r.getHeight(); row++)
    {
      for (int col = 0; col < r.getWidth(); col++)
      {
        samples = r.getPixel(col, row, samples);
        Pixel newPixel = new Pixel(samples[0], samples[1], samples[2]);
        data[row][col] = newPixel;
      }
    }

    return data;
  }

  /**
   * Set the image's pixel data from an array.  This array matches
   * that returned by getData().  It is an error to pass in an
   * array that does not match the image's dimensions or that
   * has pixels with invalid values (not 0-255)
   * @param data The array to pull from
   */
  public void setData(Pixel[][] data)
  {
    int[] pixelValues = new int[3];     // a temporary array to hold r,g,b values
    WritableRaster wr = this.myImage.getRaster();

    if (data.length != wr.getHeight())
    {
      throw new IllegalArgumentException("Array size does not match");
    }
    else if (data[0].length != wr.getWidth())
    {
      throw new IllegalArgumentException("Array size does not match");
    }

    for (int row = 0; row < wr.getHeight(); row++)
    {
      for (int col = 0; col < wr.getWidth(); col++)
      {
        pixelValues[0] = data[row][col].red;
        pixelValues[1] = data[row][col].green;
        pixelValues[2] = data[row][col].blue;
        wr.setPixel(col, row, pixelValues);
      }
    }
  }

  /* this method computes the images middle pixel given weighted averages
   * 
   * @param SCALE The number you need to scale back down to pixel range
   * @param pi is the array of pixels from the image 
   * @param pi2 is another array of pixels 
   * @param weights The weights for each pixel
   * @param h The height of the image 
   * @param w The width of the pixel 
   * 
   */
  
  
  public static Pixel[][] weightedAverage(int SCALE, Pixel[][] pi, Pixel[][] pi2,int[][] weights, int h, int w) {
	  int weighRed1=0;
	  int weighBlue1=0;
	  int weighGreen1=0;
	  
	  int lastRow = pi2.length - 1;
	  int lastCol = pi2[0].length - 1;
	  pi2[0] = pi[0];
	  pi2[lastRow] = pi[lastRow];

	  for(int i = 1; i < lastRow; ++i) {
	      pi2[i][0] = pi[i][0];
	      pi2[i][lastCol] = pi[i][lastCol];
	  }

	  for (int i=1; i< h-1;i++) {
		  
		  for(int j=1;j< w-1;j++) {


			  
			  Pixel pixTL = pi[i-1][j-1];
			  Pixel pixTM = pi[i-1][j];
			  Pixel pixTR = pi[i-1][j+1];
			  Pixel pixL = pi[i][j-1];
			  Pixel middle = pi[i][j];
			  Pixel pixR = pi[i][j+1];
			  Pixel pixBL=pi[i+1][j-1];
			  Pixel pixBM=pi[i+1][j];
			  Pixel pixBR=pi[i+1][j+1];
			  
	

			  
			  weighRed1 = (((pixTL.red * weights[0][0]) + (pixTM.red*weights[0][1]) + (pixTR.red*weights[0][2])+ 
					  (pixL.red * weights[1][0]) + (middle.red*weights[1][1]) + (pixR.red*weights[1][2]) + 
					  (pixBL.red * weights[2][0]) + (pixBM.red * weights[2][1]) + (pixBR.red * weights[2][2]))/SCALE);
	
			  
			  
			  weighGreen1 =(((pixTL.green * weights[0][0])+ (pixTM.green*weights[0][1]) + (pixTR.green*weights[0][2])+
					  (pixL.green * weights[1][0])+ (middle.green * weights[1][1]) + (pixR.green*weights[1][2])+ (pixBL.green * weights[2][0])+ 
					  (pixBM.green*weights[2][1]) + (pixBR.green*weights[2][2]))/SCALE); 

			  
			   weighBlue1 =(((pixTL.blue * weights[0][0])+ (pixTM.blue*weights[0][1]) + (pixTR.blue*weights[0][2])+
					  (pixL.blue * weights[1][0])+ (middle.blue*weights[1][1])+(pixR.blue * weights[1][2])+ 
					  (pixBL.blue * weights[2][0])+ (pixBM.blue * weights[2][1]) + (pixBR.blue * weights[2][2]))/SCALE); 
			  

			  
			  
			  
      	      pi2[i][j]= withinRange(weighRed1, weighBlue1, weighGreen1);
		  }
  
	  }

	return pi2;   
  
  }
  
  /* this method makes sure the pixels are within the range (0-255)
   * 
   * @param pi the pixel array 
   * @param weighRed The red pixels
   * @param weighBlue The blue pixels 
   * @param weighGreen The green pixels 
   * 
   */
  
  public static Pixel withinRange(int weighRed, int weighBlue, int weighGreen) {
	  
  	
      if(weighRed < 0) {
      	weighRed = 0;
      	
      }else if(weighRed > 255) {             
      	weighRed = 255;
      }
      
      
      if(weighGreen < 0) {
      	weighGreen = 0;
      	
      }else if(weighGreen > 255) {             
    	  weighGreen = 255;
      }
      
      if(weighBlue < 0) {
    	  weighBlue = 0;
    	  
      }else if(weighBlue > 255) {             
    	  weighBlue = 255;
      }
      
      Pixel newPixel = new Pixel(weighRed, weighGreen, weighBlue);
      
      return newPixel;
      
      

  }
  

}
