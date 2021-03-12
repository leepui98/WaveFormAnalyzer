
// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a SWEN131 exercise.
// You may not distribute it in any other way without permission.

import ecs100.*;
import java.io.*;
import java.util.*;
import java.awt.Color;

/**
 * This program will read the data from a file into an ArrayList, which means
 * that it can analyse the numbers more easily and in more different ways. It
 * can also cope with much larger sets of numbers. The numbers are guaranteed to
 * be integers but the values can be negative and the signal swings above and
 * below zero.
 *
 * There are 11 methods you are to complete, all focusing on the ArrayList of
 * data. CORE doRead: reads numbers into an ArrayList. doDisplay: displays the
 * waveform. doReportDistortion: prints out the fraction of time the signal is
 * distorted. COMPLETION doSpread: displays the spread with two horizontal lines
 * and returns its value. doDisplayDistortion: shows in red the distorted part
 * of the signal. doHighlightPeaks: plots the peaks with small green circles.
 * CHALLENGE doNormalise: normalises all the values down so there is no
 * distortion. upperEnvelope: displays the upper envelope. lowerEnvelope:
 * displays the lower envelope. doSave: saves the current waveform values into a
 * file. select and edit: let the user select a regions of the waveform with the
 * mouse to remove them. Add a save button to save the edited values.
 */

public class WaveformAnalyser {
	// Fields
	private ArrayList<Double> waveform; // the field to hold the ArrayList of
										// values

	// Constant: the threshold for the distortionLevel and showDistortion
	// methods
	public static final double THRESHOLD = 200;

	// Constants: the dimensions of the graph for the displayWaveform method
	public static final int GRAPH_LEFT = 10;
	public static final int ZERO_LINE = 360;

	// Constant fields holding the size of the circles for the highlightPeaks
	// method
	public static final int SIZE_CIRCLE = 10;

	/**
	 * [CORE] Clears the panes, Creates an ArrayList stored in a field, then
	 * Asks user for a waveform file (eg waveform1.txt) Reads data from the file
	 * into the ArrayList
	 */
	public void doRead() {
		try {
			UI.clearPanes();
			String fileName = "";
			Scanner scanner = null;
			
			waveform = new ArrayList<Double>();
			Scanner sc= new Scanner(new File(UIFileChooser.open()));
			while (sc.hasNextDouble()) {
			double wave= sc.nextDouble();
			waveform.add(wave);
			}
			
			/* # YOUR CODE HERE */

			// * Ask user for a file to read
			// * Open that file
			// * As long as there's another Double to read from it:
			//   - Take that double from the file
			//   - And put it into the list
			
			/* # YOUR CODE HERE */
			

			UI.println("Read " + this.waveform.size() + " data points from " + fileName);
			sc.close();
		} catch (FileNotFoundException e) {
			// The line above should be marked as an error when you start out,
			// but it will go away once you fill in your code above.
			e.printStackTrace();
		}
	}

	/**
	 * [CORE] Displays the waveform as a line graph, The n'th value in waveform
	 * is displayed at x-position is GRAPH_LEFT + n y-position is ZERO_LINE -
	 * the value Plots a line graph of all the points with a blue line between
	 * each pair of adjacent points Draw the horizontal line representing the
	 * value zero. Uses GRAPH_LEFT and ZERO_LINE for the dimensions and
	 * positions of the graph. Don't worry if the lines go off the window
	 */
	public void doDisplay() {
		if (this.waveform == null) { // there is no data to display
			UI.println("No waveform to display");
			return;
		}
		UI.clearGraphics();

		// draw x axis (showing where the value 0 will be)
		UI.setColor(Color.black);
		UI.drawLine(GRAPH_LEFT, ZERO_LINE, GRAPH_LEFT + this.waveform.size(), ZERO_LINE);

		// plot points: blue line between each pair of values
		/* # YOUR CODE HERE */
		for (int i=1; i<waveform.size(); i++) {
		double ncurrent=waveform.get(i);
		double nprevious=waveform.get(i-1);
		UI.setColor(Color.blue);
		UI.drawLine(GRAPH_LEFT + i-1,ZERO_LINE -nprevious, GRAPH_LEFT + i , ZERO_LINE + ncurrent); 
		
		}
		
		
		
	}

