import java.io.File;
import java.io.IOException;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.CannotWriteException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.KeyNotFoundException;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;



public class TagChanger {
	private File file; 
	private  AudioFile mp3file; 
	

	public TagChanger (File imputFile){
		file= imputFile;
	}

	public void tagArtist(String artist) throws  IOException, KeyNotFoundException, CannotReadException, TagException, ReadOnlyFileException, InvalidAudioFrameException, CannotWriteException{
		
		AudioFile mp3file = AudioFileIO.read(file);
		Tag tag = mp3file.getTag();
		tag.setField(FieldKey.ARTIST,artist);
		mp3file.commit();
		 

		}
	
	public void tagAlbum(String album) throws  IOException,  CannotReadException, TagException, ReadOnlyFileException, InvalidAudioFrameException, CannotWriteException{
		AudioFile mp3file = AudioFileIO.read(file);
		Tag tag = mp3file.getTag();
		tag.setField(FieldKey.ALBUM,album);
		mp3file.commit();
		 

		}

	public File getFile() {
		return file;
	}


	public void setFile(File file) {
		this.file = file;
	}




}



