package org.openscience.jchempaint.application;

import javax.swing.JFrame;

import org.openscience.cdk.DefaultChemObjectBuilder;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IMolecule;
import org.openscience.cdk.layout.StructureDiagramGenerator;
import org.openscience.cdk.smiles.SmilesParser;
import org.openscience.jchempaint.JChemPaintPanel;
import org.openscience.jchempaint.RenderPanel;

public class JChemPaint {
	
	public static IAtomContainer makeMolecule(String smiles) {
		SmilesParser parser = new SmilesParser(DefaultChemObjectBuilder.getInstance());
		try {
			IMolecule mol = parser.parseSmiles(smiles);
			
			StructureDiagramGenerator generator = new StructureDiagramGenerator();
			generator.setMolecule(mol);
			generator.generateCoordinates();
			
			return (IAtomContainer) generator.getMolecule();
		} catch (Exception e) {
			return null;
		}
	}
	
	public static void main(String[] args) {
		JFrame f = new JFrame("TestApp");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		IAtomContainer ac = JChemPaint.makeMolecule("C1=CC=NC=C1");
		if (ac == null) {
			System.exit(0);
		} 
		JChemPaintPanel p = new JChemPaintPanel(ac);
		f.add(p);
		f.pack();
		f.setVisible(true);
	}

}