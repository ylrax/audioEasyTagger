import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;


public class loadFilesInTable {

	public  loadFilesInTable(File selectedFile, JTable table, JScrollPane scrollPane, JScrollPane scrollPane_Albums, JScrollPane scrollPane_Artists, JLabel lblNewLabel_1, JList listAlbums, JList listArtists ){
		try {

			final TagAnalyser analyser = new TagAnalyser(selectedFile);
			final int numberOfFilesToTag = analyser.getListOfFilesString().size();





			 

			// here we display the Artists and the Albums in the JList
			ArrayList<String> listOfAlbumsWithFrequencies = new ArrayList<String>();
			int i=0;
			for (String album: analyser.getListOfAlbumsSortedByFrequency()){
				listOfAlbumsWithFrequencies.add(album + " (" + analyser.getListOfFrequenciesAlbums().get(i) + ")" );
				i++;
			}
			listAlbums = new JList(listOfAlbumsWithFrequencies.toArray());
			scrollPane_Albums.setViewportView(listAlbums);

			ArrayList<String> listOfArtistsWithFrequencies = new ArrayList<String>();
			int j=0;
			for (String artist: analyser.getListOfArtistsSortedByFrequency()){
				listOfArtistsWithFrequencies.add(artist + " (" + analyser.getListOfFrequenciesArtists().get(j) + ")" );
				j++;
			}
			listArtists = new JList(listOfArtistsWithFrequencies.toArray());
			scrollPane_Artists.setViewportView(listArtists);
			
			
			//here we display the table
			String[] columnNames = {"File","Title","Artist","Album","Genre"};
			String[][] dataInJTable = new String[numberOfFilesToTag][5];

			for (int i1 =0; i1< analyser.getListOfFilesString().toArray().length; i1++) {
				dataInJTable[i1][0] = analyser.getListOfFilesString().get(i1);
				dataInJTable[i1][1] = analyser.getListOfTitles().get(i1);
				dataInJTable[i1][2] = analyser.getListOfArtists().get(i1);
				dataInJTable[i1][3] = analyser.getListOfAlbums().get(i1);
				dataInJTable[i1][4] = analyser.getListOfGenres().get(i1);
			}
			table= new JTable(dataInJTable, columnNames);
			table.clearSelection(); 
			scrollPane.add(table);
			scrollPane.repaint();
			scrollPane.setViewportView(table);
			


		} catch (IOException | CannotReadException | TagException | ReadOnlyFileException | InvalidAudioFrameException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	}
	
}
