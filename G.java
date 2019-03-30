
public class G implements Filter{
	
	public void filter(PixelImage pi){
		
		final int SCALE = 16;
		
		int[][] weights= new int[][] {{1,2,1},
								     {2,4,2}, //these are the weights
								     {1,2,1}};
								     
								     
		Pixel[][] data = pi.getData(); 
		Pixel[][] data2 =pi.getData();
		 
		
		int height = pi.getHeight();
		int width = pi.getWidth();
		
		data2 = PixelImage.weightedAverage(SCALE, data, data2, weights, height , width);  
		
		pi.setData(data2);					     
		
		
		
		
	}
	

}
