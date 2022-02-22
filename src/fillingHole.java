import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class fillingHole {

    private index[][] img;
    weight weightCal;
    boolean connectivity; // true=4 boundary, false= 8 boundary
    List <index> boundaryIndexes;
    List <index> holeIndexes;

    public fillingHole(boolean connectivity, int z, float epsilon,index[][]img) {
        this.img=img;
        this.connectivity=connectivity;
        weightCal=new defaultWeight(z,epsilon);
        boundaryIndexes=new LinkedList<>();
        holeIndexes=new LinkedList<>();
        setLists();
    }
    private void setLists(){
        if(connectivity){
            for(int i=1;i<img.length-1;i++)
                for(int j=1;j<img[i].length-1;j++){
                    index temp=new index(i,j,img[i][j].getValue());
                    if(img[i][j].getValue()==-1)
                        holeIndexes.add(temp);
                    if(is4boundary(temp))
                        boundaryIndexes.add(temp);
                }
        }
        else{
            for(int i=1;i<img.length-1;i++)
                for(int j=1;j<img[i].length-1;j++){
                    index temp=new index(i,j,img[i][j].getValue());
                    if(img[i][j].getValue()==-1)
                        holeIndexes.add(temp);
                    if(is8boundary(temp))
                        boundaryIndexes.add(temp);
                }
        }
    }
    public void fillTheHole(String img_path) throws IOException {
        for(index idx: holeIndexes){
            img[idx.getRow()][idx.getCol()].setValue(fillHole(idx));
        }
        //creating a new filled image
        String imgPath=img_path.substring(0,img_path.lastIndexOf('.'));
        String imgType=img_path.substring((img_path.lastIndexOf('.')+1));
        BufferedImage output=new BufferedImage(img[0].length,img.length,BufferedImage.TYPE_INT_RGB);

        for(int i=0;i<img.length;i++){
            for(int j=0;j<img[i].length;j++){
                float color=img[i][j].getValue();
                int rgb=new Color(color,color,color).getRGB();
                output.setRGB(j,i,rgb);
            }
        }

        File toReturn = new File( imgPath + "filled." + imgType);
        ImageIO.write(output, imgType, toReturn);

    }

    private float fillHole(index idx){
        float sum=0,weightSum=0;
        for(index bound : boundaryIndexes){
            float weight=weightCal.calculateWeight(bound,idx);
            sum=sum+(weight*img[bound.getRow()][bound.getCol()].getValue());
            weightSum=weightSum+weight;
        }
        return sum/weightSum;
    }

    public void setWeightCal(weight weightCal) {
        this.weightCal = weightCal;
    }

    private boolean is8boundary(index idx){
        int col=idx.getCol(), row=idx.getRow();
        if(img[row][col].getValue()==-1)
            return false;
        if(img[row-1][col-1].getValue()==-1||img[row-1][col].getValue()==-1||img[row-1][col+1].getValue()==-1
                ||img[row][col+1].getValue()==-1||img[row+1][col+1].getValue()==-1||img[row+1][col].getValue()==-1
                ||img[row+1][col-1].getValue()==-1||img[row][col-1].getValue()==-1)
            return true;
        return false;
    }

    private boolean is4boundary(index idx){
        int col=idx.getCol(), row=idx.getRow();
        if(img[row][col].getValue()==-1)
            return false;
        if(img[row-1][col].getValue()==-1 ||img[row][col+1].getValue()==-1||img[row+1][col].getValue()==-1 ||img[row][col-1].getValue()==-1)
            return true;
        return false;
    }

    public static index[][] GeneratingMatrix(String image_file_path, String mask_file_path){
        index[][] matrix =null;
        //source: https://docs.oracle.com/javase/tutorial/2d/images/loadimage.html
        try {
            BufferedImage img = ImageIO.read(new File(image_file_path));
            BufferedImage mask = ImageIO.read(new File(mask_file_path));
            int height = img.getHeight();
            int width = img.getWidth();
            matrix=new index[height][width];
            Color black = Color.BLACK;


            for(int i=0;i<height;i++){
                for(int j=0;j<width;j++){
                    Color c = new Color(mask.getRGB(j,i));
                    if(c.equals(black))
                        matrix[i][j]= new index(i,j,-1);
                    else{
                        Color c1 = new Color(img.getRGB(j, i)); // change
                        float value=(float) ((c1.getBlue()+c1.getGreen()+c1.getRed())/3)/255;
                        matrix[i][j]=new index(i,j,value);
                    }
                }
            }
        } catch (IOException e) {
           e.printStackTrace();
        }
        return matrix;
    }
}
