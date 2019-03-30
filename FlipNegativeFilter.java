

public class FlipNegativeFilter implements Filter
{
	
	public void filter(PixelImage pi)
	  {	
		
		Pixel[][] pix = pi.getData();
	
		
	    for (int i = 0; i < pi.getHeight(); i++)
	    {
	      for (int j = 0; j < pi.getWidth(); j++)
	      {
	        pix[i][j].red = 255-pix[i][j].red;
	        pix[i][j].green = 255-pix[i][j].green;	        
	        pix[i][j].blue = 255-pix[i][j].blue;	        
	      }
	    }
	  
		
		
		
		pi.setData(pix);
		
		
	  }
	
	
		
	}

