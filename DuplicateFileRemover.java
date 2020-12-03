import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Hashtable;
//import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class DuplicateFileRemover {
	public static void main(String[] args) 
	{
		GUI obj=new GUI();
	}

}
class GUI
{
 public GUI()
 {
	 			JFrame f=new JFrame("Duplicate File Remover");
	 			JPanel p= new JPanel();
	 			JLabel l=new JLabel("Enter directory name");
	 			l.setBounds(30,0,80,25);
	 			JTextField dtext=new JTextField(20);
	 			dtext.setBounds(100,20,165,25);
	 			JButton b=new JButton("Submit");
	 			b.setBounds(10,80,80,25);
	 			b.addActionListener(new ActionListener()
	 			{
	 				public void actionPerformed(ActionEvent eobj)
	 				{
	 					DuplicteFileDelete uobj = new DuplicteFileDelete(dtext.getText());
	 					f.setVisible(false);
	 					//JOptionPane.showMessageDialog(null,"duplicate files dleted successfully ");
	 					//NewWindow o = new NewWindow();
	 				}
	 			});
	 			p.add(l);
	 			p.add(dtext);
	 			p.add(b);
	 			f.add(p);
	 			f.setVisible(true);
	 			f.setSize(370,250);
	 			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 }
}
class DuplicteFileDelete
{
	public DuplicteFileDelete(String src)
	{
		FileDelete(src);
		
	}
	public void FileDelete(String Dir )
	{
		try
		{
			JFrame f=new JFrame();
			if(Dir.length()==0)//check text field not empty
			{
				JOptionPane.showMessageDialog(f,"Invalid directory name.","Alert",JOptionPane.WARNING_MESSAGE);
				System.exit(0);
			}
		/*String Dir1;
       Scanner sobj=new Scanner(System.in);*/
	   
		FileInputStream istream=null;
		//System.out.println("Enter directory name");
		//Dir1 = sobj.next();
		File dobj=new File(Dir);
		//System.out.println(Dir);
		if(!dobj.exists())//check directory name exists
		{
				JOptionPane.showMessageDialog(f,"No such directory present in current directory.","Alert",JOptionPane.WARNING_MESSAGE);
		}
		
		File arr[]=dobj.listFiles();
		MessageDigest md = MessageDigest.getInstance("MD5");
		int ret=0;
		byte buffer[]=new byte[1024];
		String checksum=null;
		Hashtable<String,String> hm=new Hashtable<String,String>();
		int counte=0,countd=0;
		
		for(File filename : arr)
			{
				//if(filename.getName().endsWith(".txt"))//
				//{
					File fobj=new File(filename.getAbsolutePath());
					istream= new FileInputStream(filename.getAbsolutePath());
					if(fobj.length()==0)
					{
						istream.close();//since that file is in read mode so if this statement not written it cannot be deleted
						fobj.delete();
						counte++;	
					}
					else
					{
						while((ret=istream.read(buffer))>0)
						{
							md.update(buffer,0,ret);
						}
						byte hash[]=md.digest();
						checksum= new BigInteger(1,hash).toString(16);
						if(hm.containsKey(checksum))
						{		
							istream.close();//since that file is in read mode so if this statement not written it cannot be deleted
							fobj.delete();
							countd++;
						}
						else
						{
							hm.put(checksum,filename.getName());
						}
				//}
					}
			}
			JOptionPane.showMessageDialog(null,"Total duplicate files "+(counte+countd)+" deleted successfully.");
			}
		catch(Exception e)
		{
		}
		finally {}

	}
	
}