	/**
	 * [CORE] Computes and prints out the fraction of time the signal is
	 * distorted. This fraction of time is defined as the number of distorted
	 * values, divided by the number of values. A distorted value is defined as
	 * one whose absolute value is greater than the value of THRESHOLD [Hint]
	 * You may find Math.abs() useful for this method - it computes the absolute
	 * value
	 */
	public void doReportDistortion() {
		if (this.waveform == null) { // there is no data to analyse
			UI.println("No signal to analyse and report on");
			return;
		}
		double fraction = 0;
		double count = 0;
		/* # YOUR CODE HERE */
		double n= waveform.size();
		for (int i =0; i<n; i++) {          //absolute num
			double absolute =Math.abs(waveform.get(i));
			//waveform.set(i,absolute );	}
			if (absolute>THRESHOLD) {
				 //double distorted=absolute;
				 count++;//= distorted / n ;
			}		
		}
		fraction = count / n;
		
		
		/*for (double i = 0; i<waveform.size();i++) {
		
		}  */
		

		UI.printf("Fraction of time the signal is distorted %4.3f\n", fraction);
	}


	/**
	 * [COMPLETION] The spread is the difference between the maximum and minimum
	 * values of the waveform. Finds the maximum and minimum values, then
	 * Displays the spread by drawing two horizontal lines on top of the
	 * waveform: one green line for the maximum value, and one red line for the
	 * minimum value.
	 */
	public void doSpread() {
		if (this.waveform == null) { // there is no data to display
			UI.println("No waveform to display");
			return;
		}
		this.doDisplay();
		/* # YOUR CODE HERE */
		double x= GRAPH_LEFT+this.waveform.size();
		
		  //we assume that index at 0(array space) is min value
		int yMin=0;
		for(int i=1; i<waveform.size(); i++) { 
			//if example index at 2 is lower than the index in 0, it replace the i to 2
		if (waveform.get(i)<waveform.get(yMin)) {
		 yMin=i; 
		 }
		
		}
		
		int yMax=0;
		for(int j=1; j<waveform.size(); j++) {
	    if (waveform.get(j)>waveform.get(yMax)) {
			 yMax=j; 
			 }
		}
		
		UI.println("Min = "+waveform.get(yMin)+", Max = "+waveform.get(yMax));
		
		UI.setColor(Color.green);
		UI.drawLine(GRAPH_LEFT, ZERO_LINE-waveform.get(yMax) , x,ZERO_LINE- waveform.get(yMax));
		UI.setColor(Color.red);
		UI.drawLine(GRAPH_LEFT, ZERO_LINE-waveform.get(yMin), x, ZERO_LINE-waveform.get(yMin));      // not showing the right thing
		
		

	}

	/**
	 * [COMPLETION] [Fancy version of doDisplay] Display the waveform as a line
	 * graph. Draw a line between each pair of adjacent points * If neither of
	 * the points is distorted, the line is BLUE * If either of the two end
	 * points is distorted, the line is RED Draw the horizontal lines
	 * representing the value zero and thresholds values. Uses THRESHOLD to
	 * determine distorted values. Uses GRAPH_LEFT and ZERO_LINE for the
	 * dimensions and positions of the graph. [Hint] You may find Math.abs(int
	 * a) useful for this method. You may assume that all the values are between
	 * -250 and +250.
	 */
	public void doDisplayDistortion() {
		if (this.waveform == null) { // there is no data to display
			UI.println("No waveform to display");
			return;
		}
		UI.clearGraphics();

		// draw zero axis
		UI.setColor(Color.black);
		UI.drawLine(GRAPH_LEFT, ZERO_LINE, GRAPH_LEFT + this.waveform.size(), ZERO_LINE);

		// draw thresholds
		/* # YOUR CODE HERE */
		
        UI.setColor(Color.orange);
        UI.drawLine(GRAPH_LEFT, THRESHOLD, GRAPH_LEFT + this.waveform.size() , THRESHOLD);
	
        for (int i =1; i<waveform.size(); i++) {
        double ncurrent=waveform.get(i);
		double nprevious=waveform.get(i-1);	
		int distortion=0;        //absolute num
		if (Math.abs(waveform.get(i))>THRESHOLD) {
			distortion=distortion+1;
		    UI.setColor(Color.red);	
		    UI.drawLine(GRAPH_LEFT+ i-1, ZERO_LINE -nprevious  , GRAPH_LEFT +i, ZERO_LINE -ncurrent );
		}	
		else {
			distortion=distortion+1;
			UI.setColor(Color.blue);
			UI.drawLine(GRAPH_LEFT + i-1,ZERO_LINE -nprevious, GRAPH_LEFT + i , ZERO_LINE - ncurrent);
			}
		
		}
        }


