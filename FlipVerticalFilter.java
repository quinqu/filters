public class FlipVerticalFilter implements Filter
{
  public void filter(PixelImage pi)
  {
	Pixel[][] data = pi.getData();

	for (int i = 0; i< pi.getHeight()/2; i++)
	{
  	for (int j = 0; j < pi.getWidth(); j++)
  	{
    	Pixel temp = data[i][j];
    	data[i][j] = data[pi.getHeight() - 1-i][j];
    	data[pi.getHeight() - 1- i][j] = temp;
  	}
	}

	pi.setData(data);
  }
}
