
public class FlipH implements Filter {
	  public class Gaussian {

	}

	public void filter(PixelImage pi)
	  {
		Pixel[][] data = pi.getData();

		for (int i = 0; i < pi.getHeight(); i++)
		{
	  	for (int j = 0; j < pi.getWidth() / 2; j++)
	  	{
	    	Pixel temp = data[i][j];
	    	data[i][j] = data[i][pi.getWidth() - 1-j];
	    	data[i][pi.getWidth() - 1-j] = temp;
	  	}
		}

		pi.setData(data);
	
	
	
	
	
	

}
}
