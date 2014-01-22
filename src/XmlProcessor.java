import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Text;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class XmlProcessor {
	private LinkedList<Element> elementList;
	private LinkedList<BallThread> threadList;
	
	public XmlProcessor(LinkedList<BallThread> tL) {
		elementList = new LinkedList<Element>();
		threadList = tL;
		//readXML();
		
		//writeXML(threadList, elementList);
			
	}
	
	public void writeXML(String path){
		
		try {
			Document doc = new Document();
			Element theRoot = new Element("ballList");
			
			doc.setRootElement(theRoot);
			
			for (int i = 0; i < threadList.size(); i++) {				
				elementList.add(new Element("ball-" + (i + 1)));
				
				Element name = new Element("name");
				name.addContent(new Text(threadList.get(i).getBall().getBallName()));
				
				Element position_X = new Element("position-x");
				position_X.addContent(new Text("" + threadList.get(i).getBall().getPosX()));
				
				Element position_Y = new Element("position-y");
				position_Y.addContent(new Text("" + threadList.get(i).getBall().getPosY()));
				
				Element velocity_X = new Element("velocity-x");
				velocity_X.addContent(new Text("" + threadList.get(i).getBall().getVelocityX()));
				
				Element velocity_Y = new Element("velocity-y");
				velocity_Y.addContent(new Text("" + threadList.get(i).getBall().getVelocityY()));
				
				Element color_R = new Element("color-r");
				color_R.addContent(new Text("" + threadList.get(i).getBall().getColorR()));
				
				Element color_G = new Element("color-g");
				color_G.addContent(new Text("" + threadList.get(i).getBall().getColorG()));
				
				Element color_B = new Element("color-b");
				color_B.addContent(new Text("" + threadList.get(i).getBall().getColorB()));
				
				Element direction = new Element("direction");
				direction.addContent(new Text("" + threadList.get(i).getBall().getDirection()));
				
				elementList.get(i).addContent(name);
				elementList.get(i).addContent(position_X);
				elementList.get(i).addContent(position_Y);
				elementList.get(i).addContent(velocity_X);
				elementList.get(i).addContent(velocity_Y);
				elementList.get(i).addContent(color_R);
				elementList.get(i).addContent(color_G);
				elementList.get(i).addContent(color_B);
				elementList.get(i).addContent(direction);
				
				theRoot.addContent(elementList.get(i));
			}
			
			XMLOutputter xmlOutput = new XMLOutputter(Format.getPrettyFormat());
			
			xmlOutput.output(doc, new FileOutputStream(new File(path)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public LinkedList<BallThread> readXML(String path) {
		SAXBuilder builder = new SAXBuilder();
		LinkedList<BallThread> ballList = new LinkedList<BallThread>();
		Document readDoc = null;
		
		try {
			readDoc = builder.build(new File(path));
			
			System.out.println(readDoc.getRootElement().getChildren().size());
			
			for (int i = 0; i < readDoc.getRootElement().getChildren().size(); i++) {
				ballList.add(new BallThread(new Ball(readDoc.getRootElement().getChild("ball-" + (i + 1)).getChildText("name"))));
				
				ballList.getLast().getBall().setPosX(Integer.parseInt(readDoc.getRootElement().getChild("ball-" + (i + 1)).getChildText("position-x")));
				ballList.getLast().getBall().setPosY(Integer.parseInt(readDoc.getRootElement().getChild("ball-" + (i + 1)).getChildText("position-y")));
				ballList.getLast().getBall().setVelocityX(Integer.parseInt(readDoc.getRootElement().getChild("ball-" + (i + 1)).getChildText("velocity-x")));
				ballList.getLast().getBall().setVelocityY(Integer.parseInt(readDoc.getRootElement().getChild("ball-" + (i + 1)).getChildText("velocity-y")));
				ballList.getLast().getBall().setColorR(Integer.parseInt(readDoc.getRootElement().getChild("ball-" + (i + 1)).getChildText("color-r")));
				ballList.getLast().getBall().setColorG(Integer.parseInt(readDoc.getRootElement().getChild("ball-" + (i + 1)).getChildText("color-g")));
				ballList.getLast().getBall().setColorB(Integer.parseInt(readDoc.getRootElement().getChild("ball-" + (i + 1)).getChildText("color-b")));
				ballList.getLast().getBall().setDirection(Boolean.parseBoolean(readDoc.getRootElement().getChild("ball-" + (i + 1)).getChildText("direction")));
			}
		} catch (JDOMException | IOException e) {
			e.printStackTrace();
		}
		
		
		
		return ballList;
	}
}
