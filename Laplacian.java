
public class Laplacian implements Filter{
	
	public void filter(PixelImage pi) {
		
		int[][] weight = new int[][]{{-1,-1,-1},
								     {-1,8,-1}, //these are the weights
								     {-1,-1,-1}};
								     
			int SCALE = 1;
			
			
			Pixel[][] data = pi.getData(); 
										
			//Pixel[][] temp = new Pixel[pi.getHeight()][pi.getWidth()]; 
			Pixel[][] data2 =pi.getData();		
			int height = data2.length;
		    int width = data2[0].length;
		    
		    
		    
			data2 = PixelImage.weightedAverage(SCALE, data,data2, weight, height , width);  
			
			
			
			pi.setData(data2);					     
								     
			
		}

	}
	
	
	

