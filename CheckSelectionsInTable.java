import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class CheckSelectionsInTable {

	public CheckSelectionsInTable(JScrollPane scrollPane, JTable table, JLabel lblTagsSelected, 
			JLabel lblFilesWillBeChanged, JCheckBox chckbxChangeAlbum, JCheckBox chckbxChangeArtist,JCheckBox chckbxChangeGenre){
		int[] selectedIx = table.getSelectedRows();
		scrollPane.setViewportView(table);
		if (selectedIx.length==1) { 
			lblTagsSelected.setText("<html><body style='width: 200 px'>" +  selectedIx.length + " file is selected </html>");}
		else{
			lblTagsSelected.setText("<html><body style='width: 200 px'>" +  selectedIx.length + " files are selected" + "</html>");}
		if (chckbxChangeAlbum.isSelected()|| chckbxChangeArtist.isSelected()|| chckbxChangeGenre.isSelected()){
			if (selectedIx.length==1) {
				lblFilesWillBeChanged.setText("<html><body style='width: 200 px'><font color='red'>" +  selectedIx.length + " </font>" + " file  will be tagged" + "</html>");
			}
			else{
			lblFilesWillBeChanged.setText("<html><body style='width: 200 px'><font color='red'>" +  selectedIx.length + " </font>  files will be tagged </html>");
			}
		}
		else{
			lblFilesWillBeChanged.setText("<html><body style='width: 200 px'><font color='red'>" +  0 + " </font> files will be tagged </html>");

		}	
	}
}
