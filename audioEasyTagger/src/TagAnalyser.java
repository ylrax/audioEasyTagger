import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;




public class TagAnalyser extends filesFromFolder {
	private  ArrayList<String> listOfArtists = new ArrayList<String>();
	private  ArrayList<String> listOfAlbums = new ArrayList<String>();
	private ArrayList<String>  listOfGenres = new ArrayList<String>();
	private ArrayList<String>  listOfTitles = new ArrayList<String>();
	private  ArrayList<String> listOfArtistsSortedByFrequency = new ArrayList<String>();
	private  ArrayList<Integer> listOfFrequenciesArtists = new ArrayList<Integer>();

	private  ArrayList<String> listOfAlbumsSortedByFrequency = new ArrayList<String>();
	private  ArrayList<Integer> listOfFrequenciesAlbums = new ArrayList<Integer>();


	//this constructor takes the inputFolder and adds all the tags in the folder to the listOfArtists and listOfAlbums. Then it sorts them and stores the frequencies.
	public  TagAnalyser(File inputFolder) throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException {

		super(inputFolder);

		for (File file :this.getListOfFiles()){
			//here we find out the extension of the file
			String extension = "";

			int i = file.getName().lastIndexOf('.');
			if (i > 0) {
				extension = file.getName().substring(i+1);
			}

			// here we add the tags to the corresponding lists:
			if (extension.equalsIgnoreCase("mp3") || extension.equalsIgnoreCase("flac")|| extension.equalsIgnoreCase("ogg")){
				AudioFile mp3file = AudioFileIO.read(file);
				Tag tag = mp3file.getTag();
					 
						this.listOfArtists.add(tag.getFirst(FieldKey.ARTIST));
						this.listOfAlbums.add(tag.getFirst(FieldKey.ALBUM));
						this.listOfGenres.add(tag.getFirst(FieldKey.GENRE));
						this.listOfTitles.add(tag.getFirst(FieldKey.TITLE));
						 					 
				}
			}
		


		// here we sort the listOfArtists by frequencies:

		final Map<String, Integer> counter = new HashMap<String, Integer>();
		for (String str : listOfArtists)
			counter.put(str, 1 + (counter.containsKey(str) ? counter.get(str) : 0));

		this.listOfArtistsSortedByFrequency = new ArrayList<String>(counter.keySet());
		this.listOfFrequenciesArtists = new ArrayList<Integer>(listOfArtistsSortedByFrequency.size());

		Collections.sort(listOfArtistsSortedByFrequency, new Comparator<String>() {
			@Override
			public int compare(String x, String y) {
				return counter.get(y) - counter.get(x);
			}
		});

		for (String str : listOfArtistsSortedByFrequency) {
			int frequency = counter.get(str);
			this.listOfFrequenciesArtists.add(frequency);
		}



		// here we sort the listOfAlbums by frequencies:

		final Map<String, Integer> counter2 = new HashMap<String, Integer>();
		for (String str : listOfAlbums)
			counter2.put(str, 1 + (counter2.containsKey(str) ? counter2.get(str) : 0));

		this.listOfAlbumsSortedByFrequency = new ArrayList<String>(counter2.keySet());
		this.listOfFrequenciesAlbums = new ArrayList<Integer>(listOfAlbumsSortedByFrequency.size());

		Collections.sort(listOfAlbumsSortedByFrequency, new Comparator<String>() {
			@Override
			public int compare(String x, String y) {
				return counter2.get(y) - counter2.get(x);
			}
		});

		for (String str : listOfAlbumsSortedByFrequency) {
			int frequency = counter2.get(str);
			this.listOfFrequenciesAlbums.add(frequency);
		}

	}

	//getters and setters:
	
	public ArrayList<String> getListOfArtists() {
		return listOfArtists;
	}

	public void setListOfArtists(ArrayList<String> listOfArtists) {
		this.listOfArtists = listOfArtists;
	}

	public ArrayList<String> getListOfAlbums() {
		return listOfAlbums;
	}

	public void setListOfAlbums(ArrayList<String> listOfAlbums) {
		this.listOfAlbums = listOfAlbums;
	}

	public ArrayList<String> getListOfGenres() {
		return listOfGenres;
	}

	public void setListOfGenres(ArrayList<String> listOfGenres) {
		this.listOfGenres = listOfGenres;
	}

	public ArrayList<String> getListOfTitles() {
		return listOfTitles;
	}

	public void setListOfTitles(ArrayList<String> listOfTitles) {
		this.listOfTitles = listOfTitles;
	}

	public ArrayList<String> getListOfArtistsSortedByFrequency() {
		return listOfArtistsSortedByFrequency;
	}

	public void setListOfArtistsSortedByFrequency(
			ArrayList<String> listOfArtistsSortedByFrequency) {
		this.listOfArtistsSortedByFrequency = listOfArtistsSortedByFrequency;
	}

	public ArrayList<Integer> getListOfFrequenciesArtists() {
		return listOfFrequenciesArtists;
	}

	public ArrayList<Integer> getListOfFrequenciesAlbums() {
		return listOfFrequenciesAlbums;
	}

	public void setListOfFrequenciesAlbums(
			ArrayList<Integer> listOfFrequenciesAlbums) {
		this.listOfFrequenciesAlbums = listOfFrequenciesAlbums;
	}

	public void setListOfFrequenciesArtists(
			ArrayList<Integer> listOfFrequenciesArtists) {
		this.listOfFrequenciesArtists = listOfFrequenciesArtists;
	}

	public ArrayList<String> getListOfAlbumsSortedByFrequency() {
		return listOfAlbumsSortedByFrequency;
	}

	public void setListOfAlbumsSortedByFrequency(
			ArrayList<String> listOfAlbumsSortedByFrequency) {
		this.listOfAlbumsSortedByFrequency = listOfAlbumsSortedByFrequency;
	}

} 


