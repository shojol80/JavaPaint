import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Dimension;
import javax.swing.ImageIcon;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JButton;


import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JList;





public  class Animate04  extends JPanel  {
   

    public static boolean isRunning = false;
    public static int lvlx=140;
    public static int  maximumwidth= 500;
   
  

    
 Thread animate;//Store ref to animation thread
    

  final JLabel label = new JLabel();

  JButton btnAction = new JButton("Start!");
  JButton btnadd = new JButton("Add!");
          
 DefaultListModel model = new DefaultListModel();
 JList list = new JList(model);

  
  //-------------------------------------------//
  public  Animate04(){//constructor


   
 
      
      setLayout(null);

       // setLayout(new BorderLayout()); //sets layout to border layout; default is flow layout
        //setLayout( new GridLayout( 1,1, 1, 1 ) ); //sets padding between widgets in gridlayout
       // setLayout(new FlowLayout(FlowLayout.LEADING, 1,20));
        setBackground( Color.GRAY ); //sets background color of panel to white
       
        add(btnAction);   
        add(label);  //adds a statuslabel to the south bord

        
        //label.setBounds(10, 10, 10, 10);
       // label.setLocation(600, 800);
        
       Dimension size = label.getPreferredSize();
       //label.setBounds(170, 20, size.width, size.height);
      
       
        setPreferredSize(new Dimension(200, 200));
        btnAction.setBounds(10, 0, 120, 23);

        
        add(list); 
        
        
        list.setBounds(10,25, 120,140);
        add(btnadd); 
        btnadd.setBounds(10,170,120,23);
 
        
        
        btnAction.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent arg0) {

            Animate04.this.start();
            
        }
        });
        
                
        btnadd.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent arg0) {

          Animate04.this.fileselect();
            
        }
        });
        

        
        

}
  
  
    public void fileselect() {
         
        
        JFileChooser fileChooser = new JFileChooser();

                fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
                fileChooser.setMultiSelectionEnabled(true);
                int result = fileChooser.showOpenDialog(this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String filename= selectedFile.getAbsolutePath();
                    
                    
                    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
                    int pos = list.getModel().getSize();

                  // model.add(pos, chooser.getSelectedFile());
                    model.add(pos,filename);
                   
                }
 
  }


  
  public void start() {
    if (this.isRunning) {
         this.isRunning = false;  
          animate.interrupt();

          while (animate.isAlive()){}//loop;
          animate = null;
          label.repaint();
          btnAction.setText("Start");

    } else {
        this.isRunning = true;
         animate = new Animate();
         animate.start();
         btnAction.setText("Stop");
    } 
  }
   

   //Ordinary inner class to animate the image
  class Animate extends Thread{

    public void run(){//begin run method
      try{
      	//The following code will continue to
      	// loop until the animation thread is
      	// interrupted by the mouseExited
      	// method.
        while(true){

          //Display several images in succession.
        
     
          
         for (int i = 0; i < model.size(); i++) {
	           System.out.println(model.get(i));
                 // public name= ;
                   
                 String selectedText = (String)model.get(i); // it works
                  
                  display(selectedText,1000);
          }
        
        }//end while loop
      }catch(Exception ex){
        if(ex instanceof InterruptedException){
          //Do nothing. This exception is
          // expected on mouseExited.
        }else{//Unexpected exception occurred.
          System.out.println(ex);
          System.exit(1);//terminate program
        }//end else
      }//end catch
    }//end run
    //-----------------------------------------//
    
    
    
    
    //This method displays an image and sleeps
    // for a prescribed period of time.  It
    // terminates and throws an
    // InterruptedException when interrupted
    // by the mouseExited method.
    void display(String image,int delay) 
                    throws InterruptedException, IOException{
      //Select and display an image.
          Dimension a=getSize();
          int maximum=a.width;
          Dimension size = label.getPreferredSize();
         if (Animate04.lvlx>=maximumwidth ) {
             Animate04.lvlx=140;
         }  else {
            Animate04.lvlx=Animate04.lvlx+2;
       }
         

      label.setBounds(Animate04.lvlx,40, size.width, size.height);
      
   File sourceimage = new File(image);
   Image image2 = ImageIO.read(sourceimage);
      
   
     ImageIcon imageIcon = new ImageIcon(new ImageIcon(image2).getImage().getScaledInstance(104, 124, Image.SCALE_DEFAULT));
      label.setIcon(imageIcon);
  
      label.repaint();

      //Check interrupt status.  If interrupted
      // while not asleep, force animation to
      // terminate.
      if(Thread.currentThread().interrupted())
        throw(new InterruptedException());
      //Delay specified number of msec.
      //Terminate animation automatically if
      // interrupted while asleep.
      Thread.currentThread().sleep(delay);
    }//end display method
    //-----------------------------------------//
  }//end inner class named Animate

 
 
 
}


    
