import java.lang.reflect.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.undo.*;
import javax.swing.border.*;
import javax.swing.text.*;
import java.lang.*;
import java.io.*;
import java.awt.print.*;
class Notepad{
		JFrame f;

		JMenuBar mb;
		JMenu m[];
		JMenuItem mi[];
		JTextArea tf;
		JScrollPane scroll;
		Font fnt;
		JPanel p;
		Notepad(){
		try{
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			}
		catch(Exception e){
				System.out.println("Error setting native L&K" + e);
			}
			f = new JFrame("Untitled-PranavEditor");
			f.setSize(1174,586);
			f.setMaximizedBounds(new Rectangle(1174,586,1174,586));

			mb = new JMenuBar();
			mb.setPreferredSize(new Dimension(1168,31));
			f.add(mb,BorderLayout.NORTH);
			Listener o = new Listener(this);
			//SETTING MENUS
			m = new JMenu[4];
			String menu[] = {"File","Edit","Format","Help"};
			String menuicn[] = {"images/file.png","images/edit.png","images/format.png","images/help.jpg"};
			for(int i=0;i<4;i++){
					m[i] = new JMenu(menu[i]);
					m[i].setIcon(new ImageIcon(menuicn[i]));
					mb.add(m[i]);

				}
			//SETTING MENUITEMS
			mi = new JMenuItem[21];
			String menuitem[] = {"New","Open...","Save","Save As...","Page Setup...","Print...","Exit","Undo","Cut","Copy","Paste","Delete","Find...","Find Next...","Replace...","Go To...","Select All","Word Wrap","Font...","View Help","About PranavEditor"};
			String menuitemicn[] = {"images/new.jpg","images/open.png","images/save.jpg","images/save as.jpg","images/page setup.jpg","images/print.jpg","images/exit.png","images/undo.jpg","images/cut.png","images/copy.png","images/paste.png","images/delete.gif","images/find.jpg","images/font.png","images/aboutnotepad.png"};

			for(int i=0;i<7;i++){
				if(i==3 || i==5)
					m[0].addSeparator();
				mi[i] = new JMenuItem(menuitem[i]);
				mi[i].addActionListener(o);
				mi[i].setIcon(new ImageIcon(menuitemicn[i]));
				m[0].add(mi[i]);

			}
			for(int i=7;i<13;i++){
				if(i==8 || i==11)
					m[1].addSeparator();
				mi[i] = new JMenuItem(menuitem[i]);
				mi[i].addActionListener(o);
				mi[i].setIcon(new ImageIcon(menuitemicn[i]));
				m[1].add(mi[i]);

			}
			for(int i=13;i<17;i++){
				if(i==15)
					m[1].addSeparator();
				mi[i] = new JMenuItem(menuitem[i]);
				mi[i].addActionListener(o);
				m[1].add(mi[i]);

			}
			for(int i=17;i<19;i++){
				mi[i] = new JMenuItem(menuitem[i]);
				mi[i].addActionListener(o);

				m[2].add(mi[i]);

			}
			mi[18].setIcon(new ImageIcon(menuitemicn[13]));
			for(int i=19;i<20;i++){

				mi[i] = new JMenuItem(menuitem[i]);
				mi[i].addActionListener(o);
				m[3].add(mi[i]);

			}
			for(int i=20;i<21;i++){
				m[3].addSeparator();
				mi[i] = new JMenuItem(menuitem[i]);
				mi[i].addActionListener(o);
				mi[i].setIcon(new ImageIcon(menuitemicn[i-6]));
				m[3].add(mi[i]);

			}

			m[0].setMnemonic(KeyEvent.VK_F);
			m[1].setMnemonic(KeyEvent.VK_E);
			m[2].setMnemonic(KeyEvent.VK_O);
			m[3].setMnemonic(KeyEvent.VK_H);
			mi[0].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
			mi[1].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));
			mi[2].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
			mi[5].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,ActionEvent.CTRL_MASK));
			mi[7].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,ActionEvent.CTRL_MASK));
			mi[8].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.CTRL_MASK));
			mi[9].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.CTRL_MASK));
			mi[10].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,ActionEvent.CTRL_MASK));
			mi[12].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,ActionEvent.CTRL_MASK));
			mi[13].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3,ActionEvent.ALT_MASK));
			mi[14].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,ActionEvent.CTRL_MASK));
			mi[15].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G,ActionEvent.CTRL_MASK));
			mi[16].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.CTRL_MASK));




			tf = new JTextArea(26,87);
			fnt = new Font("Arial",Font.PLAIN,16);
			tf.setFont(fnt);
			scroll = new JScrollPane(tf);
			scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
			p = new JPanel();
			p.add(scroll);
			f.add(p);
			f.setLayout(new FlowLayout(0,0,FlowLayout.CENTER));
			f.setResizable(false);
			f.setVisible(true);
			tf.addKeyListener(o);
		}

	}
	class Listener implements ActionListener,KeyListener{
			Notepad n;
			String str;
			DefaultListModel l = new DefaultListModel();
			JList list = new JList(l);
			DefaultListModel lc = new DefaultListModel();
			JList listc = new JList(lc);
			Caret caret;
			JPanel pa;
			int x;
			String fiop;
			JScrollPane scroll2;
			JScrollPane scroll3;
			Point p;
			Class c;
			String strimp;
			JDialog dialog;
			JButton[] b;
			int count=0;
			int countx =0;
				String fino;

			Listener(Notepad n){
					this.n = n;
				}
			public void actionPerformed(ActionEvent ea){
				for(int i=0;i<21;i++){
					if(ea.getSource()==n.mi[i]){
						if(i==0){
							//NEW
							if(n.tf.getText().length()!=0){
								dialog = new JDialog(n.f,"Notepad");


							dialog.setLayout(new FlowLayout());
							JLabel lb = new JLabel("Do you want to save Untitled ?");
							lb.setFont(new Font("Arial",Font.PLAIN,20));
							dialog.add(lb);

							 b = new JButton[3];
							 b[0] = new JButton("Save");
							 b[0].setSize(new Dimension(20,25));
							 dialog.add(b[0]);

							 b[1] = new JButton("Don't Save");
							 b[1].setSize(new Dimension(20,25));
							 dialog.add(b[1]);

							 b[2] = new JButton("Cancel");
							 b[2].setSize(new Dimension(20,25));
							 dialog.add(b[2]);
							 b[0].addActionListener(new ActionListener(){
								 	public void actionPerformed(ActionEvent eol){
										JFileChooser sv = new JFileChooser();
										int option = sv.showSaveDialog(n.tf);
										if (option == JFileChooser.APPROVE_OPTION){
										try {
											BufferedWriter out = new BufferedWriter(new FileWriter(sv.getSelectedFile().getPath()));
											String namef = sv.getSelectedFile().getPath();
											//fiop = namef;
											int pointer = namef.lastIndexOf('\\');
											namef = namef.substring(pointer + 1);
											n.f.setTitle(namef);
											out.write(n.tf.getText());
											out.close();
									}
								catch (Exception ex) {
										System.out.println(ex.getMessage());
									}
								}


									}
								 });
							 b[1].addActionListener(new ActionListener(){
								 	public void actionPerformed(ActionEvent eol){
										dialog.setVisible(false);
										n.tf.setText("");
									}
								 });

							 b[2].addActionListener(new ActionListener(){
								 	public void actionPerformed(ActionEvent eol){
										dialog.setVisible(false);

									}
								 });
							dialog.setSize(350,125);
							dialog.setForeground(Color.white);
							dialog.setVisible(true);
							}
						}
						else if (i==1){
								JFileChooser op = new JFileChooser();
								int option = op.showOpenDialog(n.tf);
								if (option == JFileChooser.APPROVE_OPTION){
								n.tf.setText("");
								try{
									Scanner scan = new Scanner(new FileReader(op.getSelectedFile().getPath()));
									while (scan.hasNext()){
										n.tf.append(scan.nextLine());
										n.tf.setText(n.tf.getText() + "\n");
									}
								} catch (Exception ex){
									System.out.println(ex.getMessage());
								}
							}
						}
						else if (i==2){
							if(n.f.getTitle()=="Untitled-PranavEditor"){
								JFileChooser sv = new JFileChooser();
								int option = sv.showSaveDialog(n.tf);
								if (option == JFileChooser.APPROVE_OPTION){
								try {
									BufferedWriter out = new BufferedWriter(new FileWriter(sv.getSelectedFile().getPath()));
									String namef = sv.getSelectedFile().getPath();
									fiop = namef;
									int pointer = namef.lastIndexOf('\\');
									namef = namef.substring(pointer + 1);
									n.f.setTitle(namef);
									out.write(n.tf.getText());
									out.close();
									}
								catch (Exception ex) {
										System.out.println(ex.getMessage());
									}
								}
							}
							else{
								try {
									BufferedWriter out = new BufferedWriter(new FileWriter(fiop));
									out.write(n.tf.getText());
									out.close();
									}
								catch (Exception ex) {
										System.out.println(ex.getMessage());
									}
								}



						}
						else if (i==3){
								JFileChooser sv = new JFileChooser();
								int option = sv.showSaveDialog(n.tf);
								if (option == JFileChooser.APPROVE_OPTION){
								try {
									BufferedWriter out = new BufferedWriter(new FileWriter(sv.getSelectedFile().getPath()));
									String namef = sv.getSelectedFile().getPath();
									int pointer = namef.lastIndexOf('\\');
									namef = namef.substring(pointer + 1);
									n.f.setTitle(namef);
									out.write(n.tf.getText());
									out.close();
									}
								catch (Exception ex) {
										System.out.println(ex.getMessage());
									}
								}
						}
						else if(i==4){
							PrinterJob pj = PrinterJob.getPrinterJob();
							PageFormat pf = pj.pageDialog(pj.defaultPage());

						}
						else if(i==5){
							try{
								boolean c = n.tf.print();
							}
							catch(Exception eop){
								System.out.println(eop);
							}
						}
						else if(i==6){
							System.exit(0);

						}/*
						else if(i==7){
									UndoManager undoManager  = new UndoManager();
									n.tf.getDocument().addUndoableEditListener(new UndoableEditListener(){
									public void undoableEditHappened(UndoableEditEvent ue){
											undoManager.addEdit(ue.getEdit());

										}
									});
									try{
										undoManager.undo();
									}
									catch(Exception ueo){
									}
						}*/
						else if(i==8){
							n.tf.cut();

						}
						else if(i==9){
							n.tf.copy();

						}
						else if(i==10){
							n.tf.paste();

						}
						else if(i==11){
							n.tf.replaceSelection("");

						}
						else if(i==16){
							n.tf.selectAll();

						}
						else if(i==17){
							n.tf.setLineWrap(true);
						}
						else if(i==18){
							CreateDialog cd = new CreateDialog();
							MyFont f = cd.getFont1();
							Font font = new Font(f.fontName,f.fontStyle,f.size);
							n.tf.setFont(font);
							cd.d.setVisible(true);

						}
						else{
							JDialog dio = new JDialog(n.f,"INFO.");
							dio.setSize(300,150);
							JLabel ldio = new JLabel("	NOT AVAILABLE!!");
							ldio.setFont(new Font("Arial",Font.PLAIN,20));
							dio.add(ldio);
							dio.setForeground(Color.WHITE);
							dio.setVisible(true);
						}
					}
				}
			}
			public void keyReleased(KeyEvent e){
					str = n.tf.getText();
					Li o = new Li(this,this.n);
					list.addMouseListener(o);
					Lic ob = new Lic(this,this.n);
					listc.addMouseListener(ob);

					try{
						char ch = e.getKeyChar();
						if(ch!='.' && pa.isVisible()==true)
							pa.setVisible(false);


						//PACKAGES(UTIL,LANG,ETC...)

					 if(ch=='.'){
						l.removeAllElements();
						lc.removeAllElements();
						String stri;
						x = str.lastIndexOf('.');
						int y2= str.lastIndexOf(';',x);
						if(y2==-1)
						{
							strimp = str.substring(y2+1,x);
							strimp = strimp + ".";
							strimp = strimp.replaceAll("\\s+","");
							if(count>0){
									count = 0;
									int dot = n.tf.getText().lastIndexOf(".",x-1);

									String pack = n.tf.getText().substring(dot+1,x);
									pa = new JPanel();
									pa.setSize(220,145);
									pa.setVisible(true);
									scroll3= new JScrollPane();
									scroll3.setViewportView(listc);

									scroll3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
									pa.add(scroll3);
									n.tf.add(pa);
									caret = n.tf.getCaret();
									p = caret.getMagicCaretPosition();
									p.x += n.tf.getLocationOnScreen().x;
									p.y += n.tf.getLocationOnScreen().y;
										p.x = p.x;
										p.y = p.y - 44;
									pa.setLocation(p);

									File file = new File("java" + "/" + pack);
									String[] names = file.list();
									for(String name: names){
										lc.addElement(name);

									}
									Font font = new Font("Arial",Font.ITALIC,12);
									listc.setFont(font);
									listc.setFixedCellWidth(200);

									n.tf.add(pa);


							}

							else if(countx>0){
								countx = 0;
								int dot = n.tf.getText().lastIndexOf(".",x-1);
								String pack = n.tf.getText().substring(dot+1,x);
								pa = new JPanel();
									pa.setSize(220,145);
									pa.setVisible(true);
									scroll3= new JScrollPane();
									scroll3.setViewportView(listc);

									scroll3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
									pa.add(scroll3);
									n.tf.add(pa);
									caret = n.tf.getCaret();
									p = caret.getMagicCaretPosition();
									p.x += n.tf.getLocationOnScreen().x;
									p.y += n.tf.getLocationOnScreen().y;
										p.x = p.x;
									p.y = p.y - 44;
									pa.setLocation(p);

									File file = new File("javax" + "/" + pack);
									String[] names = file.list();
									for(String name: names){
										lc.addElement(name);

									}
									Font font = new Font("Arial",Font.ITALIC,12);
									listc.setFont(font);
									listc.setFixedCellWidth(200);
									n.tf.add(pa);


							}




							else if(strimp.equalsIgnoreCase("importjava.")){
								try{
									count++;

									pa = new JPanel();
									pa.setSize(100,240);
									pa.setVisible(true);
									n.tf.add(pa);
									caret = n.tf.getCaret();
									p = caret.getMagicCaretPosition();
									p.x += n.tf.getLocationOnScreen().x;
									p.y += n.tf.getLocationOnScreen().y;
										p.x = p.x ;
									p.y = p.y - 44;
									pa.setLocation(p);

									File file = new File("java");
									String[] names = file.list();
									for(String name: names){

										l.addElement(name);

									}
									Font font = new Font("Arial",Font.ITALIC,12);
									list.setFont(font);
									list.setFixedCellWidth(100);
									pa.add(list);
									n.tf.add(pa);

								}
								catch(Exception eeol ){
									System.out.println(eeol);
								}

							}

							else if(strimp.equalsIgnoreCase("importjavax.")){
								try{
									countx++;
									pa = new JPanel();
									pa.setSize(100,240);
									pa.setVisible(true);
									n.tf.add(pa);
									caret = n.tf.getCaret();
									p = caret.getMagicCaretPosition();
									p.x += n.tf.getLocationOnScreen().x;
									p.y += n.tf.getLocationOnScreen().y;
								p.x = p.x ;
									p.y = p.y - 44;
									pa.setLocation(p);

									File file = new File("javax");
									String[] names = file.list();
									for(String name: names){

										l.addElement(name);

									}
									Font font = new Font("Arial",Font.ITALIC,12);
									list.setFont(font);
									list.setFixedCellWidth(100);
									pa.add(list);
									n.tf.add(pa);
								}
								catch(Exception eeol ){
									System.out.println(eeol);
									}
								}
					}

					else if(y2!=-1)
						{
							strimp = str.substring(y2+1,x);
							strimp = strimp + ".";
							strimp = strimp.replaceAll("\\s+","");
							if(count>0){
								count = 0;
								int dot = n.tf.getText().lastIndexOf(".",x-1);
								String pack = n.tf.getText().substring(dot+1,x);

								pa = new JPanel();
									pa.setSize(220,145);
									pa.setVisible(true);
									scroll3= new JScrollPane();
									scroll3.setViewportView(listc);

									scroll3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
									pa.add(scroll3);
									n.tf.add(pa);
									caret = n.tf.getCaret();
									p = caret.getMagicCaretPosition();
									p.x += n.tf.getLocationOnScreen().x;
									p.y += n.tf.getLocationOnScreen().y;
									p.x = p.x ;
									p.y = p.y - 44;
									pa.setLocation(p);

									File file = new File("java" + "/" + pack);
									String[] names = file.list();
									for(String name: names){
										lc.addElement(name);

									}
									Font font = new Font("Arial",Font.ITALIC,12);
									listc.setFont(font);
									listc.setFixedCellWidth(200);
									n.tf.add(pa);


							}

							else if(countx>0){
								countx = 0;
								int dot = n.tf.getText().lastIndexOf(".",x-1);
								String pack = n.tf.getText().substring(dot+1,x);
								pa = new JPanel();
									pa.setSize(220,145);
									pa.setVisible(true);
									scroll3= new JScrollPane();
									scroll3.setViewportView(listc);

									scroll3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
									pa.add(scroll3);
									n.tf.add(pa);
									caret = n.tf.getCaret();
									p = caret.getMagicCaretPosition();
									p.x += n.tf.getLocationOnScreen().x;
									p.y += n.tf.getLocationOnScreen().y;
												p.x = p.x ;
									p.y = p.y - 44;
									pa.setLocation(p);

									File file = new File("javax" + "/" + pack);
									String[] names = file.list();
									for(String name: names){
										lc.addElement(name);

									}
									Font font = new Font("Arial",Font.ITALIC,12);
									listc.setFont(font);
									listc.setFixedCellWidth(200);
									n.tf.add(pa);


							}
							else if(strimp.equalsIgnoreCase("importjava.")){
								try{
									count++;
									pa = new JPanel();
									pa.setSize(100,240);
									pa.setVisible(true);
									n.tf.add(pa);
									caret = n.tf.getCaret();
									p = caret.getMagicCaretPosition();
									p.x += n.tf.getLocationOnScreen().x;
									p.y += n.tf.getLocationOnScreen().y;
												p.x = p.x ;
									p.y = p.y - 44;
									pa.setLocation(p);

									File file = new File("java");
									String[] names = file.list();
									for(String name: names){

										l.addElement(name);

									}
									Font font = new Font("Arial",Font.ITALIC,12);
									list.setFont(font);
									list.setFixedCellWidth(100);
									pa.add(list);
									n.tf.add(pa);

								}
								catch(Exception eeol ){
									System.out.println(eeol);
								}

							}
							else if(strimp.equalsIgnoreCase("importjavax.")){
								try{
									countx++;
									pa = new JPanel();
									pa.setSize(100,240);
									pa.setVisible(true);
									n.tf.add(pa);
									caret = n.tf.getCaret();
									p = caret.getMagicCaretPosition();
									p.x += n.tf.getLocationOnScreen().x;
									p.y += n.tf.getLocationOnScreen().y;
												p.x = p.x ;
									p.y = p.y - 44;
									pa.setLocation(p);

									File file = new File("javax");
									String[] names = file.list();
									for(String name: names){
										l.addElement(name);

									}
									Font font = new Font("Arial",Font.ITALIC,12);
									list.setFont(font);
									list.setFixedCellWidth(100);
									pa.add(list);
									n.tf.add(pa);
								}
								catch(Exception eeol ){
									System.out.println(eeol);
									}
							}




							//GETTING METHODS
							else {
							stri="";
							int y1 = str.lastIndexOf(' ',x);
							y2= str.lastIndexOf(';',x);
							int y;
							if(y1>=y2)
							{
								y = y1;
								stri = str.substring(y+1,x);
							}
							else{

								y = y2;
								stri = str.substring(y+2,x);
							}
							stri = stri.replaceAll("\\s","");
							int cmod=0;
							Package[] packageso = Package.getPackages();

								for(Package p : packageso){
									String pack = p.getName();
									String tentative = pack + "." + stri;
									try{
										Class.forName(tentative);

										}
									catch(ClassNotFoundException eo){
											continue;
										}

									c = Class.forName(pack + "." + stri);
									cmod++;
									break;
								}

							if(cmod>0){
									cmod=0;
									pa = new JPanel();
									pa.setSize(420,150);
									pa.setVisible(true);
									scroll2= new JScrollPane();
									scroll2.setViewportView(list);

									scroll2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
									pa.add(scroll2);
									n.tf.add(pa);
									caret = n.tf.getCaret();
									p = caret.getMagicCaretPosition();
									p.x += n.tf.getLocationOnScreen().x;
									p.y += n.tf.getLocationOnScreen().y;
												p.x = p.x ;
									p.y = p.y - 44;
									pa.setLocation(p);

									Field fo[] = c.getFields();
								 for(int cou =0;cou<fo.length;cou++){


											if(cou==fo.length-1){

												fino =  fo[cou].getName();
											}
											else{
												fino =  fo[cou].getName();
											}



											l.addElement(fino);
									}
									Method m[] = c.getMethods();
								for(int cou =0;cou<m.length;cou++){
									fino = m[cou].getReturnType().getName();
									fino = fino + " " + m[cou].getName() + "(";
									Class type[] = m[cou].getParameterTypes();
									for(int cu=0;cu<type.length;cu++){

											if(cu==type.length-1){

												fino = fino + type[cu].getName();
											}
											else{
												fino = fino + type[cu].getName() + ",";
											}
										}

											fino = fino + ")";
											l.addElement(fino);
									}
									Font font = new Font("Arial",Font.ITALIC,12);
									list.setFont(font);
									list.setFixedCellWidth(400);
									n.tf.add(pa);
									stri = null;




								}
							else{

							stri = " " + stri;
							int a = str.indexOf(stri);
							int b;
							int b1 = str.lastIndexOf(';',a);
							int b2= str.lastIndexOf('{',a);
							if(b1>=b2)
								b=b1;
							else
								b=b2;

							stri = str.substring(b+1,a);
							stri = stri.replaceAll("\\s","");

						}
						if(stri.indexOf('(')==-1&& stri.indexOf(')')==-1 && stri.indexOf(';')==-1 && stri.indexOf('=')==-1 && stri.equalsIgnoreCase("import")==false ){

							if(stri.indexOf('.')== -1 && stri.length()>1)
							{
									pa = new JPanel();
									pa.setSize(420,150);
									pa.setVisible(true);
									scroll2= new JScrollPane();
									scroll2.setViewportView(list);

									scroll2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
									pa.add(scroll2);
									n.tf.add(pa);
									caret = n.tf.getCaret();
									p = caret.getMagicCaretPosition();
									p.x += n.tf.getLocationOnScreen().x;
									p.y += n.tf.getLocationOnScreen().y;
												p.x = p.x ;
									p.y = p.y - 44;
									pa.setLocation(p);

							//GETTING PACKAGES
								Package[] packages = Package.getPackages();
								String fin;
								for(Package p : packages){
									String pack = p.getName();
									String tentative = pack + "." + stri;
									try{
										Class.forName(tentative);

										}
									catch(ClassNotFoundException eo){
											continue;
										}

									c = Class.forName(pack + "." + stri);
									break;
								}



								Method m[] = c.getMethods();
								for(int cou =0;cou<m.length;cou++){
									fin = m[cou].getReturnType().getName();
									fin = fin + " " + m[cou].getName() + "(";
									Class type[] = m[cou].getParameterTypes();
									for(int cu=0;cu<type.length;cu++){

											if(cu==type.length-1){

												fin = fin + type[cu].getName();
											}
											else{
												fin = fin + type[cu].getName() + ",";
											}
										}

											fin = fin + ")";
											l.addElement(fin);
									}
									Font font = new Font("Arial",Font.ITALIC,12);
									list.setFont(font);
									list.setFixedCellWidth(400);
									n.tf.add(pa);
									stri = null;


						}
							else if(stri.length()>1)
							{
									String fin;
									pa = new JPanel();
									pa.setSize(420,150);
									pa.setVisible(true);
									scroll2= new JScrollPane();
									scroll2.setViewportView(list);

									scroll2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
									pa.add(scroll2);
									n.tf.add(pa);
									caret = n.tf.getCaret();
									p = caret.getMagicCaretPosition();
									p.x += n.tf.getLocationOnScreen().x;
									p.y += n.tf.getLocationOnScreen().y;
												p.x = p.x ;
									p.y = p.y - 44;
									pa.setLocation(p);
									c = Class.forName(stri);

									Method m[] = c.getMethods();
									for(int cou =0;cou<m.length;cou++){
										fin = m[cou].getReturnType().getName();
										fin = fin + " " + m[cou].getName() + "(";
										Class type[] = m[cou].getParameterTypes();
										for(int cu=0;cu<type.length;cu++){

												if(cu==type.length-1){

												fin = fin + type[cu].getName();
											}
											else{

											fin = fin + type[cu].getName() + ",";
											}

										}

											fin = fin + ")";
											l.addElement(fin);
										}
										Font font = new Font("Arial",Font.ITALIC,12);
										list.setFont(font);
										list.setFixedCellWidth(400);
										n.tf.add(pa);

									stri = null;

								}
						}

							}
						}
						stri = "";

						}//else if block
					}//try



					catch(Exception ee){

						}

				}
			public void keyPressed(KeyEvent e){}


			public void keyTyped(KeyEvent e){}



		}
	class MyFont{
			String fontName;
			int fontStyle;
			int size;
			void setdata(String a,int b,int i){
				fontName = a;
				fontStyle = b;
				size = i;

			}


		}
	class CreateDialog extends JFrame implements ActionListener,ItemListener{
		JDialog d;
		MyFont font;
		JLabel sample;
		JButton jb1,jb2;
		JComboBox fontcombo,jcb1,jcb2;
		JLabel lb1,lb2,lb3;
		GraphicsEnvironment ge;
		CreateDialog(){
			d = new JDialog(this,"Font",true);
			lb1 = new JLabel("Font:");
			lb2 = new JLabel("Font Style:");
			lb3 = new JLabel("Size:");
			//creating font dialog box

			ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			String text[] = ge.getAvailableFontFamilyNames();
			fontcombo = new JComboBox(text);
			fontcombo.addItemListener(this);
			String fontStyle[] = {"Regular","Italic","Bold","Bold-Italic"};
			jcb1 = new JComboBox(fontStyle);
			jcb1.addItemListener(this);
			String fontSize[] = {"8","9","10","12","14","16","20","24","26","32","36","48","72"};
			jcb2 = new JComboBox(fontSize);
			jcb2.addItemListener(this);
			JPanel panel1 = new JPanel();
			panel1.add(lb1);
			panel1.add(fontcombo);
			panel1.add(lb2);
			panel1.add(jcb1);
			panel1.add(lb3);
			panel1.add(jcb2);
			panel1.setLayout(new GridLayout(3,2,30,30));
			JPanel panel2 = new JPanel();
			Border b1 = BorderFactory.createTitledBorder("Sample");
			panel2.setBorder(b1);
			sample = new JLabel("AaBbYyZz");
			panel2.add(sample);
			panel2.setLayout(new GridLayout(1,1));
			jb1 = new JButton("Ok");
			jb1.setBounds(20,20,100,100);
			jb1.addActionListener(this);
			jb2 = new JButton("Cancel");
			jb2.setBounds(20,20,100,100);
			jb2.addActionListener(this);
			JPanel panel3 = new JPanel();
			panel3.add(jb1);
			panel3.add(jb2);
			d.add(panel1);
			d.add(panel2);
			d.add(panel3);
			d.setLayout(new GridLayout(2,1,30,30));
			d.setSize(400,400);
			d.setResizable(false);
			d.setVisible(true);

		}

		public void itemStateChanged(ItemEvent erro){
			String name = (String)fontcombo.getSelectedItem();
			int style = jcb1.getSelectedIndex();
			int sze = Integer.parseInt((String)jcb2.getSelectedItem());
			Font font = new Font(name,style,sze);
			sample.setFont(font);

		}
		MyFont getFont1(){
			return font;
		}
		public void actionPerformed(ActionEvent erroy){
			int x = 0;
			if(erroy.getSource()==jb1){
				String name = (String)fontcombo.getSelectedItem();
				int style = jcb1.getSelectedIndex();
				int sze = Integer.parseInt((String)jcb2.getSelectedItem());
				font = new MyFont();
				font.setdata(name,style,sze);
				x=1;


			}
			if(x==1 || erroy.getActionCommand()=="Cancel"){
				d.setVisible(false);
			}
		}


	}

	class Li extends MouseAdapter{
		Listener o;
		Notepad n;
		Li(Listener o,Notepad n){
			this.o = o;
			this.n=n;

	}
		int si;

		public void mouseClicked(MouseEvent e){
			if(o.pa.isVisible()){
				si = o.list.getSelectedIndex();
				String sis = (String)o.l.getElementAt(si);
				int index = sis.indexOf(" ");
				String sisf = sis.substring(index+1);
				StringBuffer sb = new StringBuffer(n.tf.getText());
				//sb.insert(n.tf.getCaretPosition(),sisf);
				sb.insert(o.x+1,sisf);
				n.tf.setText(sb.toString());

				o.l.removeAllElements();
				o.pa.setVisible(false);


			}

			}

}

	class Lic extends MouseAdapter{
		Listener o;
		Notepad n;
		Lic(Listener o,Notepad n){
			this.o = o;
			this.n=n;

	}
		int sio;

		public void mouseClicked(MouseEvent e){
			if(o.pa.isVisible()){
				sio = o.listc.getSelectedIndex();
				String sisc = (String)o.lc.getElementAt(sio);

				int index1 = sisc.lastIndexOf(".");

				String sisfc = sisc.substring(0,index1);
				StringBuffer sb1 = new StringBuffer(n.tf.getText());
				//sb.insert(n.tf.getCaretPosition(),sisf);
				sb1.insert(o.x+1,sisfc + ";");
				n.tf.setText(sb1.toString());

				o.lc.removeAllElements();
				o.pa.setVisible(false);


			}

			}

}

class PranavEditor{
		public static void main(String s[]){
			new Notepad();
		}
	}