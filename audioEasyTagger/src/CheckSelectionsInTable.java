import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class CheckSelectionsInTable {

	public CheckSelectionsInTable(JScrollPane scrollPane, JTable table, JLabel lblTagsSelected, 
			JLabel lblFilesWillBeChanged, JCheckBox chckbxChangeAlbum, JCheckBox chckbxChangeArtist){
		int[] selectedIx = table.getSelectedRows();
		scrollPane.setViewportView(table);
		if (selectedIx.length==1) { 
			lblTagsSelected.setText(selectedIx.length + " file  is selected");}
		else{
			lblTagsSelected.setText(selectedIx.length + " files are selected");}
		if (chckbxChangeAlbum.isSelected()|| chckbxChangeArtist.isSelected()){
			if (selectedIx.length==1) {lblFilesWillBeChanged.setText("<html><font color='red'>" +  selectedIx.length + " </font>" + " file  will be tagged" + "</html>");
			}
			else{
			lblFilesWillBeChanged.setText("<html><font color='red'>" +  selectedIx.length + " </font>" + " files will be tagged" + "</html>");
			}
		}
		else{
			lblFilesWillBeChanged.setText("<html><font color='red'>" +  0 + " </font>" + " files will be tagged" + "</html>");

		}	
	}
}