	/**
	 * [COMPLETION] Plots the peaks with small green circles. A peak is defined
	 * as a value that is greater or equals to both its neighbouring values.
	 * Note the size of the circle is in the constant SIZE_CIRCLE You may assume
	 * that peaks values differ from their neighbouring points.
	 */
	public void doHighlightPeaks() {
		this.doDisplayDistortion(); // use doDisplay if doDisplayDistortion
									// isn't complete
		/* # YOUR CODE HERE */
		for (int i =1; i < waveform.size()-1; i++) {  //if no size -1, then program will crash because no value after the last number
		double nPrevious=waveform.get(i-1);
		double nNext=waveform.get(i+1);
		double mid= waveform.get(i);
		if(mid>nPrevious && mid>nNext) {
		UI.setColor(Color.green);
		UI.drawOval(GRAPH_LEFT+i-10, ZERO_LINE-mid-10, SIZE_CIRCLE, SIZE_CIRCLE);
		}
		}

	}

	/**
	 * [CHALLENGE] Finds the largest value (positive or negative) in the
	 * waveform, and normalises all the values down so that the largest value is
	 * now equal to the distortion threshold. Then redraws the waveform.
	 */
	public void doNormalise() {
		/* # YOUR CODE HERE */
      int largest = 0;
      for (int i=1; i<waveform.size();i++ ) {
    	  if (Math.abs(i) > largest) {
    		  largest = Math.abs(i);
    		  }
    		  }
      
      
      for (int j=1; j<waveform.size(); j++) {
       if (largest>THRESHOLD) {
      /* waveform.  */ 
       }
      }
		this.doDisplayDistortion(); // use doDisplay if doDisplayDistortion
									// isn't complete
	}

	public void doEnvelope() {
		if (this.waveform == null) { // there is no data to display
			UI.println("No waveform to display");
			return;
		}
		this.doDisplay(); // display the waveform
		this.upperEnvelope();
		this.lowerEnvelope();
	}

	/*int yMin=0;
		for(int i=1; i<waveform.size(); i++) { 
			//if example index at 2 is lower than the index in 0, it replace the i to 2
		if (waveform.get(i)<waveform.get(yMin)) {
		 yMin=i; 
		 }
		 */
	/**
	 * [CHALLENGE] Displays the upper envelope with GREEN lines connecting all
	 * the peaks. A peak is defined as a point that is greater or equal to
	 * *both* neighbouring points. DO NOT clear the graphics as we also want to
	 * view the waveform.
	 */
	public void upperEnvelope() {
		/* # YOUR CODE HERE */
	}

	/**
	 * [CHALLENGE] Displays the lower envelope with RED lines connecting all the
	 * "negative" peaks. A "negative" peak is defined as a point that is smaller
	 * or equal to *both* neighbouring points. DO NOT clear the graphics as we
	 * also want to view the waveform.
	 */
	public void lowerEnvelope() {
		/* # YOUR CODE HERE */

	}

	/**
	 * [CHALLENGE] Saves the current waveform values into a file
	 */
	public void doSave() {
		/* # YOUR CODE HERE */

	}

	private int index1;

	/**
	 * [CHALLENGE] Lets user select a region of the waveform with the mouse and
	 * deletes that section of the waveform.
	 */
	public void doMouse(String action, double x, double y) {
		/* # YOUR CODE HERE */

	}

	/** ---------- The code below is already written for you ---------- **/

	/**
	 * Constructor: Set up the ten buttons and mouselistener
	 */
	public WaveformAnalyser() {
		// core
		UI.addButton("Read Data", this::doRead);
		UI.addButton("Display Waveform", this::doDisplay);
		UI.addButton("Report Distortion", this::doReportDistortion);
		// completion
		UI.addButton("Spread", this::doSpread);
		UI.addButton("Display Distortion", this::doDisplayDistortion);
		UI.addButton("Peaks", this::doHighlightPeaks);
		// challenge
		UI.addButton("Normalise", this::doNormalise);
		UI.addButton("Envelope", this::doEnvelope);
		UI.addButton("Save", this::doSave);
		UI.addButton("Quit", UI::quit);
		UI.setMouseListener(this::doMouse); // only for challenge

	}

	// Main
	public static void main(String[] arguments) {
		new WaveformAnalyser();
	}

}
