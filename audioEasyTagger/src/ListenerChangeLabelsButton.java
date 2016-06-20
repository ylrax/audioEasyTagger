import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;

import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.CannotWriteException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.KeyNotFoundException;
import org.jaudiotagger.tag.TagException;
 

public class ListenerChangeLabelsButton {


	public ListenerChangeLabelsButton(JButton btnChangeLabels, final JCheckBox chckbxChangeAlbum, final JTextField txtNewAlbum, final ArrayList<File> listOfFiles, final JCheckBox chckbxChangeArtist,final JTextField txtNewArtist){
		btnChangeLabels.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				for (final File fileInList : listOfFiles){
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
