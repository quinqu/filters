/* Report:
 * I did not add any additional filters 
 * 
 * Half of my filters work. I got the first four to work correctly while the others not. The only surprise I encountered was
 * that each time you click Gaussian, it blurs more and more. The filters I was not able to get working correctly were the Laplacian, Unsharp Masking, and Edgy. 
 * The code I currently have takes the shape of the image but not the right colors. 
 * 
 */
 
 
/**
 * A class to configure the SnapShop application
 *
 * @author (Quin Quintero)
 * @version (03/09/2018)
 * 
 */
public class SnapShopConfiguration
{
  /**
   * Method to configure the SnapShop.  Call methods like addFilter
   * and setDefaultFilename here.
   * @param theShop A pointer to the application
   */
  public static void configure(SnapShop theShop)
  {

    theShop.setDefaultFilename("seattle.jpg");
    theShop.addFilter(new FlipH(), "Flip Horizontal");
    theShop.addFilter(new FlipNegativeFilter(), "Negative Filter");
    theShop.addFilter(new FlipVerticalFilter(),"Vertical Filter");
    theShop.addFilter(new G(), "Gaussian");
    theShop.addFilter(new Laplacian(), "Laplacian");
    theShop.addFilter(new UnsharpMasking(), "Unsharp Masking");
    theShop.addFilter(new Edgy(),"Edgy");
    
      }
}