import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;


public class loadFilesInTable {

	public  loadFilesInTable(File selectedFile, JTable table, JScrollPane scrollPane, 
			JScrollPane scrollPane_Albums, JScrollPane scrollPane_Genres,  JScrollPane scrollPane_Artists,  
			JLabel lblNewLabel_1, JList listAlbums, JList listArtists, JList listGenres ){
		try {

			 TagAnalyser analyser = new TagAnalyser(selectedFile);

			// here we display the Artists and the Albums in the JList
			DefaultListModel<String> listModelAlbum = new DefaultListModel();
			ArrayList<String> listOfAlbumsWithFrequencies = new ArrayList<String>();
			int i=0;
			for (String album: analyser.getListOfAlbumsSortedByFrequency()){
				listOfAlbumsWithFrequencies.add(album + " (" + analyser.getListOfFrequenciesAlbums().get(i) + ")" );
				listModelAlbum.add(i, album + " (" + analyser.getListOfFrequenciesAlbums().get(i) + ")" );
				i++;
			}

			listAlbums.setModel(listModelAlbum);
			scrollPane_Albums.setViewportView(listAlbums);

			DefaultListModel<String> listModelArtist = new DefaultListModel();

			ArrayList<String> listOfArtistsWithFrequencies = new ArrayList<String>();
			int j=0;
			for (String artist: analyser.getListOfArtistsSortedByFrequency()){
				listOfArtistsWithFrequencies.add(artist + " (" + analyser.getListOfFrequenciesArtists().get(j) + ")" );
				listModelArtist.add(j, artist + " (" + analyser.getListOfFrequenciesArtists().get(j) + ")" );
				j++;

			}
			listArtists.setModel(listModelArtist);
			scrollPane_Artists.setViewportView(listArtists);
			
			
			
			DefaultListModel<String> listModelGenres = new DefaultListModel();

			ArrayList<String> listOfGenresWithFrequencies = new ArrayList<String>();
			int k=0;
			for (String genre: analyser.getListOfGenresSortedByFrequency()){
				listOfGenresWithFrequencies.add(genre + " (" + analyser.getListOfFrequenciesGenres().get(k) + ")" );
				listModelGenres.add(k, genre + " (" + analyser.getListOfFrequenciesGenres().get(k) + ")" );
				k++;

			}
			listGenres.setModel(listModelGenres);
			scrollPane_Genres.setViewportView(listGenres);



			//here we display the table
			String[] columnNames = {"File","Title","Artist","Album","Genre"};
			int numberOfFilesToTag = analyser.getListOfFilesString().size();
			String[][] dataInJTable = new String[numberOfFilesToTag][5];
 			for (int i1 =0; i1< numberOfFilesToTag; i1++) {
				dataInJTable[i1][0] = analyser.getListOfFilesString().get(i1);
				dataInJTable[i1][1] = analyser.getListOfTitles().get(i1);
				dataInJTable[i1][2] = analyser.getListOfArtists().get(i1);
				dataInJTable[i1][3] = analyser.getListOfAlbums().get(i1);
				dataInJTable[i1][4] = analyser.getListOfGenres().get(i1);
 			}
			DefaultTableModel model = new DefaultTableModel(dataInJTable, columnNames);
			table.setModel(model);
			scrollPane.setViewportView(table);



		} catch (IOException | CannotReadException | TagException | ReadOnlyFileException | InvalidAudioFrameException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	}

}
