import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.CannotWriteException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.KeyNotFoundException;
import org.jaudiotagger.tag.TagException;


public class ListenerChangeLabelsButton {

	//this class controls the button btnChangeLabels. It checks the selected files and changes the tags every time the user presses the button.

	public ListenerChangeLabelsButton(JButton btnChangeLabels, final JCheckBox chckbxChangeAlbum, final JTextField txtNewAlbum, 
			final ArrayList<File> totalListOfFiles, final JTable table, final JCheckBox chckbxChangeArtist,final JTextField txtNewArtist,
			final File selectedFile, final JScrollPane scrollPane, final JScrollPane scrollPane_Albums, final JScrollPane scrollPane_Artists, 
			final JLabel lblNewLabel_1, final JList listAlbums, final  JList listArtists){

		btnChangeLabels.addActionListener(new ActionListener() {

			//First we get the selected files whose tags the user wants to change:
			public void actionPerformed(ActionEvent e) {
 
				int[] selectedIx = table.getSelectedRows();
				ArrayList<File> selectedFilesToTag = new ArrayList<File>();
				for (int i = 0; i < selectedIx.length; i++) {
					selectedFilesToTag.add(totalListOfFiles.get(selectedIx[i]));	
				}
				System.out.println(selectedFilesToTag);

				table.clearSelection(); 

				for (final File fileInList : selectedFilesToTag){
					TagChanger tagger = new TagChanger(fileInList);
					if (chckbxChangeAlbum.isSelected()){
						try {
							tagger.tagAlbum(txtNewAlbum.getText());
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (CannotReadException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (TagException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ReadOnlyFileException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (InvalidAudioFrameException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (CannotWriteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
					if (chckbxChangeArtist.isSelected()){
						try {
							tagger.tagArtist(txtNewArtist.getText());
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (KeyNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (CannotReadException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (TagException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ReadOnlyFileException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (InvalidAudioFrameException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (CannotWriteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
				}
				  new loadFilesInTable(selectedFile, table,   scrollPane,  scrollPane_Albums,   scrollPane_Artists,  lblNewLabel_1,  listAlbums, listArtists );
					table.clearSelection(); 

			}

		});

	}

	
}
