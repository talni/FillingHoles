import java.io.IOException;
import java.util.Scanner;

public class test {
    public static void main(String[]args) {
     Scanner in = new Scanner(System.in);
     int z;
     float epsilon;
     boolean connectivity;
     System.out.println("Enter Z please");
     z= in.nextInt();
     System.out.println("Enter epsilon please");
     epsilon=in.nextFloat();
     System.out.println("Please enter connectivity type: true for 4-connectivity, false for 8-connectivity.");
     connectivity=in.nextBoolean();
     in.nextLine();
     System.out.println("Enter image path please");
     String img_path=in.nextLine();
     System.out.println("Enter mask path please");
     String mask_path=in.nextLine();

     if(!img_path.substring(img_path.lastIndexOf('.')+1).equals("png")){
      String newImagePath=img_path.substring(img_path.lastIndexOf('.'))+".png";
       boolean succ = convertImage.imageConverter(img_path, newImagePath, "png");
       if(succ==false){
        System.out.println("Can not convert image");
        System.exit(1);
       }
      }

     index[][] img= fillingHole.GeneratingMatrix(img_path,mask_path);
     fillingHole toDo = new fillingHole(connectivity,z,epsilon,img);
     try {
      toDo.fillTheHole(img_path);
     }
     catch (IOException e){
      e.printStackTrace();;
     }






    }
}


