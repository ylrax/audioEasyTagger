import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;


public class RefreshTable {

	private JTable table = new JTable();
	private JScrollPane scrollPane = new JScrollPane();



	public RefreshTable (JTable table, JScrollPane scrollPane, File selectedFile) throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException{
		this.table =table;
		this.scrollPane = scrollPane;

		final TagAnalyser analyser = new TagAnalyser(selectedFile);
		final int numberOfFilesToTag = analyser.getListOfFilesString().size();
 		String[] columnNames = {"File","Title","Artist","Album","Genre"};
		String[][] dataInJTable = new String[numberOfFilesToTag][5];
		
		for (int i =0; i< analyser.getListOfFilesString().toArray().length; i++) {
			dataInJTable[i][0] = analyser.getListOfFilesString().get(i);
				dataInJTable[i][1] = analyser.getListOfTitles().get(i);
				dataInJTable[i][2] = analyser.getListOfArtists().get(i);
				dataInJTable[i][3] = analyser.getListOfAlbums().get(i);
				dataInJTable[i][4] = analyser.getListOfGenres().get(i);
		}
		  DefaultTableModel model = new DefaultTableModel(dataInJTable, columnNames);
		  table.setModel(model);
		scrollPane.setViewportView(table);
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}
	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

}
