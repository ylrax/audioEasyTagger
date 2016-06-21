import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JList;
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


	public ListenerChangeLabelsButton(JButton btnChangeLabels, final JCheckBox chckbxChangeAlbum, final JTextField txtNewAlbum, final ArrayList<File> totalListOfFiles, final JList<File> listOfFilesToTag, final JCheckBox chckbxChangeArtist,final JTextField txtNewArtist){
			
		btnChangeLabels.addActionListener(new ActionListener() {
			//First we get the selected files whose tags the user wants to change:
			public void actionPerformed(ActionEvent e) {
				final ArrayList<File> totalListOfFilesLocal = totalListOfFiles;

				//Here we change the selected files only
				int[] selectedIx = listOfFilesToTag.getSelectedIndices();
				ArrayList<File> selectedFilesToTag = new ArrayList<File>();
				if (! listOfFilesToTag.isSelectionEmpty()){
					for (int i = 0; i < selectedIx.length; i++) {
						selectedFilesToTag.add(totalListOfFilesLocal.get(selectedIx[i]));	
					}
					//jlistListOfFilesToTag.clearSelection(); 
					//jlistListOfFilesToTag.setSelectedIndex(-1);
					System.out.println(selectedFilesToTag);
				}
				else{ 
					selectedFilesToTag =  totalListOfFilesLocal;}
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
			}

		});

	}
}
